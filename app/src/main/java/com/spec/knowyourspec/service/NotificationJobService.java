package com.spec.knowyourspec.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

import com.spec.knowyourspec.MainActivity;
import com.spec.knowyourspec.R;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {

    public static final String CHANNEL_ID = "channel_id";
    public static final CharSequence NOTIFICATION_CHANNEL_NAME = "Notification Channel";
    public static final int REQUEST_CODE = 0;
    public static final int NOTIFICATION_ID = 4;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //other ways of getting the notificationManager
        /*NotificationManager notificationManager = getSystemService(NotificationManager.class);
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);*/


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Spec of the day");
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(NotificationJobService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationJobService.this,
                REQUEST_CODE,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);
        // to add an action
        NotificationCompat.Action action = new NotificationCompat.Action(R.drawable.ic_share, "Share", pendingIntent);

        NotificationCompat.Builder notificationBuilder =  new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_person)
                .setContentTitle("Title")
                .setContentText("Content")
                .setContentIntent(pendingIntent)
                .addAction(action)
                .setAutoCancel(true);

        Notification notification = notificationBuilder.build();

        notificationManager.notify(NOTIFICATION_ID, notification);
        return false; // do you want to continue in the background -> false (Normally, you should
        // create a background task to do your work, but there is no long running work here)
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
