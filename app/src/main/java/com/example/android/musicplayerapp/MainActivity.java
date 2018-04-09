package com.example.android.musicplayerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        /*
        Start---------->Bottom Navigation Bar with Activities<-------------
        Bottom Navigation Bar with Activities - Android Advanced Tutorial #6 - https://www.youtube.com/watch?v=xyGrdOqseuw
         */

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        //To change color as we change activity
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_artist:
                                Intent intent2 = new Intent(MainActivity.this, AllArtistListActivity.class);
                                startActivity(intent2);
                                break;

                            case R.id.action_song:
                                Intent intent3 = new Intent(MainActivity.this, AllSongListActivity.class);
                                startActivity(intent3);
                                break;
                        }

                        return false;
                    }
                });

//End---------->Bottom Navigation Bar with Activities<-------------


        // Create ArrayList of playlist categories - Material Design Icons
        ArrayList<CategorySong> categorySongs = new ArrayList<CategorySong>();
        categorySongs.add(new CategorySong(getString(R.string.library_category_musopen), R.drawable.ic_sunglasses_black_48dp));
        categorySongs.add(new CategorySong(getString(R.string.library_category_youtube), R.drawable.ic_youtube_black_48dp));
        categorySongs.add(new CategorySong(getString(R.string.library_category_bensound), R.drawable.ic_guitar_electric_black_48dp));


        //Set Background
        ListView listView = (ListView) findViewById(R.id.list_playlist);
        listView.setBackgroundColor(getResources().getColor(R.color.home_background));


        // Create CategorySongAdapter object to display listview
        CategorySongAdapter adapter = new CategorySongAdapter(this, categorySongs);
        listView.setAdapter(adapter);

        // Set OnClickListener on ListView to identify the item on ListView clicked by user
        // Text on the ListView item clicked is passed on to CategorySongListActivity.class
        listView.setOnItemClickListener(this);
    }

    /**
     * This method identifies ListView item clicked and launches CategorySongListActivity
     *
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final Context context = this;

        TextView textView = (TextView) view.findViewById(R.id.text_playlist_category);
        String playlistText = textView.getText().toString();

        Intent intent = new Intent(context, CategorySongListActivity.class);
        intent.putExtra("message", playlistText);
        startActivity(intent);
    }
}