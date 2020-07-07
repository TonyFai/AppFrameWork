package com.dxtdkwt.zzh.appframework.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.dxtdkwt.zzh.appframework.R;

public class RemoteActivity extends AppCompatActivity {

    private static final String TAG = "RemoteActivity";
    public static final String EXTRA_REMOTE_VIEWS = "extraRemoteViews";
    public static final String REMOTE_ACTION = "remoteAction";

    private LinearLayout mRemoteViewsContent;

    private BroadcastReceiver mRemoteViewsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews remoteViews = intent.getParcelableExtra(EXTRA_REMOTE_VIEWS);
            if (remoteViews != null){
                updateUI(remoteViews);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        initView();
    }

    private void initView() {
        mRemoteViewsContent = findViewById(R.id.remote_views_content);
        IntentFilter filter = new IntentFilter(REMOTE_ACTION);
        registerReceiver(mRemoteViewsReceiver,filter);
    }

    private void updateUI(RemoteViews remoteViews){
        View view = remoteViews.apply(this, mRemoteViewsContent);
        mRemoteViewsContent.addView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRemoteViewsReceiver);
    }

    public void b(){
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.activity_notification);
        remoteViews.setTextViewText(R.id.msg,"msg from process:"+ Process.myPid());
        remoteViews.setImageViewResource(R.id.icon,R.mipmap.ic_launcher);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, ViewActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, DataTestActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.msg,pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.icon,openActivity2PendingIntent);
        Intent intent = new Intent(REMOTE_ACTION);
        intent.putExtra(EXTRA_REMOTE_VIEWS,remoteViews);
        sendBroadcast(intent);
    }
}