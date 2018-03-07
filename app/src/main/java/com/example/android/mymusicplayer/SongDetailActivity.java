package com.example.android.mymusicplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ceciliaHumlelu on 2018-03-06.
 */

public class SongDetailActivity extends AppCompatActivity {
    private int songPosition;
    private int albumPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detail);

        final AlbumDetail[] albums = {new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","Reputation",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
                new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","Witness",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
                new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","1989",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
                new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","Bad blood",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3})
        } ;

        ImageView albumPhoto = (ImageView) findViewById(R.id.song_detail_album_image);
        TextView songName = (TextView)findViewById(R.id.song_detail_name);
        TextView singerName = (TextView)findViewById(R.id.song_detail_singer);
        TextView albumName = (TextView) findViewById(R.id.song_detail_album_name);
        TextView startTime = (TextView) findViewById(R.id.start_time);
        TextView finishTime = (TextView) findViewById(R.id.finish_time);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras==null){
                Toast.makeText(this,"Data can't be found",Toast.LENGTH_SHORT);
            }else {
                songPosition = extras.getInt("songPosition");
                albumPosition = extras.getInt("albumPosition");
                String[] songs = albums[albumPosition].getSongNames();
                albumPhoto.setImageResource(albums[albumPosition].getAlbumPhotoId());
                songName.setText(songs[songPosition]);
                singerName.setText(albums[albumPosition].getSinger());
                albumName.setText(albums[albumPosition].getAlbumName());

            }
        }
    }
}
