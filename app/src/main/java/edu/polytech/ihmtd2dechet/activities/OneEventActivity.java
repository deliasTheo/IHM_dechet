package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.TimeZone;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.Event;

public class OneEventActivity extends AppCompatActivity {

    private Event event;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_event);

        // Récupérer l'événement depuis l'intent
        event = getIntent().getParcelableExtra(EVENT);

        // Vérifier si l'événement est null
        if (event == null) {
            Toast.makeText(this, "Erreur: Aucune donnée d'événement trouvée", Toast.LENGTH_SHORT).show();
            finish(); // Terminer l'activité si l'événement est null
            return;
        }

        // Initialiser la référence de la base de données
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Event").child(event.getUid());

        // Remplir les vues avec les données de l'événement
        ((TextView)findViewById(R.id.title_page_one_event)).setText(event.getTitle());
        ((TextView)findViewById(R.id.one_event_description)).setText(event.getDescription());
        ((TextView)findViewById(R.id.one_event_date)).setText(event.getDate());
        ((TextView)findViewById(R.id.one_event_location)).setText(event.getLocation());
        ((ImageView)findViewById(R.id.image_one_event)).setImageResource(event.getImage());
        ((TextView)findViewById(R.id.one_event_start_time)).setText(event.getStart_time());
        ((TextView)findViewById(R.id.one_event_end_time)).setText(event.getEnd_time());

        // Définir un OnClickListener pour le bouton de retour
        ((Button)findViewById(R.id.bouton_retour)).setOnClickListener(click -> {
            onBackPressed(); // Simuler le bouton de retour
        });

        // Définir un OnClickListener pour le bouton de suppression
        ((Button)findViewById(R.id.bouton_Supprimer)).setOnClickListener(click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Suppression de l'événement");
            builder.setMessage("Voulez-vous vraiment supprimer cet événement ?");
            builder.setPositiveButton("Oui", (dialog, which) -> {
                // Supprimer l'événement de la base de données
                deleteEvent();
            });
            builder.setNegativeButton("Non", (dialog, which) -> {
                dialog.dismiss(); // Fermer la boîte de dialogue
            });
            builder.show(); // Afficher la boîte de dialogue
        });

        ((Button)findViewById(R.id.bouton_importer)).setOnClickListener(click -> {
            // Appeler la méthode pour importer l'événement dans le calendrier
            importEventToCalendar(event);
        });
    }

    private void deleteEvent() {
        // Utiliser la référence pour supprimer l'événement
        databaseReference.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "L'événement a été supprimé", Toast.LENGTH_SHORT).show();
                // Retourner à l'activité des événements
                Intent intent = new Intent(OneEventActivity.this, EventsActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Erreur: La suppression a échoué", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void importEventToCalendar(Event event) {
        // Créer un Intent pour ajouter un événement au calendrier
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, event.getTitle())
                .putExtra(CalendarContract.Events.DESCRIPTION, event.getDescription())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, event.getLocation())
                .putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID())
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, getDateTimeInMillis(event.getDate(), event.getStart_time()))
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, getDateTimeInMillis(event.getDate(), event.getEnd_time()));

        // Lancer l'activité pour ajouter un événement au calendrier
        startActivity(intent);
    }

    private long getDateTimeInMillis(String date, String time) {
        // Parsez la date et l'heure pour obtenir l'objet Calendar
        Calendar calendar = Calendar.getInstance();
        String[] dateParts = date.split("/");
        String[] timeParts = time.split(":");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1; // Mois commence à 0
        int year = Integer.parseInt(dateParts[2]);
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        calendar.set(year, month, day, hour, minute);

        // Retourner le temps en millisecondes
        return calendar.getTimeInMillis();
    }
}
