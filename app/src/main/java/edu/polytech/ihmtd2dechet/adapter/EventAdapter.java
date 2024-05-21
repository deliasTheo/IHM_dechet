package edu.polytech.ihmtd2dechet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.objects.Event;
import edu.polytech.ihmtd2dechet.R;

public class EventAdapter extends BaseAdapter {

    private ArrayList<Event> events;
    private LayoutInflater inflater;
    private Context context;

    public EventAdapter(Context context, ArrayList<Event> events, LayoutInflater inflater) {
        this.context = context;
        this.events = events;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_event, null);
        TextView title = view.findViewById(R.id.title_layout_event);
        TextView location = view.findViewById(R.id.location_layout_event);
        TextView date = view.findViewById(R.id.date_layout_event);
        ImageView image = view.findViewById(R.id.image_layout_event);

        title.setText(events.get(position).getTitle());
        location.setText(events.get(position).getLocation());
        date.setText(events.get(position).getDate());
        image.setImageResource(events.get(position).getImage());

        return view;
    }
}
