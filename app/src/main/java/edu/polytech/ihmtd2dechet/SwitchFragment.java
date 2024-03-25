package edu.polytech.ihmtd2dechet;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SwitchFragment extends Fragment {

    public SwitchFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View rootView = inflater.inflate(R.layout.fragment_switch, container, false);

        TextView map = rootView.findViewById(R.id.map);
        map.setOnClickListener(click -> {
            Intent intent = new Intent(getContext(), MapActivity.class);
            startActivity(intent);
        });

        TextView list = rootView.findViewById(R.id.list);
        list.setOnClickListener(click -> {
            Intent intent = new Intent(getContext(), ListActivity.class);
            startActivity(intent);
        });

        return rootView;
    }

}