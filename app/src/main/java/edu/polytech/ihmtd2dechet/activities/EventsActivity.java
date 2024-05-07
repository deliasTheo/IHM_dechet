package edu.polytech.ihmtd2dechet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.fragments.MenuFragment;

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


    }
}