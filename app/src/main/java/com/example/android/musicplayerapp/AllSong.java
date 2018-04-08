package com.example.android.musicplayerapp;

/**
 * Created by Bruno André on 04/04/2018.
 */

public class AllSong {
    private String mSong;
    private String  mSinger;

    // Default constructor
    public AllSong(String song, String singer) {
        mSong = song;
        mSinger = singer;
    }

    /**
     * This method gets activity_category_song_list name
     * @return activity_category_song_list
     */
    public String getSong() {
        return mSong;
    }

    /**
     * This method gets singer name
     * @return singer
     */
    public String getSinger() {
        return mSinger;
    }

}
