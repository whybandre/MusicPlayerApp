package com.example.android.musicplayerapp;

/**
 * Created by Bruno André on 04/04/2018.
 */

public class CategorySong {
    private String mPlayList;
    private int mImageResId;

    // Default constructor
    public CategorySong(String playList, int imageResId) {
        mPlayList = playList;
        mImageResId = imageResId;
    }

    /**
     * This method gets playlist name
     *
     * @return playlist
     */
    public String getPlayList() {
        return mPlayList;
    }

    /**
     * This method gets image resource id
     *
     * @return image resource id
     */
    public int getImageResId() {
        return mImageResId;
    }
}
