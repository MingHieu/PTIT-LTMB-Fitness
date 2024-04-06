package com.ltmb.fitness.boardcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ltmb.fitness.service.NotificationService;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private NotificationService notificationService;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationService = new NotificationService(context);
        notificationService.createNotificationRemindWork();
    }
}
