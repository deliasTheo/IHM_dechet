package edu.polytech.ihmtd2dechet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

public class MenuFragment extends Fragment {
    public MenuFragment() { } //constructeur (même vide)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        //do something

        return rootView;
    }
}
