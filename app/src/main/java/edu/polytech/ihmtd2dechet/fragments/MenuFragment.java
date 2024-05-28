package edu.polytech.ihmtd2dechet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.ImageView;

import edu.polytech.ihmtd2dechet.activities.EventsActivity;
import edu.polytech.ihmtd2dechet.activities.GuideActivity;
import edu.polytech.ihmtd2dechet.activities.MapActivity;
import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.activities.NotificationActivity;
import edu.polytech.ihmtd2dechet.activities.StatisticActivity;

public class MenuFragment extends Fragment {
    public MenuFragment() { }
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        ImageView logoMap = rootView.findViewById(R.id.mapPin);

        logoMap.setOnClickListener(view -> navigateToActivity(MapActivity.class));

        ImageView logoChart = rootView.findViewById(R.id.chartIcon);
        logoChart.setOnClickListener(view -> navigateToActivity(StatisticActivity.class));

        ImageView logoGuide = rootView.findViewById(R.id.guideIcon);
        logoGuide.setOnClickListener(view -> navigateToActivity(GuideActivity.class));

        ImageView logoEvents = rootView.findViewById(R.id.EventsIcon);
        logoEvents.setOnClickListener(view -> navigateToActivity(EventsActivity.class));

        ImageView logoNotification = rootView.findViewById(R.id.notificationIcon);
        logoNotification.setOnClickListener(view -> navigateToActivity(NotificationActivity.class));

        return rootView;
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(getContext(), activityClass);
        startActivity(intent);
    }
}
