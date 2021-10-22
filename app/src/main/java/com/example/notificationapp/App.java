package com.example.notificationapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

public class App extends android.app.Application{

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    // starts before we start the activity
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels()
    {
        // check if higher than Oreo
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,
                    "channel 1",
                    NotificationManager.IMPORTANCE_HIGH);
            // create the settings as default user can change
            // user can see in the settings
            channel1.setDescription(" Channel 1 user for...");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,
                    "channel 2",
                    NotificationManager.IMPORTANCE_LOW);
            // create the settings as default user can change
            // user can see in the settigs

            channel2.setDescription(" Channel 2 user for...");

            manager.createNotificationChannel(channel2);

        }
    }
}
