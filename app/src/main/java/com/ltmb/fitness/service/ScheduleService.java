package com.ltmb.fitness.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ltmb.fitness.boardcast.MyBroadcastReceiver;

public class ScheduleService {
    private final Context context;

    public ScheduleService(Context context) {
        this.context = context;
    }

    private void setAlarm(long triggerAtMillis, PendingIntent alarmIntent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, alarmIntent);
    }

    public void cancelAlarm(PendingIntent alarmIntent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(alarmIntent);
    }

    public PendingIntent createPendingIntent(int requestCode, Intent intent, int flags) {
        return PendingIntent.getBroadcast(context, requestCode, intent, flags);
    }

    public void setRemindWork(long triggerAtMillis) {
        Intent intent = new Intent(this.context, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        this.setAlarm(triggerAtMillis, pendingIntent);
    }
}
