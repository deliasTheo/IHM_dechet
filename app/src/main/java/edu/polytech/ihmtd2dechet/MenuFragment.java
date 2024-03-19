package edu.polytech.ihmtd2dechet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.Button;
import android.widget.ImageView;

public class MenuFragment extends Fragment {
    public MenuFragment() { } //constructeur (même vide)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        //do something
        ImageView logoMap = rootView.findViewById(R.id.mapPin);
        logoMap.setOnClickListener(click -> {
            if(logoMap.getVisibility()==View.VISIBLE){
                logoMap.setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }
}
