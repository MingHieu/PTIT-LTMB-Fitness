package com.ltmb.fitness.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.ltmb.fitness.R;
import com.ltmb.fitness.scene.main.MainActivity;

import java.util.Date;

public class NotificationBase {
    /*
    - Tạo channel đầu tiên
    - Gửi Notification qua chanel id
     */

    private final String CHANNEL_ID = "CHANNEL_ID";
    private final String CHANNEL_NAME = "CHANNEL_NAME";
    private final String CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION";
    private final int NOTIFICATION_CODE = 100;

    @SuppressLint("InlinedApi")
    public void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Check Permission
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions( (Activity) context,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_CODE);
            }

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotification(Context context, String title, String content) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(content)
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .setBigContentTitle("Notification BigStyle Title")
//                        .bigText("Notification BigStyle Content"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

//        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.raw.icon_notification);
//        builder.setLargeIcon(largeIcon);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.notify((int) new Date().getTime(), builder.build());
    }
}
