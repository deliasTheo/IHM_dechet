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
import edu.polytech.ihmtd2dechet.objects.Report;

public class ReportAdapter extends BaseAdapter {

    private final List<Report> reports;
    private final LayoutInflater inflater;
    private final Context context;

    public ReportAdapter(Context context, List<Report> reports, LayoutInflater inflater) {
        this.context = context;
        this.reports = reports;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return reports.size();
    }

    @Override
    public Object getItem(int position) {
        return reports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_signalement, parent, false);
        }

        Report report = reports.get(position);

        TextView title = convertView.findViewById(R.id.titre_layout_signalement);
        title.setText(report.getTitle() + " (" + report.getAdvancement() + ")");

        TextView description = convertView.findViewById(R.id.description_layout_signalement);
        description.setText(report.getDescription());

        ImageView image = convertView.findViewById(R.id.image_layout_signalement);
        image.setImageResource(report.getImage());

        TextView location = convertView.findViewById(R.id.lieu_layout_signalement);
        location.setText(report.getLocation().toString());

        return convertView;
    }

    public void updateReportAdvancement(int position, String newAdvancement) {
        Report report = reports.get(position);
        report.setAdvancement(newAdvancement);
        notifyDataSetChanged();
    }
}
