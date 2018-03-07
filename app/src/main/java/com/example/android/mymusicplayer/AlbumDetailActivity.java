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
    int albumPosition;
    ImageView albumPhoto;
    TextView albumName;
    TextView albumSinger;
    ListView songs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_detail);

        albumPhoto = (ImageView) findViewById(R.id.album_detail_image);
        albumName = (TextView) findViewById(R.id.album_detail_name);
        albumSinger = (TextView) findViewById(R.id.album_detail_singer);


        //repeated data of album list
        //TODO find a way to build a seperate data file, read data from that file for both this activity and album list activity
        final AlbumDetail[] albums = {new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","Reputation",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
                new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","Witness",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
                new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","1989",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
                new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift","Bad blood",new String[]{"Swish Swish","Power","Mind Maze"},new int[]{R.raw.song1,R.raw.song2,R.raw.song3})
        } ;


        if (savedInstanceState == null){
            Bundle extra = getIntent().getExtras();
                if (extra == null){
                    Toast.makeText(AlbumDetailActivity.this,"Data not available",Toast.LENGTH_SHORT).show();
                }else {
                    albumPosition = extra.getInt("albumPosition");
                    albumPhoto.setImageResource(albums[albumPosition].getAlbumPhotoId());
                    albumName.setText(albums[albumPosition].getAlbumName());
                    albumSinger.setText(albums[albumPosition].getSinger());
                }
        }

        songs = (ListView) findViewById(R.id.songs_list);
        SongsAdapter songsAdapter = new SongsAdapter(AlbumDetailActivity.this,albums[albumPosition].getSongNames(),
                albums[albumPosition].getSinger());
        songs.setAdapter(songsAdapter);

        songs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AlbumDetailActivity.this,SongDetailActivity.class);
                intent.putExtra("songPosition",i);
                intent.putExtra("albumPostion",albumPosition);
                startActivity(intent);

            }
        });


    }
}

