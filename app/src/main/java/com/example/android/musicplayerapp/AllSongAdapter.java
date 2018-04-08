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

/**
 * Created by Bruno André on 04/04/2018.
 */

public class AllSongAdapter extends ArrayAdapter<AllSong> {

    private Context mContext;

    public AllSongAdapter(Activity context, ArrayList<AllSong> allSong) {
        super(context, 0, allSong);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

        AllSong currentAllSong = getItem(position);
        TextView textSong = (TextView) listItemView.findViewById(R.id.text_song);
        textSong.setText(currentAllSong.getSong());

        TextView textSinger = (TextView) listItemView.findViewById(R.id.text_singer);
        textSinger.setText(currentAllSong.getSinger());


        ImageView imageView = (ImageView) listItemView.findViewById(R.id.icon_song);
        imageView.setImageResource(R.drawable.play_circle_outline);
        //Define an Icon color
        ImageView imageViewIcon = (ImageView) listItemView.findViewById(R.id.icon_song);
        imageViewIcon.setColorFilter(getContext().getResources().getColor(R.color.blue));

        return listItemView;
    }
}

