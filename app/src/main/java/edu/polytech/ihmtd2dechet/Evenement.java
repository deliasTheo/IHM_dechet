package edu.polytech.ihmtd2dechet;

public class Evenement {
    private String titre;
    private String description;
    private String date;
    private String lieu;
    private int image;

    public Evenement(String titre, String description, String date, String lieu, int image) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public int getImage() {
        return image;
    }
}
