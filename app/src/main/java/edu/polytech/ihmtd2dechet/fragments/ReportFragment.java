package edu.polytech.ihmtd2dechet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.activities.ReportActivity;

public class ReportFragment extends Fragment {

    public ReportFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View rootView = inflater.inflate(R.layout.fragment_report, container, false);

        TextView report = rootView.findViewById(R.id.report);
        report.setOnClickListener(click -> {
            Intent intent = new Intent(getContext(), ReportActivity.class);
            startActivity(intent);
        });

        return rootView;
    }
}
