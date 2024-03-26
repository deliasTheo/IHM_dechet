package edu.polytech.ihmtd2dechet;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Fragment fragmentMenu = new MenuFragment();
        transaction.replace(R.id.fragment_menu, fragmentMenu);

        transaction.commit();
    }

}