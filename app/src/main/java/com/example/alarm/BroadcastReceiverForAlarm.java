package com.example.alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.alarm.dummy.Test;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BroadcastReceiverForAlarm extends BroadcastReceiver {
    final public static String ONE_TIME = "onetime";
    public static int value = 5;
    public static int requestCode = 1;

    Context myContext;


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm is null", Toast.LENGTH_SHORT).show();



        myContext = context;


        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp: будильник");
//Осуществляем блокировку
        wl.acquire();
        Toast.makeText(context,"55", Toast.LENGTH_SHORT).show();
        context.startService(new Intent(context, ServiceForAlarm.class));
//Разблокируем поток.
        wl.release();
    }




    public void CancelAlarm(Context context) {
        ServiceForAlarm.mediaPlayer.stop();
        context.stopService(new Intent(context, ServiceForAlarm.class));
    }

    public void setOnetimeTimer(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastReceiverForAlarm.class);
        intent.putExtra(ONE_TIME, Boolean.TRUE);//Задаем параметр интента
        PendingIntent pi = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + value * 1000, pi);
        } else am.setWindow(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + value * 1000, 100, pi);
        Toast.makeText(context,"66", Toast.LENGTH_SHORT).show();
    }



    public void setAlarm(Context context, long time) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastReceiverForAlarm.class);
        intent.putExtra(ONE_TIME, Boolean.TRUE);//Задаем параметр интента
        PendingIntent pi = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pi);
        } else am.setWindow(AlarmManager.RTC_WAKEUP, time, 100, pi);
        Toast.makeText(context,"66", Toast.LENGTH_SHORT).show();
    }
}







