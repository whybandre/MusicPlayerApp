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

/**
 * Created by Bruno André on 04/04/2018.
 */

public class AllArtistListActivity extends AppCompatActivity implements ListView.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);

          /*
        Start---------->Bottom Navigation Bar with Activities<-------------
        Bottom Navigation Bar with Activities - Android Advanced Tutorial #6 - https://www.youtube.com/watch?v=xyGrdOqseuw
         */

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        //To change color as we change activity
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);



        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_home:
                                Intent intent1 = new Intent(AllArtistListActivity.this, MainActivity.class);
                                startActivity(intent1);
                                break;


                            case R.id.action_song:
                                Intent intent3 = new Intent(AllArtistListActivity.this, AllSongListActivity.class);
                                startActivity(intent3);
                                break;
                        }

                        return false;
                    }
                });

//End---------->Bottom Navigation Bar with Activities<-------------




        // Display full list of songs
        createFullAlbumList();
    }

    /**
     * Method to create list of songs to be displayed
     */
    public void createFullAlbumList() {

        ArrayList<AllArtist> allArtists = new ArrayList<AllArtist>();

        //Musopen
        allArtists.add(new AllArtist("Scott Joplin", "Maple Leaf Rag"));
        allArtists.add(new AllArtist("Franz Liszt", "Hungarian Rhapsody"));
        allArtists.add(new AllArtist("Korsakov", "Flight of the Bumblebee"));
        allArtists.add(new AllArtist("Mozart", "Eine Kleine Nachtmusik"));
        allArtists.add(new AllArtist("Tchaikovsky", "Dance of the sugar plum fairy"));

        //Youtube
        allArtists.add(new AllArtist("Bach", "Toccata in D Minor"));
        allArtists.add(new AllArtist("Beethoven", "Symphony No 5"));
        allArtists.add(new AllArtist("Handel", "Aleluia Messiah"));
        allArtists.add(new AllArtist("Kevin MacLeod", "If I Had a Chicken"));
        allArtists.add(new AllArtist("Scott Joplin", "The Entertainer"));

        //Bensound
        allArtists.add(new AllArtist("Bensound", "Buddy"));
        allArtists.add(new AllArtist("Bensound", "Cute"));
        allArtists.add(new AllArtist("Bensound", "Happiness"));
        allArtists.add(new AllArtist("Bensound", "Sunny"));
        allArtists.add(new AllArtist("Bensound", "Ukulele"));




        ListView listView = (ListView) findViewById(R.id.list_albums);
        listView.setBackgroundColor(getResources().getColor(R.color.home_background));



        // Create AllSongAdapter object to display listview
        AllArtistAdapter adapter = new AllArtistAdapter(this, allArtists);
        listView.setAdapter(adapter);

        // Set OnClickListener on ListView to identify the item on ListView clicked by user
        // Text on the ListView item clicked is passed on to MediaPlayerActivity
        listView.setOnItemClickListener(this);
    }



    /**
     * Method to identify ListView item clicked and launch MediaPlayerActivity
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final Context context = this;
        String intentExtra = "";

        TextView textViewSong = (TextView) view.findViewById(R.id.text_song);
        String song = textViewSong.getText().toString();

        TextView textViewSinger = (TextView) view.findViewById(R.id.text_singer);
        String singer = textViewSinger.getText().toString();

        intentExtra = song + "|" + singer + "|";
        Intent intent = new Intent(context, MediaPlayerActivity.class);
        intent.putExtra("message", intentExtra);
        startActivity(intent);
    }

}