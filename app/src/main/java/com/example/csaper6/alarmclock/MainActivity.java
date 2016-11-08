package com.example.csaper6.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    private TimePicker timePicker;
    private Switch switch_sun;
    private Switch switch_mon;
    private Switch switch_tues;
    private Switch switch_wed;
    private Switch switch_thurs;
    private Switch switch_fri;
    private Switch switch_sat;
    private CheckBox checkBox_weekly;
    private Button button_cancel;
    private Button button_save;
    private Button button_alarmSounds;
    private long time;
    private int hours;
    private int minutes;
    private int timeNow;
    private int alarmTime;
    private long timeLeft1;
    private int songNumber;
    public static final String HOURS_NAME ="hours";
    public static final String MINUTES_NAME ="minutes";
    public static final String ALARM_CHOICE = "";
    public static final int REQUEST_SONG=0;
    private int alarmNumber;
    private int alarmChoice;


    private boolean[] day_of_week;



    /**
     switch_sun.isChecked(),
     switch_mon.isChecked(),
     switch_tues.isChecked(),
     switch_wed.isChecked(),
     switch_thurs.isChecked(),
     switch_fri.isChecked(),
     switch_sat.isChecked()
     **/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wireWidgets();
        day_of_week = new boolean[]{
                switch_sun.isChecked(),
                switch_mon.isChecked(),
                switch_tues.isChecked(),
                switch_wed.isChecked(),
                switch_thurs.isChecked(),
                switch_fri.isChecked(),
                switch_sat.isChecked()
        };




        button_save.setOnClickListener(new View.OnClickListener(){
                                           @Override
                                           public void onClick(View view){
                                               //Context context = MainActivity.this;
                                               //PendingIntent.FLAG_UPDATE_CURRENT
                                               //Intent alarmPage = new Intent(MainActivity.this, AlarmPage.class);
                                               /**
                                                setTime();
                                                AlarmManager alarm = (AlarmManager)(MainActivity.this.getSystemService(Context.ALARM_SERVICE));
                                                Intent intent = new Intent(MainActivity.this, myReceiver.class);
                                                intent.putExtra(HOURS_NAME, hours);
                                                intent.putExtra(MINUTES_NAME, minutes);
                                                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 123, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                                                //alarm.setExact(AlarmManager.RTC_WAKEUP,timeLeft, PendingIntent.getBroadcast(MainActivity.this,123,alarmPage,PendingIntent.FLAG_UPDATE_CURRENT));
                                                alarm.setExact(AlarmManager.RTC_WAKEUP,timeLeft1, pendingIntent);

                                                Toast.makeText(MainActivity.this, "Your alarm has been saved", Toast.LENGTH_SHORT).show();

                                                **/



                                               AlarmManager alarm = (AlarmManager)(MainActivity.this.getSystemService(Context.ALARM_SERVICE));
                                               Intent intent = new Intent(MainActivity.this, myReceiver.class);
                                               hours = timePicker.getHour();
                                               minutes = timePicker.getMinute();
                                               intent.putExtra(HOURS_NAME, hours);
                                               intent.putExtra(MINUTES_NAME, minutes);
                                               intent.putExtra(AlarmSounds.ALARM_CHOICE, alarmNumber);
                                               PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 123, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                                               setTime();
                                               alarm.set(AlarmManager.RTC_WAKEUP,timeLeft1, pendingIntent);
                                               Toast.makeText(MainActivity.this, "Your alarm has been saved", Toast.LENGTH_SHORT).show();


                                               /**
                                                * public class AlarmReceiver extends BroadcastReceiver {
                                                public void onReceive(Context context, Intent intent){
                                                Toast.makeText(context, "Recieved!!", Toast.LENGTH_LONG).show();

                                                Activity act = new Activity();
                                                act.startActivity(intent);
                                                }
                                                }
                                                **/



                                           }
                                       }
        );

        button_alarmSounds.setOnClickListener(new View.OnClickListener(){
                                                  @Override
                                                  public void onClick(View view) {

                                                      Intent alarmSounds = new Intent(MainActivity.this, AlarmSounds.class);
                                                      startActivityForResult(alarmSounds, REQUEST_SONG);

                                                  }
                                              }
        );





        button_cancel.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 AlarmManager alarm = (AlarmManager)(MainActivity.this.getSystemService(Context.ALARM_SERVICE));
                                                 Intent alarmPage = new Intent(MainActivity.this, AlarmPage.class);
                                                 PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 123, alarmPage, PendingIntent.FLAG_UPDATE_CURRENT);
                                                 alarm.cancel(pendingIntent);
                                                 Toast.makeText(MainActivity.this, "Your alarm has been cancelled", Toast.LENGTH_SHORT).show();
                                             }
                                         }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //result code says what happened. data is what gets passed back to us
        //TODO: do something with the data that gets passed back
        if(resultCode == RESULT_OK && requestCode == REQUEST_SONG){
            //TODO extract the information from the intent "data"
            //now do something with it
            //true if they cheated. have this in the cheat activity
            //but not in this one
            //use getExtra to extract information from the intent "data"
            alarmChoice =data.getIntExtra(ALARM_CHOICE,0);
            Intent intent = new Intent(MainActivity.this, AlarmPage.class);
            intent.putExtra(ALARM_CHOICE, alarmChoice);

            //this needs to be outside of the onCreate



        }
    }

    /**
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
     if (resultCode== RESULT_OK && requestCode == REQUEST_SONG){
     songNumber = data.getIntExtra(ALARM_CHOICE, 1);
     }
     }
     **/

    private void setTime() {
        /**
         hours = timePicker.getHour();
         if (hours==12)
         {
         hours = 0;
         }
         minutes = timePicker.getMinute();
         alarmTime = (hours*3600000 + minutes*60000);
         timeNow = (hoursNow*3600000 + minutesNow*60000);
         timeLeft1 = (long)(alarmTime);

         Calendar calendar = Calendar.getInstance();
         calendar.add(hoursNow, minutesNow);

         hours = timePicker.getHour();
         hours = hours -12;
         if (hours==12)
         {
         hours = 0;
         }
         minutes = timePicker.getMinute();
         alarmTime = (hours*3600000 + minutes*60000);
         Calendar calendar = Calendar.getInstance();
         calendar.add(hoursNow, minutesNow);
         timeLeft1 = (long)(System.currentTimeMillis()+(alarmTime-calendar.getTimeInMillis()));
         Toast.makeText(MainActivity.this, "" +timeLeft1, Toast.LENGTH_SHORT).show();
         **/



        Calendar c = Calendar.getInstance();
        timeNow = c.get(Calendar.HOUR_OF_DAY)*60*60*1000 + c.get(Calendar.MINUTE)*60*1000;
        alarmTime = timePicker.getHour()*60*60*1000 + timePicker.getMinute()*60*1000;
        timeLeft1 = (long)(System.currentTimeMillis() + (alarmTime-timeNow));
        //Toast.makeText(MainActivity.this, ""+timeLeft1, Toast.LENGTH_SHORT).show();


    }

    private void wireWidgets() {
        timePicker = (TimePicker)(findViewById(R.id.timePicker));
        switch_sun = (Switch)(findViewById(R.id.switch_sun));
        switch_mon = (Switch)(findViewById(R.id.switch_mon));
        switch_tues = (Switch)(findViewById(R.id.switch_tues));
        switch_wed = (Switch)(findViewById(R.id.switch_wed));
        switch_thurs = (Switch)(findViewById(R.id.switch_thurs));
        switch_fri = (Switch)(findViewById(R.id.switch_fri));
        switch_sat = (Switch)(findViewById(R.id.switch_sat));
        checkBox_weekly = (CheckBox)(findViewById(R.id.checkBox_weekly));
        button_cancel = (Button)(findViewById(R.id.button_cancel));
        button_save = (Button) (findViewById(R.id.button_save));
        button_alarmSounds = (Button)(findViewById(R.id.button_alarmSounds));

    }



}
