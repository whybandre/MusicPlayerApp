package com.example.android.musicplayerapp;

/**
 * Created by Bruno André on 04/04/2018.
 */


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MediaPlayerActivity extends AppCompatActivity implements Runnable,
        View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    // UI Components
    private SeekBar mSeekBar;
    private ImageButton mStartMedia;
    private ImageButton mStopMedia;
    private MediaPlayer mMediaPlayer;
    private TextView mTextSong;
    private TextView mTextSinger;
    private ImageView mImageArtwork;
    private TextView elapsedTimeLabel;
    private TextView remainingTimeLabel;
    private int totalTime;

    // Various identifiers
    private String mIntentMessage;
    private String mSong;
    private String mSinger;
    private String mSongResource;
    private String mPictureResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String [] intentMsgArray = new String[3];

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);



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

                            case R.id.action_home:
                                Intent intent1 = new Intent(MediaPlayerActivity.this, MainActivity.class);
                                startActivity(intent1);
                                break;

                            case R.id.action_album:
                                Intent intent2 = new Intent(MediaPlayerActivity.this, AllAlbumListActivity.class);
                                startActivity(intent2);
                                break;

                            case R.id.action_song:
                                Intent intent3 = new Intent(MediaPlayerActivity.this, AllSongListActivity.class);
                                startActivity(intent3);
                                break;
                        }

                        return false;
                    }
                });

//End---------->Bottom Navigation Bar with Activities<-------------



        Bundle bundle = getIntent().getExtras();
        mIntentMessage = bundle.getString("message");

        // Splits intent message received into activity_category_song_list, singer, and activity_category_song_list category
        intentMsgArray = mIntentMessage.split("\\|");
        mSong = intentMsgArray[0];
        mSinger = intentMsgArray[1];

        // Initialize UI Components
        mTextSong = (TextView) findViewById(R.id.text_song);
        mTextSinger = (TextView) findViewById(R.id.text_singer);
        mImageArtwork = (ImageView) findViewById(R.id.image_picture);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar_media);
        mStartMedia = (ImageButton) findViewById(R.id.imagebutton_play_pause);
        mStopMedia = (ImageButton) findViewById(R.id.imagebutton_stop);
        elapsedTimeLabel = (TextView) findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = (TextView) findViewById(R.id.remainingTimeLabel);


        // Set OnClickListeners on clickable items
        mStartMedia.setOnClickListener(this);
        mStopMedia.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setEnabled(false);
        mSeekBar.setMax(totalTime);


        // Get activity_category_song_list resource name
        getSongResource();

        // Get artwork resource name
        getPictureResource();

        // Display Media Details and Image
        displayMediaDetails();


    }
    /**
     * This method gets activity_category_song_list resource name of the activity_category_song_list selected
     */
    public void getSongResource() {

        mSongResource = mSinger.replace("'", "").toLowerCase()+ "_";
        mSongResource += mSong.replace("'", "").toLowerCase();
        mSongResource = mSongResource.replaceAll(" ", "_");
    }

    /**
     * This method gets artwork drawable name of the activity_category_song_list selected
     */
    public void getPictureResource() {

            mPictureResource = "picture__";
            mPictureResource += mSinger.replace("'", "").toLowerCase();
            mPictureResource = mPictureResource.replaceAll(" ", "_");
        }


    /**
     * This method displays Media details - activity_category_song_list title, singer name, picture
     */
    public void displayMediaDetails() {
        mTextSong.setText(mSong);
        mTextSinger.setText(mSinger);

        int resId = getResources().getIdentifier(mPictureResource, "drawable", getPackageName());
        mImageArtwork.setImageResource(resId);
    }

    /**
     * This method identifies progress while media is playing and sets position on SeekBar
     */
    public void run() {

        while (mMediaPlayer != null) {
            try {
                Message msg = new Message();
                msg.what = mMediaPlayer.getCurrentPosition();
                handler.sendMessage(msg);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }

        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            mSeekBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);

            return true;
        }
    });

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }



    /**
     * This method handles playing, pausing or stopping media file
     */
    public void onClick(View v) {

        if (v.equals(mStartMedia)) {
            if (mMediaPlayer == null) {
                int resId = getResources().getIdentifier(mSongResource, "raw", getPackageName());
                mMediaPlayer = MediaPlayer.create(MediaPlayerActivity.this, resId);
                mSeekBar.setEnabled(true);
                totalTime = mMediaPlayer.getDuration();

            }
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
                mStartMedia.setBackground(ContextCompat.getDrawable(MediaPlayerActivity.this, R.drawable.play_circle_outline));
                mSeekBar.setEnabled(true);
                totalTime = mMediaPlayer.getDuration();


            } else {
                mMediaPlayer.start();
                mStartMedia.setBackground(ContextCompat.getDrawable(MediaPlayerActivity.this, R.drawable.pause_circle_outline));
                mSeekBar.setMax(mMediaPlayer.getDuration());
                mSeekBar.setEnabled(true);
                totalTime = mMediaPlayer.getDuration();

                new Thread(this).start();
            }
        }

        if (v.equals(mStopMedia) && mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying() || mMediaPlayer.getDuration() > 0) {
                mMediaPlayer.stop();
                mMediaPlayer = null;
                mSeekBar.setProgress(0);
                MediaPlayerActivity.this.finish();
            }
        }

}







    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        try {
            if (mMediaPlayer.isPlaying() || mMediaPlayer != null) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                    mSeekBar.setProgress(progress);
                }
            } else if (mMediaPlayer == null) {
                Toast.makeText(getApplicationContext(), "Media is not running",
                        Toast.LENGTH_SHORT).show();
                seekBar.setProgress(0);
            }
        } catch (Exception e) {
            Log.e("MediaPlayerActivity", "SeekBar not responding" + e);
            seekBar.setEnabled(false);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
}