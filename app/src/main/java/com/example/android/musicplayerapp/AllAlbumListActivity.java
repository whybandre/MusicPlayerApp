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

public class AllAlbumListActivity extends AppCompatActivity implements ListView.OnItemClickListener {


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
                                Intent intent1 = new Intent(AllAlbumListActivity.this, MainActivity.class);
                                startActivity(intent1);
                                break;


                            case R.id.action_song:
                                Intent intent3 = new Intent(AllAlbumListActivity.this, AllSongListActivity.class);
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

        ArrayList<AllAlbum> allAlbums = new ArrayList<AllAlbum>();

        //Musopen
        allAlbums.add(new AllAlbum("Scott Joplin", "Maple Leaf Rag"));
        allAlbums.add(new AllAlbum("Franz Liszt", "Hungarian Rhapsody"));
        allAlbums.add(new AllAlbum("Korsakov", "Flight of the Bumblebee"));
        allAlbums.add(new AllAlbum("Mozart", "Eine Kleine Nachtmusik"));
        allAlbums.add(new AllAlbum("Tchaikovsky", "Dance of the sugar plum fairy"));

        //Youtube
        allAlbums.add(new AllAlbum("Bach", "Toccata in D Minor"));
        allAlbums.add(new AllAlbum("Beethoven", "Symphony No 5"));
        allAlbums.add(new AllAlbum("Handel", "Aleluia Messiah"));
        allAlbums.add(new AllAlbum("Kevin MacLeod", "If I Had a Chicken"));
        allAlbums.add(new AllAlbum("Scott Joplin", "The Entertainer"));

        //Bensound
        allAlbums.add(new AllAlbum("Bensound", "Buddy"));
        allAlbums.add(new AllAlbum("Bensound", "Cute"));
        allAlbums.add(new AllAlbum("Bensound", "Happiness"));
        allAlbums.add(new AllAlbum("Bensound", "Sunny"));
        allAlbums.add(new AllAlbum("Bensound", "Ukulele"));




        ListView listView = (ListView) findViewById(R.id.list_albums);
        listView.setBackgroundColor(getResources().getColor(R.color.home_background));



        // Create AllSongAdapter object to display listview
        AllAlbumAdapter adapter = new AllAlbumAdapter(this, allAlbums);
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