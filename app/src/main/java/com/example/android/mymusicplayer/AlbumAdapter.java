package com.example.android.mymusicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ceciliaHumlelu on 2018-03-05.
 */

public class AlbumAdapter extends ArrayAdapter<AlbumDetail> {


    public AlbumAdapter(@NonNull Context context, @NonNull AlbumDetail[] objects) {
        super(context, R.layout.album_view, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View albumView = convertView;
        if (albumView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            albumView = layoutInflater.inflate(R.layout.album_view,parent,false);
        }
        ImageView albumImage = (ImageView) albumView.findViewById(R.id.album_image);
        TextView albumName = (TextView)albumView.findViewById(R.id.album_name);
        TextView albumSinger = (TextView) albumView.findViewById(R.id.singer_name);

        albumImage.setImageResource(getItem(position).getAlbumPhotoId());
        albumName.setText(getItem(position).getAlbumName());
        albumSinger.setText(getItem(position).getSinger());

        return albumView;
    }
}
