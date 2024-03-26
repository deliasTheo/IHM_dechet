package edu.polytech.ihmtd2dechet;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;

public class MapActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragmentMenu= new MenuFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_menu, fragmentMenu);
        transaction.commit();

        setContentView(R.layout.activity_map);
    }

}