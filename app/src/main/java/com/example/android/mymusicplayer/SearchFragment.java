package com.example.android.mymusicplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ceciliaHumlelu on 2018-03-04.
 */

public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View browse = inflater.inflate(R.layout.fragment_search,container,false);
        TextView anotherthing = (TextView) browse.findViewById(R.id.another_thing_view);
        anotherthing.setText("Search");
        return browse;
    }

}
