package edu.polytech.ihmtd2dechet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.view.Display;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.adapter.EvenementAdapter;
import edu.polytech.ihmtd2dechet.fragments.MenuFragment;
import edu.polytech.ihmtd2dechet.objects.ListEvenement;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragmentMenu = new MenuFragment();
        transaction.replace(R.id.fragment_menu, fragmentMenu);
        transaction.commit();

        setContentView(R.layout.activity_events);

        ((Button)findViewById(R.id.button_créer_evenement)).setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), CreateEventActivity.class);
            startActivity(intent);
        });

        ListEvenement listEvenement = new ListEvenement();

        EvenementAdapter adapter = new EvenementAdapter(getApplicationContext(), listEvenement, getLayoutInflater());

        ListView listView = findViewById(R.id.liste_evenements);

        listView.setAdapter(adapter);
        }

}