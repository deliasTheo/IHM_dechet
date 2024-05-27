package edu.polytech.ihmtd2dechet.activities;

import static android.app.PendingIntent.getActivity;

import static edu.polytech.ihmtd2dechet.applications.NotificationApplication.*;

import android.app.AlertDialog;
import android.widget.ListView;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.adapter.ReportAdapter;

import edu.polytech.ihmtd2dechet.objects.Report;
import edu.polytech.ihmtd2dechet.objects.ReportsList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViewById(R.id.notification).setOnClickListener(click -> {
            String title = "Titre de la notification";
            String message = "Message de la notification";
            sendNotificationOnChannel(this, this, title, message, REPORTING_CHANNEL, NotificationCompat.PRIORITY_DEFAULT);
        });

        ReportAdapter adapter = new ReportAdapter(getApplicationContext(), ReportsList.getInstance().get(), getLayoutInflater());

        ListView listView = findViewById(R.id.liste_signalement);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Report report = (Report) adapter.getItem(position);
            showStatusDialog(report, adapter);
        });
    }

    private void showStatusDialog(Report report, ReportAdapter adapter) {
        String[] statuses = {"A faire", "En cours", "Finis"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisissez le statut")
                .setItems(statuses, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            report.setAdvancement("A faire");
                            break;
                        case 1:
                            report.setAdvancement("En cours");
                            break;
                        case 2:
                            report.setAdvancement("Finis");
                            break;
                    }
                    adapter.notifyDataSetChanged();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}