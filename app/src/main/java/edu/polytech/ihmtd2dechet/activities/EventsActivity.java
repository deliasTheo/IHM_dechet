package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import edu.polytech.ihmtd2dechet.objects.ControllerEvent;
import edu.polytech.ihmtd2dechet.objects.Event;
import edu.polytech.ihmtd2dechet.objects.EventListModel;
import edu.polytech.ihmtd2dechet.objects.EventView;

public class EventsActivity extends AppCompatActivity {

    private static final String TAG = "EventsActivity";
    private ListView listView;
    private ArrayList<Event> listEvent;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        onViewCreated( findViewById(R.id.view_event) );

        listView = findViewById(R.id.list_events);
        listEvent = new ArrayList<>();
        listView.setAdapter(adapter);

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
    public <T extends ViewGroup> void onViewCreated(T layout){
        EventView view = new EventView(layout); // todo
        EventListModel model = new EventListModel();    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        ControllerEvent controller = new ControllerEvent(view, model);
        adapter = new EventAdapter(getApplicationContext(), controller, model, view);
        model.populate(getApplicationContext(), adapter);
        view.setAdapter(adapter);
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setController(controller);

        ListView listView = layout.findViewById(R.id.list_events);
        listView.setAdapter(adapter);
    }

}
