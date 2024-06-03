package edu.polytech.ihmtd2dechet.objects;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import edu.polytech.ihmtd2dechet.activities.EventsActivity;
import edu.polytech.ihmtd2dechet.activities.OneEventActivity;
import edu.polytech.ihmtd2dechet.adapter.EventAdapter;

public class ControllerEvent {
    private EventView view;
    private EventListModel model;

    public ControllerEvent(EventView view, EventListModel model) {
        this.view = view;
        this.model = model;
    }

    public void onClickItem(int position) {
    }
}
