package com.example.csaper6.alarmclock;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlarmPage extends AppCompatActivity {
    private MediaPlayer[] alarms;
    private MediaPlayer circleOfLife;
    private MediaPlayer minion;
    private int hours;
    private int minutes;
    private TextView textView_time;
    private int songChoice;
    private Button button_stop;




   // public static int songChoice = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmpage);
        button_stop = (Button) (findViewById(R.id.button_stop));


        Intent alarmPage = getIntent();
        hours = alarmPage.getIntExtra(MainActivity.HOURS_NAME, 0);
        minutes = alarmPage.getIntExtra(MainActivity.MINUTES_NAME, 0);
       //Toast.makeText(this, ""+hours, Toast.LENGTH_SHORT).show();

        textView_time = (TextView) findViewById(R.id.textView_time);
        if (hours > 12) {
            hours = hours - 12;
        }
        if(minutes==0){
            textView_time.setText(hours + ":" + minutes+"0");
        }
        else {
            textView_time.setText(hours + ":" + minutes);
        }
        loadMediafiles();
        alarms = new MediaPlayer[]{circleOfLife, minion};
//        Intent alarmPage1 = getIntent();
        songChoice = alarmPage.getIntExtra(MainActivity.ALARM_CHOICE, 0);




        playSong(songChoice);


        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarms[0].stop();
                finish();
                //TODO: stop the right alarm from playing
            }
        });
    }

    private void playSong(int a) {
        switch(a){
            case 0:
                circleOfLife.seekTo(0);
                circleOfLife.start();
                break;
            case 1:
                minion.seekTo(0);
                minion.start();
        }


    }

    private void loadMediafiles() {
        circleOfLife = MediaPlayer.create(this,R.raw.circle_of_life);
        minion = MediaPlayer.create(this, R.raw.minion);
    }







}
