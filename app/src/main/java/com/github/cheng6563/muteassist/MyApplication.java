package com.github.cheng6563.muteassist;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

public class MyApplication extends Application {
    public static final String LAST_HANDLE_TIME = "LAST_HANDLE_TIME";
    public static MyApplication INSTANCE;

    public MyApplication() {
        INSTANCE = this;
    }

    public synchronized void switchMute(Context context) {
        if(context == null){
            context = getBaseContext();
        }
        SharedPreferences mSettings = context.getSharedPreferences(getClass().getName(), 0);
        long lastHandleTime = mSettings.getLong(LAST_HANDLE_TIME, 0);
        if (System.currentTimeMillis() > lastHandleTime + 1000) {
            SharedPreferences.Editor edit = mSettings.edit();
            edit.putLong(LAST_HANDLE_TIME, System.currentTimeMillis());
            edit.apply();

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (!notificationManager.isNotificationPolicyAccessGranted()) {
                Intent intent = new Intent(
                        android.provider.Settings
                                .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                context.startActivity(intent);
                Toast.makeText(getApplicationContext(), R.string.toast_grant, Toast.LENGTH_SHORT).show();
            } else {
                AudioManager audioManager =
                        (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                try {
                    int ringerMode = audioManager.getRingerMode();
                    Log.i("MyApplication", "current ringerMode: " + ringerMode);
                    if(ringerMode == AudioManager.RINGER_MODE_NORMAL) {
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    } else {
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    }
                } catch (Exception e) {
                    Log.e("MyApplication", "switchMute error", e);
                }
            }

            Log.i("MyApplication", "switchMute " + lastHandleTime);
        }

    }
}
