package com.example.android.mymusicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ceciliaHumlelu on 2018-03-06.
 */

public class SongsAdapter extends ArrayAdapter<String> {
    private String singer;

    public SongsAdapter(@NonNull Context context, @NonNull String[] objects,String singer) {
        super(context,R.layout.song_view , objects);
        singer = singer;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View songView = convertView;
        if (songView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            songView = layoutInflater.inflate(R.layout.song_view,parent,false);
        }

        TextView songName = (TextView) songView.findViewById(R.id.song_name);
        TextView singerName = (TextView) songView.findViewById(R.id.song_singer_name);

        songName.setText(getItem(position));
        singerName.setText(singer);
        return songView;
    }
}
