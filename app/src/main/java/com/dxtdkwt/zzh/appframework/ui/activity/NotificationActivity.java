package com.dxtdkwt.zzh.appframework.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.dxtdkwt.zzh.appframework.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = new Intent(this, ViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.activity_notification);
        remoteViews.setTextViewText(R.id.msg,"chapter");
        remoteViews.setImageViewResource(R.id.icon,R.mipmap.ic_launcher);
        remoteViews.setOnClickPendingIntent(R.id.open_activity,pendingIntent);


        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
//                .setContentText("你好世界")
                .setTicker("hello world")
                .setContentIntent(pendingIntent)
//                .setCustomBigContentView(remoteViews)
                .build();

        notification.contentView = remoteViews;

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,notification);

    }
}