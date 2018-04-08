package com.example.android.musicplayerapp;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bruno André on 04/04/2018.
 */

public class CategorySongAdapter extends ArrayAdapter<CategorySong> {
    private Context mContext;

    public CategorySongAdapter(Activity context, ArrayList<CategorySong> playlist) {
        super(context, 0, playlist);
        mContext = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.category_item, parent, false);
        }

        CategorySong currentCategorySong = getItem(position);
        TextView textPlayList = (TextView) listItemView.findViewById(R.id.text_playlist_category);

        textPlayList.setText(currentCategorySong.getPlayList());


        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_playlist_category);
        imageView.setImageResource(currentCategorySong.getImageResId());
        //Define an Icon color
        ImageView imageViewIcon = (ImageView) listItemView.findViewById(R.id.image_playlist_category);
        imageViewIcon.setColorFilter(getContext().getResources().getColor(R.color.White));



        //Define a Random color
        int[] androidColors = listItemView.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

        //Set random background color for cardview
        ImageView backgroundColorThumbnail = (ImageView) listItemView.findViewById(R.id.thumbnail);
        backgroundColorThumbnail.setBackgroundColor(randomAndroidColor);

        return listItemView;
    }
}