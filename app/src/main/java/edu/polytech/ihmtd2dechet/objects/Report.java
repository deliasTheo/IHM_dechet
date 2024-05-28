package edu.polytech.ihmtd2dechet.objects;

import org.osmdroid.util.GeoPoint;

public class Report {

    private  String title;
    private final String description;


    private  String advancement ;

    private final GeoPoint location;
    private final int image;


    public Report(String title, String description, String advancement, GeoPoint location, int image) {
        this.title = title;
        this.description = description;
        this.advancement = "A faire";
        this.location = location;
        this.image = image;
    }


    public String getDescription() {
        return description;
    }


    public String getAdvancement() {
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


    public void setAdvancement(String advancement){
        this.advancement = advancement;
    }




}