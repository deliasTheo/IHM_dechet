package edu.polytech.ihmtd2dechet.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        TextView description = convertView.findViewById(R.id.description_layout_signalement);
        description.setText(report.getDescription());

        TextView type = convertView.findViewById(R.id.type_layout_signalement);
        type.setText(report.getType());

        TextView avancement = convertView.findViewById(R.id.avancement_layout_signalement);
        avancement.setText("statut : " + report.getAdvancement());

        ImageView image = convertView.findViewById(R.id.image_layout_signalement);
        if (report.getImage() == -1) {
            displayReportImage(image, report);
        } else {
            image.setImageResource(report.getImage());
        }

        TextView location = convertView.findViewById(R.id.lieu_layout_signalement);
        location.setText(report.getLocation().toString());

        return convertView;
    }

    public void displayReportImage(ImageView imageView, Report report) {
        String imagePath = report.getImagePath();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            // Gestion du cas où le bitmap est null
            imageView.setImageResource(R.drawable.dechet); // Placeholder par défaut
        }
    }

    public void updateReportAdvancement(int position, String newAdvancement) {
        Report report = reports.get(position);
        report.setAdvancement(newAdvancement);
        notifyDataSetChanged();
    }
}
