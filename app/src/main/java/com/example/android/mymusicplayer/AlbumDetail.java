package com.example.android.mymusicplayer;

/**
 * Created by ceciliaHumlelu on 2018-03-05.
 */

public class AlbumDetail {
    private int albumPhotoId;
    private String singer;
    private String albumName;
    private String[] songNames;
    private int[] songAudioId;

    public AlbumDetail(int albumPhotoId, String singer, String albumName,String[] songNames, int[] songAudioId ){
        this.albumPhotoId = albumPhotoId;
        this.singer = singer;
        this.albumName = albumName;
        this.songNames = songNames;
        this.songAudioId = songAudioId;

    }



    public int getAlbumPhotoId() {
        return albumPhotoId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSinger() {
        return singer;
    }

    public String[] getSongNames() {
        return songNames;
    }
}
