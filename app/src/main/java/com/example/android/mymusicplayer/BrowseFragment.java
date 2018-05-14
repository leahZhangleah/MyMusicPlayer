package com.example.android.mymusicplayer;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ceciliaHumlelu on 2018-03-04.
 */

public class BrowseFragment extends Fragment {
    GridView albumView;
    private DemoData demo = new DemoData();
    private AlbumDetail[] albums;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View browse = inflater.inflate(R.layout.fragment_browse,container,false);
        albumView = (GridView) browse.findViewById(R.id.album_view);
        albums = demo.getDemoData();

        AlbumAdapter albumAdapter = new AlbumAdapter(getActivity(),albums);
        albumView.setAdapter(albumAdapter);

        albumView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView albumPhoto = (ImageView) view.findViewById(R.id.album_image);
                TextView albumName = (TextView) view.findViewById(R.id.album_name);
                TextView albumSinger = (TextView)view.findViewById(R.id.singer_name);

                Pair<View,String> p1 = Pair.create((View)albumPhoto,getString(R.string.shared_image));
                Pair<View,String> p2 = Pair.create((View)albumName,getString(R.string.shared_album));
                Pair<View,String> p3 = Pair.create((View)albumSinger,getString(R.string.shared_singer));

                Intent intent = new Intent(getActivity(),AlbumDetailActivity.class);
                intent.putExtra("albumPosition",i);

                if (Build.VERSION.SDK_INT >= 21) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),p1,p2,p3);
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });

        return browse;
    }


}
