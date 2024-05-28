package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.NotificationApplication.REPORTING_CHANNEL;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.adapter.NotificationAdapter;
import edu.polytech.ihmtd2dechet.applications.NotificationApplication;
import edu.polytech.ihmtd2dechet.objects.Notification;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        findViewById(R.id.notification).setOnClickListener(click -> {
            Notification notification = new Notification(REPORTING_CHANNEL, NotificationCompat.PRIORITY_DEFAULT, R.drawable.logo_dechets, "Titre de la notification", "Message de la notification");
            NotificationApplication.sendNotification(this, this, notification);
        });

        NotificationAdapter adapter = new NotificationAdapter(NotificationApplication.getNotificationList(), getLayoutInflater(), getApplicationContext());
        ListView listView = findViewById(R.id.notification_list);
        listView.setAdapter(adapter);
    }

}