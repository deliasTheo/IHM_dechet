package edu.polytech.ihmtd2dechet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.Notification;

public class NotificationAdapter extends BaseAdapter {

    private List<Notification> notifications;
    private final LayoutInflater inflater;
    private final Context context;


    public NotificationAdapter(List<Notification> notifications, LayoutInflater inflater, Context context) {
        this.notifications = notifications;
        this.inflater = inflater;
        this.context = context;
    }


    @Override
    public int getCount() {
        return notifications.size();
    }


    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_notification, null);

        TextView channel = view.findViewById(R.id.channel_layout_notification);
        TextView priority = view.findViewById(R.id.priority_layout_notification);
        ImageView smallIcon = view.findViewById(R.id.small_icon_layout_notification);
        TextView title = view.findViewById(R.id.title_layout_notification);
        TextView text = view.findViewById(R.id.text_layout_notification);

        channel.setText(notifications.get(position).getChannel());
        priority.setText("Priorité : " + notifications.get(position).getPriority());
        smallIcon.setImageResource(notifications.get(position).getSmallIcon());
        title.setText(notifications.get(position).getTitle());
        text.setText(notifications.get(position).getText());

        return view;
    }

}