package com.example.android.mymusicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ceciliaHumlelu on 2018-03-06.
 */

public class SongDetailActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private int mSongPosition, mAlbumPosition, mAlbumPhotoId, mSongId;
    private String mAlbumName, mSongName, mSingerName, totalTime, elapsedTime;
    private int[] mSongIds;
    private DemoData mDemo = new DemoData();
    private AlbumDetail[] mAlbums;
    private AlbumDetail mAlbum;
    private String[] mSongNames;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private ImageView mAlbumPhotoV;
    private TextView mSongNameV, mSingerNameV, mAlbumNameV, mStartTimeV, mFinishTimeV;
    private SeekBar mSeekBar;
    private ImageButton mPlay_pause_btn, mPrevious_btn, mNext_btn;
    private Runnable mUpdateSeekbar;
    private Handler mHandler = new Handler();
    private boolean isAudioFocusGranted = false;
    private SimpleDateFormat formatter;

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
                updateSeekBar();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer(true);
            } else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mMediaPlayer.pause();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detail);

        //get all views from song detail layout
        mAlbumPhotoV = (ImageView) findViewById(R.id.song_detail_album_image);
        mSongNameV = (TextView) findViewById(R.id.song_detail_name);
        mSingerNameV = (TextView) findViewById(R.id.song_detail_singer);
        mAlbumNameV = (TextView) findViewById(R.id.song_detail_album_name);
        mStartTimeV = (TextView) findViewById(R.id.start_time);
        mFinishTimeV = (TextView) findViewById(R.id.finish_time);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mPlay_pause_btn = (ImageButton) findViewById(R.id.play_or_pause);
        mPrevious_btn = (ImageButton) findViewById(R.id.skip_previous);
        mNext_btn = (ImageButton) findViewById(R.id.skip_next);

        mPlay_pause_btn.setOnClickListener(this);
        mNext_btn.setOnClickListener(this);
        mPrevious_btn.setOnClickListener(this);

        mSeekBar.setOnSeekBarChangeListener(this);

        //get demo data
        mAlbums = mDemo.getDemoData();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Toast.makeText(this, getString(R.string.data_not_found_msg), Toast.LENGTH_SHORT);
            } else {
                mSongPosition = extras.getInt(getString(R.string.song_position_key));
                mAlbumPosition = extras.getInt(getString(R.string.album_position_key));
                mAlbum = mAlbums[mAlbumPosition];
                mSongNames = mAlbum.getSongNames();
                mSongIds = mAlbum.getSongAudioId();
                mAlbumPhotoId = mAlbum.getAlbumPhotoId();
                mAlbumName = mAlbum.getAlbumName();
                mSingerName = mAlbum.getSinger();
                mSongName = mSongNames[mSongPosition];
                mSongId = mSongIds[mSongPosition];
                mAlbumPhotoV.setImageResource(mAlbumPhotoId);
                mAlbumNameV.setText(mAlbumName);
                mSingerNameV.setText(mSingerName);
                updateView(mSongName);
            }
        }

        initMediaPlayer(mSongId, true);
    }

    private void initMediaPlayer(int songId, boolean ifAbandonAudioFocus) {
        releaseMediaPlayer(ifAbandonAudioFocus);
        if (ifAbandonAudioFocus) {
            requestAudioFocus();
        }
        if (isAudioFocusGranted) {
            mMediaPlayer = MediaPlayer.create(this, songId);
            mMediaPlayer.start();
            updateSeekBar();
            mPlay_pause_btn.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
            mMediaPlayer.setOnCompletionListener(this);
        }
    }

    private void releaseMediaPlayer(boolean ifAbandonAudioFocus) {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mSeekBar.setProgress(0);
            mHandler.removeCallbacks(mUpdateSeekbar);
            if (ifAbandonAudioFocus) {
                mAudioManager.abandonAudioFocus(audioFocusChangeListener);
            }
        }
    }

    private boolean requestAudioFocus() {
        isAudioFocusGranted = false;
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int result = mAudioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            isAudioFocusGranted = true;
        } else if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
            isAudioFocusGranted = false;
        } else {
            isAudioFocusGranted = false;
        }
        return isAudioFocusGranted;
    }

    private void updateSeekBar() {
        int songDuration = mMediaPlayer.getDuration() / 1000;
        formatter = new SimpleDateFormat("mm:ss");
        Date total = new Date(mMediaPlayer.getDuration());
        totalTime = formatter.format(total);
        mFinishTimeV.setText(totalTime);
        mStartTimeV.setText("00:00");
        mSeekBar.setMax(songDuration);
        mUpdateSeekbar = new Runnable() {
            @Override
            public void run() {
                if (mMediaPlayer.isPlaying()) {
                    int songCurrentPosition = mMediaPlayer.getCurrentPosition() / 1000;
                    Date elapsed = new Date(mMediaPlayer.getCurrentPosition());
                    elapsedTime = formatter.format(elapsed);
                    mStartTimeV.setText(elapsedTime);
                    mSeekBar.setProgress(songCurrentPosition);
                    mHandler.postDelayed(this, 1000);
                }
            }
        };
        mHandler.postDelayed(mUpdateSeekbar, 1000);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        nextSong();
    }

    private void nextSong() {
        if (mSongPosition < mSongIds.length - 1) {
            mSongPosition += 1;
        } else {
            mSongPosition = 0;
        }
        mSongId = mSongIds[mSongPosition];
        mSongName = mSongNames[mSongPosition];
        initMediaPlayer(mSongId, false);
        updateView(mSongName);
    }

    private void updateView(String songName) {
        mSongNameV.setText(songName);
    }

    private void previousSong() {
        if (mSongPosition > 0) {
            mSongPosition -= 1;
        } else {
            mSongPosition = mSongIds.length - 1;
        }
        mSongId = mSongIds[mSongPosition];
        mSongName = mSongNames[mSongPosition];
        updateView(mSongName);
        initMediaPlayer(mSongId, false);
    }

    private void playOrPause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlay_pause_btn.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
            return;
        } else if (mMediaPlayer != null) {
            mMediaPlayer.start();
            mPlay_pause_btn.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
            return;
        } else {
            initMediaPlayer(mSongId, true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_or_pause:
                playOrPause();
                return;
            case R.id.skip_next:
                nextSong();
                return;
            case R.id.skip_previous:
                previousSong();
                return;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlay_pause_btn.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        releaseMediaPlayer(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        releaseMediaPlayer(true);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mMediaPlayer.seekTo(progress * 1000);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
