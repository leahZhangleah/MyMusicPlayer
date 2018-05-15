package com.example.android.mymusicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ceciliaHumlelu on 2018-03-05.
 */

public class AlbumDetailActivity extends AppCompatActivity {
    private int albumPosition;
    private ImageView albumPhoto;
    private TextView albumName, albumSinger;
    private ListView songs;
    private DemoData demo = new DemoData();
    private AlbumDetail[] albums;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_detail);

        albumPhoto = (ImageView) findViewById(R.id.album_detail_image);
        albumName = (TextView) findViewById(R.id.album_detail_name);
        albumSinger = (TextView) findViewById(R.id.album_detail_singer);


        //repeated data of album list
        albums = demo.getDemoData();

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                Toast.makeText(AlbumDetailActivity.this, getString(R.string.data_not_found_msg), Toast.LENGTH_SHORT).show();
            } else {
                albumPosition = extra.getInt("albumPosition");
                albumPhoto.setImageResource(albums[albumPosition].getAlbumPhotoId());
                albumName.setText(albums[albumPosition].getAlbumName());
                albumSinger.setText(getString(R.string.album_by) + " " + albums[albumPosition].getSinger());
            }
        }

        songs = (ListView) findViewById(R.id.songs_list);
        SongsAdapter songsAdapter = new SongsAdapter(AlbumDetailActivity.this, albums[albumPosition].getSongNames(), albums[albumPosition].getSinger());
        songs.setAdapter(songsAdapter);

        songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AlbumDetailActivity.this, SongDetailActivity.class);
                intent.putExtra(getString(R.string.song_position_key), i);
                intent.putExtra(getString(R.string.album_position_key), albumPosition);
                startActivity(intent);
            }
        });
    }
}

