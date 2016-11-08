package com.example.csaper6.alarmclock;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlarmSounds extends AppCompatActivity {


    private MediaPlayer[] alarms;
    private MediaPlayer circleOfLife;
    private MediaPlayer minion;
    private Button button_circle;
    private Button button_minion;
    private String alarmChoice;
    public static final String ALARM_CHOICE = "alarmchoice";
    public static final int REQUEST_SONG=0;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmsounds);
        loadMediafiles();
        wireWidgets();
        alarms = new MediaPlayer[]{circleOfLife, minion};
        Intent alarmSounds = getIntent();

        button_circle.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {

                                                 Intent intent = new Intent();
                                                 intent.putExtra(ALARM_CHOICE, 0);
                                                 //AlarmPage.songChoice = 0;
                                                 setResult(RESULT_OK, intent);
                                                 Toast.makeText(AlarmSounds.this, "You have selected Circle of Life", Toast.LENGTH_SHORT).show();
                                                 finish();


                                             }
        }
        );

        button_minion.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {

                                                 Intent intent = new Intent();
                                                 intent.putExtra(ALARM_CHOICE, 1);
                                                 setResult(RESULT_OK, intent);
                                                // AlarmPage.songChoice = 1;
                                                 Toast.makeText(AlarmSounds.this, "You have selected the Minion Song", Toast.LENGTH_SHORT).show();
                                                 finish();

                                             }
                                         }
        );

    }

    private void wireWidgets() {
        button_circle = (Button) (findViewById(R.id.button_circle));
        button_minion = (Button) (findViewById(R.id.button_minion));
    }

    private void loadMediafiles() {
        circleOfLife = MediaPlayer.create(this, R.raw.circle_of_life);
        minion = MediaPlayer.create(this, R.raw.minion);
    }
}
