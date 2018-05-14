package com.example.android.mymusicplayer;

import java.util.ArrayList;

/**
 * Created by ceciliaHumlelu on 2018-04-01.
 */

public class DemoData {
    private AlbumDetail[] demoData = {
            new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift",
            "Reputation",new String[]{"Swish Swish","Power","Mind Maze"},
            new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
            new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift",
                    "Witness",new String[]{"Swish Swish","Power","Mind Maze"},
                    new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
            new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift",
                    "1989",new String[]{"Swish Swish","Power","Mind Maze"},
                    new int[]{R.raw.song1,R.raw.song2,R.raw.song3}),
            new AlbumDetail(R.drawable.ic_home_black_24dp,"Taylor Swift",
                    "Bad blood",new String[]{"Swish Swish","Power","Mind Maze"},
                    new int[]{R.raw.song1,R.raw.song2,R.raw.song3})
    };

    public AlbumDetail[] getDemoData() {
        return demoData;
    }
}
