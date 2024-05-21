package edu.polytech.ihmtd2dechet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.Signalement;

public class SignalementAdapter extends BaseAdapter {


    private ArrayList<Signalement> signalements;
    private LayoutInflater inflater;
    private Context context;

    public SignalementAdapter(Context context, ArrayList<Signalement> signalements, LayoutInflater inflater) {
        this.context = context;
        this.signalements = signalements;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return signalements.size();
    }

    @Override
    public Object getItem(int position) {
        return signalements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_signalement, null);
        TextView titre = view.findViewById(R.id.titre_layout_signalement);
        TextView description = view.findViewById(R.id.description_layout_signalement);
        ImageView image = view.findViewById(R.id.image_layout_signalement);
        TextView lieu = view.findViewById(R.id.lieu_layout_signalement);
        titre.setText(signalements.get(position).getTitre());
        lieu.setText(signalements.get(position).getLieu());
        description.setText(signalements.get(position).getDescription());
        image.setImageResource(signalements.get(position).getImage());
        return view;
    }


}
