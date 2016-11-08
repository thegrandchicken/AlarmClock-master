package com.example.csaper6.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class myReceiver extends BroadcastReceiver {

    public static final String HOURS_NAME ="hours";
    public static final String MINUTES_NAME ="minutes";
    private int numHours;
    private int numMin;

    public void onReceive(Context context, Intent intent){
        Intent i = new Intent (context, AlarmPage.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle extras = intent.getExtras();
        numHours = extras.getInt(HOURS_NAME);
        numMin = extras.getInt(MINUTES_NAME);

        i.putExtra(HOURS_NAME, numHours);
        i.putExtra(MINUTES_NAME, numMin);
        context.startActivity(i);




    }
}
