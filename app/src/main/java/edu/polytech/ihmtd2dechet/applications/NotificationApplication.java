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
import edu.polytech.ihmtd2dechet.objects.Notification;

public class NotificationApplication extends Application {

    public static final String REPORTING_CHANNEL = "Canal des déchets";
    public static final String EVENT_CHANNEL = "Canal des évènements";

    public static final int NOTIFICATION_REQUEST_CODE = 0;

    private static int notificationId = 0;


    @Override
    public void onCreate() {
        super.onCreate();

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


    public static void sendNotification(Context context, Activity activity, Notification notification) {
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(context, notification.getChannel())
                .setPriority(notification.getPriority())
                .setSmallIcon(notification.getSmallIcon())
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getText());

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_REQUEST_CODE);
        }

        NotificationManagerCompat.from(context).notify(notificationId++, notificationCompat.build());
    }

}