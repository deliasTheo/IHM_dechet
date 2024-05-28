package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.adapter.EventAdapter;
import edu.polytech.ihmtd2dechet.objects.Event;

public class EventsActivity extends AppCompatActivity {

    private static final String TAG = "EventsActivity";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ListView listView;
    private ArrayList<Event> listEvent;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        listView = findViewById(R.id.list_events);
        listEvent = new ArrayList<>();
        adapter = new EventAdapter(this, listEvent);
        listView.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Event");

        // Récupérer les données depuis Firebase
        getEventData();

        // Avant de démarrer OneEventActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtenir l'élément sélectionné à partir de l'adaptateur
                Event selectedEvent = (Event) adapter.getItem(position);

                // Créer un intent pour démarrer OneEventActivity
                Intent intent = new Intent(EventsActivity.this, OneEventActivity.class);
                // Passer les données de l'événement à OneEventActivity avec la même clé que dans OneEventActivity
                intent.putExtra(EVENT, selectedEvent);
                // Démarrer OneEventActivity
                startActivity(intent);
            }
        });

        Button createEventButton = findViewById(R.id.button_create_event);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un intent pour démarrer CreateEventActivity
                Intent intent = new Intent(EventsActivity.this, CreateEventActivity.class);
                // Démarrer CreateEventActivity
                startActivity(intent);
            }
        });
    }

    private void getEventData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listEvent.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    listEvent.add(event);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error fetching data", databaseError.toException());
                Toast.makeText(EventsActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
