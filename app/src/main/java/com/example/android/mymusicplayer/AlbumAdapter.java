package com.example.android.mymusicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.DisplayMetrics;
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
    private Context context;

    public AlbumAdapter(@NonNull Context context, @NonNull AlbumDetail[] objects) {
        super(context, R.layout.album_view, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View albumView = convertView;
        if (albumView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            albumView = layoutInflater.inflate(R.layout.album_view, parent, false);
        }
        //set height for each album
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = metrics.widthPixels / 2;
        ViewGroup.LayoutParams layoutParams = albumView.getLayoutParams();
        layoutParams.height = px;
        albumView.setLayoutParams(layoutParams);

        ImageView albumImage = (ImageView) albumView.findViewById(R.id.album_image);
        TextView albumName = (TextView) albumView.findViewById(R.id.album_name);
        TextView albumSinger = (TextView) albumView.findViewById(R.id.singer_name);

        albumImage.setImageResource(getItem(position).getAlbumPhotoId());
        albumName.setText(getItem(position).getAlbumName());
        albumSinger.setText(context.getString(R.string.album_by) + " " + getItem(position).getSinger());

        return albumView;
    }
}
