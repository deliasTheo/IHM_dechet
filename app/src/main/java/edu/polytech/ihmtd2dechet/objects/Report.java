package edu.polytech.ihmtd2dechet.objects;

import org.osmdroid.util.GeoPoint;

public class Report {

    private final String title;
    private final String description;

    private final Avancement advancement ;
    private final GeoPoint location;
    private final int image;


    public Report(String title, String description, Avancement advancement, GeoPoint location, int image) {
        this.title = title;
        this.description = description;
        this.advancement = advancement ;
        this.location = location;
        this.image = image;
    }


    public String getDescription() {
        return description;
    }


    public Avancement getAdvancement() {
        return advancement;
    }


    public int getImage() {
        return image;
    }


    public String getTitle() {
        return this.title;
    }


    public GeoPoint getLocation() {
        return this.location;
    }

}