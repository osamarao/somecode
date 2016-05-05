package io.clutchstud.nfems.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by a653h496 on 5/5/16.
 */
public class AlarmHandlingUtility {

    public static void setAlarm(Context context, int intervalInHours, Intent serviceIntent){
        Log.d("setAlarm", "IN!");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //Intent intent = new Intent(context, WeatherBroadcastReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalInHours * 60 * 60 * 60 * 1000, alarmIntent);

    }
}
