package edu.polytech.ihmtd2dechet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.objects.Evenement;
import edu.polytech.ihmtd2dechet.R;

public class EvenementAdapter extends BaseAdapter {

    private ArrayList<Evenement> evenements;
    private LayoutInflater inflater;
    private Context context;

    public EvenementAdapter(Context context, ArrayList<Evenement> evenements, LayoutInflater inflater) {
        this.context = context;
        this.evenements = evenements;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return evenements.size();
    }

    @Override
    public Object getItem(int position) {
        return evenements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_event, null);
        TextView titre = view.findViewById(R.id.titre_layout_event);
        TextView lieu = view.findViewById(R.id.lieu_layout_event);
        TextView date = view.findViewById(R.id.date_layout_event);
        ImageView image = view.findViewById(R.id.image_layout_event);

        titre.setText(evenements.get(position).getTitre());
        lieu.setText(evenements.get(position).getLieu());
        date.setText(evenements.get(position).getDate());
        image.setImageResource(evenements.get(position).getImage());

        return view;
    }
}
