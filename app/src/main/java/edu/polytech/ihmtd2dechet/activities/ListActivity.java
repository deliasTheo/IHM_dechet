package edu.polytech.ihmtd2dechet.activities;

import static android.app.PendingIntent.getActivity;

import static edu.polytech.ihmtd2dechet.applications.NotificationApplication.*;

import android.widget.ListView;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.adapter.ReportAdapter;

import edu.polytech.ihmtd2dechet.objects.Notification;
import edu.polytech.ihmtd2dechet.objects.ReportsList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ReportAdapter adapter = new ReportAdapter(getApplicationContext(), ReportsList.getInstance().get(), getLayoutInflater());

        ListView listView = findViewById(R.id.liste_signalement);

        listView.setAdapter(adapter);
    }

}