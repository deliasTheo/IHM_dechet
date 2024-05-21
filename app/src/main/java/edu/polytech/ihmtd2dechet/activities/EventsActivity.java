package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.adapter.EventAdapter;
import edu.polytech.ihmtd2dechet.fragments.MenuFragment;
import edu.polytech.ihmtd2dechet.objects.Event;
import edu.polytech.ihmtd2dechet.objects.ListEvent;

public class EventsActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listView;
    ArrayList<Event> listEvent = new ListEvent();
    EventAdapter adapter;

    Button btValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // Initialize views after setContentView
        listView = findViewById(R.id.list_events);
        adapter = new EventAdapter(getApplicationContext(), listEvent, getLayoutInflater());

        ((Button) findViewById(R.id.button_create_event)).setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
            startActivity(intent);
        });

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = (Event) parent.getItemAtPosition(position);
                onClickEvent(event);
            }
        });

        // Initialize database
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Initialize database reference
        databaseReference = firebaseDatabase.getReference().child("");

        // Get events from the database
        getEvent();

        // Add fragment transaction after views initialization
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragmentMenu = new MenuFragment();
        transaction.replace(R.id.fragment_menu, fragmentMenu);
        transaction.commit();
    }

    private void getEvent() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listEvent.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Event sEvent = dataSnapshot.child("Event").getValue(Event.class);
                    listEvent.add(sEvent);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickEvent(Event item) {
        Intent intent = new Intent(getApplicationContext(), OneEventActivity.class);
        intent.putExtra(EVENT, (Parcelable) item);
        startActivity(intent);
    }
}
