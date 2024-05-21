package edu.polytech.ihmtd2dechet.applications;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Objects;

import edu.polytech.ihmtd2dechet.R;

public class NotificationApplication extends Application {

    public static final String REPORTING_CHANNEL = "reporting channel";
    public static final String EVENT_CHANNEL = "event channel";

    public static final int NOTIFICATION_REQUEST_CODE = 0;


    private static int notificationId = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }


    private void createNotificationChannels() {
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            NotificationManager manager = getSystemService(NotificationManager.class);

            NotificationChannel eventChannel = new NotificationChannel(EVENT_CHANNEL, EVENT_CHANNEL, NotificationManager.IMPORTANCE_DEFAULT);
            eventChannel.setDescription("This channel is used to send notification about upcoming events.");
            Objects.requireNonNull(manager).createNotificationChannel(eventChannel);

            NotificationChannel reportingChannel = new NotificationChannel(REPORTING_CHANNEL, REPORTING_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            reportingChannel.setDescription("This channel is used to send notification about new toxic waste reports");
            Objects.requireNonNull(manager).createNotificationChannel(reportingChannel);
        }
    }


    public static void sendNotificationOnChannel(Context context, Activity activity, String title, String content, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(priority);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_REQUEST_CODE);
        }
        NotificationManagerCompat.from(context).notify(notificationId++, notification.build());
    }

}
