package edu.polytech.ihmtd2dechet;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class NotificationApplication extends Application {

    public static final String REPORTING_CHANNEL = "reporting channel";
    public static final String EVENT_CHANNEL = "event channel";


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }


    private void createNotificationChannels() {
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            NotificationManager manager = getSystemService(NotificationManager.class);

            NotificationChannel eventChannel = new NotificationChannel(EVENT_CHANNEL, EVENT_CHANNEL, NotificationManager.IMPORTANCE_LOW);
            eventChannel.setDescription("This channel is used to send notification about upcoming events.");
            Objects.requireNonNull(manager).createNotificationChannel(eventChannel);

            NotificationChannel reportingChannel = new NotificationChannel(REPORTING_CHANNEL, REPORTING_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            reportingChannel.setDescription("This channel is used to send notification about new toxic waste reports");
            Objects.requireNonNull(manager).createNotificationChannel(reportingChannel);
        }
    }

}
