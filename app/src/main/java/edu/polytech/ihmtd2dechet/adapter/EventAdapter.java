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

    public EventAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
        this.inflater = LayoutInflater.from(context);
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_event, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title_layout_event);
            holder.location = convertView.findViewById(R.id.location_layout_event);
            holder.date = convertView.findViewById(R.id.date_layout_event);
            holder.image = convertView.findViewById(R.id.image_layout_event);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Event event = events.get(position);
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
