package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // wraps bacground compatibiltiy
    // cannot create channels
    private NotificationManagerCompat notificationManagerCompat;
    private EditText etTitle,etMessage;
    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat =  NotificationManagerCompat.from(this);
        initViews();
    }

    private void initViews() {
        etMessage = findViewById(R.id.editTextMessage);
        etTitle = findViewById(R.id.editTextTitle);
    }

    public void Channel1(View view) {
        counter++;
        String title = etTitle.getText().toString();
        String message = etMessage.getText().toString();

        // create pending intent to start activity
        Intent notifyIntent = new Intent(this,SecondActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                  | Intent.FLAG_ACTIVITY_CLEAR_TASK
              //  | Intent.FLAG_ACTIVITY_TASK_ON_HOME
                //| Intent.FLAG_ACTIVITY_CLEAR_TOP
        );
        // Create the PendingIntent
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_1_ID).
                // icon is the only mandatory field
                setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(Color.BLUE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //add the pending intent to notification builder
                .setContentIntent(notifyPendingIntent)
                .build();
        // Notification id stands for showing multiple notifications
        // at the same time
        // if we use the same ID it will override the
        // existing notification
        notificationManagerCompat.notify(counter,notification);


    }

    public void Channel2(View view) {
        counter++;
        String title = etTitle.getText().toString();
        String message = etMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_2_ID).
                setSmallIcon(R.drawable.two)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        // id stands for showing multiple notifications
        // at the same time
        // if we use the same ID it will override the
        // existing notification
        notificationManagerCompat.notify(counter,notification);

    }
}