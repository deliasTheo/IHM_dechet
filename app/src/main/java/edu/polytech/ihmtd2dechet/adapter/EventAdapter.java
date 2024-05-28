package edu.polytech.ihmtd2dechet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.objects.ControllerEvent;
import edu.polytech.ihmtd2dechet.objects.Event;
import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.EventListModel;
import edu.polytech.ihmtd2dechet.objects.EventView;

public class EventAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private final EventListModel model;
    private final EventView view;
    private final ControllerEvent controller;

    public EventAdapter(Context context, ControllerEvent controller, EventListModel model, EventView view) {
        this.model = model;
        this.view = view;
        this.controller = controller;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }
    // controller.

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_event, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title_layout_event);
            holder.location = convertView.findViewById(R.id.location_layout_event);
            holder.date = convertView.findViewById(R.id.date_layout_event);
            holder.image = convertView.findViewById(R.id.image_layout_event);
            convertView.setTag(holder);
            View finalConvertView = convertView;
            convertView.setOnClickListener(clic ->  controller.userActionDisplayEvent(this, position, finalConvertView.getContext()) );
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Event event = model.get(position);
        holder.title.setText(event.getTitle());
        holder.location.setText(event.getLocation());
        holder.date.setText(event.getDate());

        // Assuming the image is a drawable resource ID
        holder.image.setImageResource(event.getImage());

        // If the image is a URL, you could use Glide or Picasso
        // Glide.with(context).load(event.getImage()).into(holder.image);

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView location;
        TextView date;
        ImageView image;
    }
}
