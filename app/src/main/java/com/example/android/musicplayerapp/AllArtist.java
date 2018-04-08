package com.example.android.musicplayerapp;

/**
 * Created by Bruno André on 04/04/2018.
 */

public class AllArtist {

        private String  mSinger;
        private String mSong;

    // Default constructor
    public AllArtist(String singer, String song) {
        mSinger = singer;
        mSong = song;
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
