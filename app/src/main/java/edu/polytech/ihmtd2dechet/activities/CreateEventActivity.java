package edu.polytech.ihmtd2dechet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.applications.NotificationApplication;
import edu.polytech.ihmtd2dechet.objects.Event;
import edu.polytech.ihmtd2dechet.objects.Notification;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.VALUE_DATE;
import static edu.polytech.ihmtd2dechet.applications.EventApplication.VALUE_DESCRIPTION;
import static edu.polytech.ihmtd2dechet.applications.EventApplication.VALUE_LOCATION;
import static edu.polytech.ihmtd2dechet.applications.EventApplication.VALUE_TITLE;

public class CreateEventActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);

        // Initialiser Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Event");

        ((Button)findViewById(R.id.bouton_retour)).setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
            startActivity(intent);
        });

        ((Button)findViewById(R.id.bouton_valider)).setOnClickListener(click -> {
            String value_title = ((EditText)findViewById(R.id.input_title)).getText().toString();
            String value_description = ((EditText)findViewById(R.id.input_description)).getText().toString();
            String value_date = ((EditText)findViewById(R.id.input_date)).getText().toString();
            String value_lieu = ((EditText)findViewById(R.id.input_location)).getText().toString();

            if (value_title.length() != 0 && value_date.length() != 0 && value_lieu.length() != 0) {
                // Créer un nouvel objet Event avec les valeurs saisies
                Event newEvent = new Event(value_title, value_description, value_date, value_lieu);

                // Ajouter l'événement à Firebase Database
                databaseReference.push().setValue(newEvent).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Événement créé avec succès", Toast.LENGTH_SHORT).show();

                        NotificationApplication.sendNotification(getApplicationContext(), this, new Notification(NotificationApplication.EVENT_CHANNEL, NotificationCompat.PRIORITY_DEFAULT, R.drawable.logo_events, "Nouvel évènement ajouté", value_title + "\n\n" + value_description + "\n\n" + value_date + ", " + value_lieu + "."));

                        Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Erreur lors de la création de l'événement", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Remplir tous les champs SVP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((EditText)findViewById(R.id.input_title)).setText("");
        ((EditText)findViewById(R.id.input_description)).setText("");
        ((EditText)findViewById(R.id.input_date)).setText("");
        ((EditText)findViewById(R.id.input_location)).setText("");
    }
}
