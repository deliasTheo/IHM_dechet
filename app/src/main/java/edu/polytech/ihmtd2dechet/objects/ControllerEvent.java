package edu.polytech.ihmtd2dechet.objects;

import static edu.polytech.ihmtd2dechet.applications.EventApplication.EVENT;

import android.content.Context;
import android.content.Intent;


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

    public void userActionDisplayEvent(EventAdapter adapter, int position, Context context) {
        // Obtenir l'élément sélectionné à partir de l'adaptateur
        Event selectedEvent = (Event) adapter.getItem(position);

        // Créer un intent pour démarrer OneEventActivity
        Intent intent = new Intent(context, OneEventActivity.class);
        // Passer les données de l'événement à OneEventActivity avec la même clé que dans OneEventActivity
        intent.putExtra(EVENT, selectedEvent);
        // Démarrer OneEventActivity
        context.startActivity(intent);
    }

    public void onClickItem(int position) {
    }
}
