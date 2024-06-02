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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.Notification;

public class NotificationApplication extends Application {

    public enum Filter {
        ALL,
        REPORTING_CHANNEL,
        EVENT_CHANNEL
    }

    /* Attributes */

    /* Channels */

    public static final String REPORTING_CHANNEL = "Canal des déchets";
    public static final String EVENT_CHANNEL = "Canal des évènements";

    public static final int NOTIFICATION_REQUEST_CODE = 0;
    private static int notificationId = 0;

    /* Notification list */

    private static final List<Notification> notificationList = new ArrayList<>(
            Arrays.asList(
                    new Notification(REPORTING_CHANNEL, NotificationCompat.PRIORITY_MAX, R.drawable.logo_dechets,"Nouveau déchet toxique signalé", "Tôle en amiante trouvé sur le bords de la route.\n\n Déchet toxique, 89 Chemin de l'amiante."),
                    new Notification(EVENT_CHANNEL, NotificationCompat.PRIORITY_DEFAULT, R.drawable.logo_events,"Nouvel évènement ajouté", "Marche pour le climat.\n\n Et oui vous ne rêvez pas ! La marche du climat fait son grand retour !\n\n 26/07/2189, 188 rue de la chaussure."),
                    new Notification(EVENT_CHANNEL, NotificationCompat.PRIORITY_DEFAULT, R.drawable.logo_events,"Nouvel évènement ajouté", "Clean walk.\n\n Tout est dans le titre.\n\n 16/05/2024, Place de la mairie."),
                    new Notification(REPORTING_CHANNEL, NotificationCompat.PRIORITY_HIGH, R.drawable.logo_dechets,"Nouveau déchet encombrant signalé", "Un canapé abandonné au milieu de la place.\n\n Encombrant, Place du Moulin."),
                    new Notification(EVENT_CHANNEL, NotificationCompat.PRIORITY_DEFAULT, R.drawable.logo_events,"Nouvel évènement ajouté", "Collecte d'objets.\n\nCe mardi, une collecte d'objet est mise en place afin de leur offrir une nouvelle vie !\n\n 08/11/2032, Rue des objets d'occasion.")
            )
    );


    /* Constructor */

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


    /* Methods */

    /* Send notification */

    public static void sendNotification(Context context, Activity activity, Notification notification) {
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(context, notification.getChannel())
                .setPriority(notification.getPriority())
                .setSmallIcon(notification.getSmallIcon())
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getText())
                .setTimeoutAfter(90000);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_REQUEST_CODE);
        }

        NotificationManagerCompat.from(context).notify(notificationId++, notificationCompat.build());
        NotificationApplication.addNotificationToList(notification);
    }


    /* Notification list */

    public static List<Notification> getNotificationList() {
        return NotificationApplication.notificationList;
    }

    public static List<Notification> getNotificationList(Filter filter) {
        switch (filter) {
            case REPORTING_CHANNEL:
                List<Notification> reporting_res = new ArrayList<>();
                for (Notification notification : NotificationApplication.notificationList) {
                    if (notification.getChannel().compareTo(NotificationApplication.REPORTING_CHANNEL) == 0) {
                        reporting_res.add(notification);
                    }
                }
                return reporting_res;

            case EVENT_CHANNEL:
                List<Notification> event_res = new ArrayList<>();
                for (Notification notification : NotificationApplication.notificationList) {
                    if (notification.getChannel().compareTo(NotificationApplication.EVENT_CHANNEL) == 0) {
                        event_res.add(notification);
                    }
                }
                return event_res;

            default:
                return NotificationApplication.notificationList;
        }
    }


    public static void addNotificationToList(Notification notification) {
        NotificationApplication.notificationList.add(notification);
    }


    public static void removeNotificationFromList(Notification notification) {
        NotificationApplication.notificationList.remove(notification);
    }

}