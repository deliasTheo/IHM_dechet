package edu.polytech.ihmtd2dechet;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;
import static edu.polytech.ihmtd2dechet.NotificationApplication.*;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViewById(R.id.notification ).setOnClickListener(click -> {
            String title = "Titre de la notification";
            String message = "Message de la notification";
            sendNotificationOnChannel(getContext(), getActivity(), title, message, REPORTING_CHANNEL, NotificationCompat.PRIORITY_DEFAULT);
        });
    }

}