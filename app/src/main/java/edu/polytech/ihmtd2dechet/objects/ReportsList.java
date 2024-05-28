package edu.polytech.ihmtd2dechet.objects;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.polytech.ihmtd2dechet.R;

public class ReportsList {

    /* Singleton pattern */

    private static final ReportsList instance = new ReportsList();

    public static ReportsList getInstance() {
        return instance;
    }


    /* Attributes */

    private static final List<Report> reports = new ArrayList<>(
            Arrays.asList(
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.64950, 7.00517), R.drawable.dechet),
                    new Report("Uranium", "Déchet d'uranium laissé sur la voie publique", null, new GeoPoint(43.65020, 7.00517), R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.64950, 7.00555), R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.77950, 7.00517), R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.12950, 7.00545), R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.64950, 7.00517), R.drawable.dechet),
                    new Report("Uranium", "Déchet d'uranium laissé sur la voie publique", null, new GeoPoint(43.65020, 7.00517), R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.64950, 7.00555), R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.77950, 7.00517), R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, new GeoPoint(43.12950, 7.00545), R.drawable.dechet)/*,
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, "La Ciotat", R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, "La Ciotat", R.drawable.dechet),
                    new Report("Uranium", "Déchet d'uranium laissé sur la voie publique", null, "La Ciotat", R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, "La Ciotat", R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, "La Ciotat", R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, "La Ciotat", R.drawable.dechet),
                    new Report("Poubelle pleine", "Poubelle pleine depuis 2 jours , et les déchets se cumulent", null, "La Ciotat", R.drawable.dechet)*/
            )
    );


    /* Constructor */

    private ReportsList() {}


    /* Methods */

    /**
     * @return the reports list.
     */
    public List<Report> get() {
        return reports;
    }


    /**
     * @param report to add the the reports list.
     */
    public void add(Report report) {
        reports.add(report);
    }


    /**
     * @param report to remove from the reports list.
     */
    public void remove(Report report) {
        reports.remove(report);
    }

}