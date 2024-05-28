package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.NotificationApplication.REPORTING_CHANNEL;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.adapter.NotificationAdapter;
import edu.polytech.ihmtd2dechet.applications.NotificationApplication;
import edu.polytech.ihmtd2dechet.objects.Guide;
import edu.polytech.ihmtd2dechet.objects.GuideFactory;
import edu.polytech.ihmtd2dechet.objects.Notification;

public class NotificationActivity extends AppCompatActivity {

    NotificationAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        this.adapter = new NotificationAdapter(NotificationApplication.getNotificationList(), getLayoutInflater(), getApplicationContext());
        this.listView = findViewById(R.id.notification_list);
        this.listView.setAdapter(this.adapter);

        findViewById(R.id.event_channel).setOnClickListener(click ->
        {
            final String[] items = {"Tout", "Canal des évènements", "Canal des déchets"};

            AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
            builder.setTitle("Filtrer");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    NotificationApplication.Filter filter;
                    switch (which) {
                        case 1:
                            filter = NotificationApplication.Filter.EVENT_CHANNEL;
                            break;
                        case 2:
                            filter = NotificationApplication.Filter.REPORTING_CHANNEL;
                            break;
                        default:
                            filter = NotificationApplication.Filter.ALL;
                    }
                    adapter = new NotificationAdapter(NotificationApplication.getNotificationList(filter), getLayoutInflater(), getApplicationContext());
                    listView.setAdapter(adapter);
                }
            });
            builder.show();
        });


        findViewById(R.id.notification).setOnClickListener(click -> {
            Notification notification = new Notification(REPORTING_CHANNEL, NotificationCompat.PRIORITY_DEFAULT, R.drawable.logo_dechets, "Titre de la notification", "Message de la notification");
            NotificationApplication.sendNotification(this, this, notification);
        });
    }

}