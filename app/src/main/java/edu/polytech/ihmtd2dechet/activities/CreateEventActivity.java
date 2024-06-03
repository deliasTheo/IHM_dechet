package edu.polytech.ihmtd2dechet.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
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

import java.util.Calendar;
import java.util.Locale;

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
            String value_start_time = ((EditText)findViewById(R.id.input_start_time)).getText().toString();
            String value_end_time = ((EditText)findViewById(R.id.input_end_time)).getText().toString();

            if (value_title.length() != 0 && value_date.length() != 0 && value_lieu.length() != 0) {
                // Créer un nouvel objet Event avec les valeurs saisies
                Event newEvent = new Event(value_title, value_description, value_date, value_lieu, value_start_time, value_end_time);

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

        EditText inputDate = findViewById(R.id.input_date);

// OnClickListener pour inputDate
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenez la date actuelle pour initialiser le DatePickerDialog
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Créer et afficher le DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEventActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                                // Mettez à jour le texte de l'EditText avec la date sélectionnée
                                String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDayOfMonth, selectedMonth + 1, selectedYear);
                                inputDate.setText(formattedDate);
                            }
                        }, year, month, dayOfMonth);

                // Affichez le DatePickerDialog
                datePickerDialog.show();
            }
        });


        EditText inputStartTime = findViewById(R.id.input_start_time);
        EditText inputEndTime = findViewById(R.id.input_end_time);

// OnClickListener pour inputStartTime
        inputStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenez l'heure actuelle pour initialiser le TimePickerDialog
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Créer et afficher le TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                                // Mettez à jour le texte de l'EditText avec l'heure sélectionnée
                                String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                                inputStartTime.setText(time);
                            }
                        }, hour, minute, true);

                // Affichez le TimePickerDialog
                timePickerDialog.show();
            }
        });

// OnClickListener pour inputEndTime
        inputEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenez l'heure actuelle pour initialiser le TimePickerDialog
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                // Créer et afficher le TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEventActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                                // Mettez à jour le texte de l'EditText avec l'heure sélectionnée
                                String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                                inputEndTime.setText(time);
                            }
                        }, hour, minute, true);

                // Affichez le TimePickerDialog
                timePickerDialog.show();
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
        ((EditText)findViewById(R.id.input_start_time)).setText("");
        ((EditText)findViewById(R.id.input_end_time)).setText("");
    }
}
