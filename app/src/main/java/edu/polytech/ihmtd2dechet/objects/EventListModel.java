package edu.polytech.ihmtd2dechet.objects;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

import edu.polytech.ihmtd2dechet.activities.EventsActivity;
import edu.polytech.ihmtd2dechet.adapter.EventAdapter;

public class EventListModel extends Observable {
    private static final String TAG = "EventsActivity";
    private ArrayList<Event> listEvent= new ArrayList<>();
    private ControllerEvent controller;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    public EventListModel() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Event");
    }

    public void setController(ControllerEvent controllerEvent) {
        this.controller = controllerEvent;
    }
    public Event get(int postion){
        return listEvent.get(postion);
    }

    public int size(){
        return listEvent.size();
    }
    public void populate(Context context, EventAdapter adapter) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listEvent.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    listEvent.add(event);

                }
                adapter.notifyDataSetChanged();
                Log.d("moi", String.valueOf(listEvent.size()));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e(TAG, "Error fetching data", databaseError.toException());
                Toast.makeText(context, "Error fetching data", Toast.LENGTH_SHORT).show();
            }

        });
        Log.d("moi", String.valueOf(listEvent.size()));
    }


}
