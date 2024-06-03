package edu.polytech.ihmtd2dechet.objects;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.activities.EventsActivity;
import edu.polytech.ihmtd2dechet.activities.OneEventActivity;
import edu.polytech.ihmtd2dechet.adapter.EventAdapter;

public class EventView implements Observer {

    private EventAdapter adapter;
    private ControllerEvent controller;
    private final ViewGroup layout;


    public EventView(ViewGroup layout) {
        this.layout = layout;
    }

    @Override
    public void update(Observable observable, Object o) {

        adapter.notifyDataSetChanged();
    }

    public void setAdapter(EventAdapter adapter) {
        this.adapter = adapter;
    }

    public void setController(ControllerEvent controller) {
        this.controller = controller;
    }
}
