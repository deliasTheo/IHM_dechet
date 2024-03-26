package edu.polytech.ihmtd2dechet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.ImageView;

public class MenuFragment extends Fragment {
    public MenuFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        ImageView logoMap = rootView.findViewById(R.id.mapPin);
        logoMap.setOnClickListener(click -> {
            Intent intent = new Intent(getContext(), MapActivity.class);
            if (getContext().getClass()!=MapActivity.class){
                startActivity(intent);
            }
        });


        ImageView logoChart = rootView.findViewById(R.id.chartIcon);
        logoChart.setOnClickListener(click -> {
            Intent intent = new Intent(getContext(), StatisticActivity.class);
            startActivity(intent);
        });


        ImageView logoGuide = rootView.findViewById(R.id.guideIcon);
        logoGuide.setOnClickListener(view -> navigateToActivity(GuideActivity.class));

        ImageView logoEvents = rootView.findViewById(R.id.EventsIcon);
        logoEvents.setOnClickListener(click -> {
            Intent intent = new Intent(getContext(), EventsActivity.class);
            startActivity(intent);
        });

        return rootView;
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(getContext(), activityClass);
        startActivity(intent);
    }
}
