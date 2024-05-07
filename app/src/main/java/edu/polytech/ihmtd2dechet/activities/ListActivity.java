package edu.polytech.ihmtd2dechet.activities;

import static android.app.PendingIntent.getActivity;
import static edu.polytech.ihmtd2dechet.applications.NotificationApplication.*;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.WasteList;

public class ListActivity extends AppCompatActivity {

    private static final WasteList wasteList = WasteList.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViewById(R.id.notification ).setOnClickListener(click -> {
            String title = "Titre de la notification";
            String message = "Message de la notification";
            sendNotificationOnChannel(this, this, title, message, REPORTING_CHANNEL, NotificationCompat.PRIORITY_DEFAULT);
        });
    }

}