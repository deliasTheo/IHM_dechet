package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.Event;

public class OneEventActivity extends AppCompatActivity {

    private Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_event);

        event = getIntent().getParcelableExtra(EVENT);

        ((TextView)findViewById(R.id.title_page_one_event)).setText(event.getTitle());
        ((TextView)findViewById(R.id.one_event_description)).setText(event.getDescription());
        ((TextView)findViewById(R.id.one_event_date)).setText(event.getDate());
        ((TextView)findViewById(R.id.one_event_location)).setText(event.getLocation());
        ((ImageView)findViewById(R.id.image_one_event)).setBackgroundResource(event.getImage());

        ((Button)findViewById(R.id.bouton_retour)).setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
            startActivity(intent);
        });

        ((Button)findViewById(R.id.bouton_Supprimer)).setOnClickListener(click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Suppression de l'événement");
            builder.setMessage("Voulez-vous vraiment supprimer cet événement ?");
            builder.setPositiveButton("Oui", (dialog, which) -> {
                event.delete();
                Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                startActivity(intent);
            });
            builder.setNegativeButton("Non", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();
        });

    }
}