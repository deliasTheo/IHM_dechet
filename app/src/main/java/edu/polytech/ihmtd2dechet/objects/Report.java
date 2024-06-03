package edu.polytech.ihmtd2dechet.objects;

import org.osmdroid.util.GeoPoint;

public class Report {

    private final String description;
    private final String type;
    private  String advancement ;
    private final GeoPoint location;
    private final int image;
    private final String imagePath;


    public Report(String description, String type, String advancement, GeoPoint location, int image, String imagePath) {
        this.description = description;
        this.type = type;
        this.advancement = advancement;
        this.location = location;
        this.image = image;
        this.imagePath = imagePath;
    }

    public Report(String description, String type, GeoPoint location, int image) {
        this.description = description;
        this.type = type;
        this.advancement = "A faire";
        this.location = location;
        this.image = image;
        this.imagePath = "";
    }

    public Report(String description, String type, GeoPoint location, String imagePath) {
        this.description = description;
        this.type = type;
        this.advancement = "A faire";
        this.location = location;
        this.image = -1;
        this.imagePath = imagePath;
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


    public String getImagePath() {
        return imagePath;
    }


    public String getType() {
        return this.type;
    }


    public GeoPoint getLocation() {
        return this.location;
    }


    public void setAdvancement(String advancement){
        this.advancement = advancement;
    }




}