package edu.polytech.ihmtd2dechet.objects;

public class GuideToxique extends Guide{
    public GuideToxique(String info, String comment) {
        super(info,comment);
        super.type = "Toxique";
    }
}
