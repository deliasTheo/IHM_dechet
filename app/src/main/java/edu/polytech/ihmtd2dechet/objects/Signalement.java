package edu.polytech.ihmtd2dechet.objects;

public class Signalement {

    private String titre;
    private String description;

    private Avancement avancement ;
    private String lieu;
    private int image;

    public Signalement(String titre, String description,Avancement avancement, String lieu, int image) {
        this.titre = titre;
        this.description = description;
        this.avancement = avancement ;
        this.lieu = lieu;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public Avancement getAvancement() {
        return avancement;
    }

    public int getImage() {
        return image;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getLieu() {
        return this.lieu;
    }
}
