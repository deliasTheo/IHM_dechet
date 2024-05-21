package edu.polytech.ihmtd2dechet.objects;

public class GuideEncombrant extends Guide{

    public GuideEncombrant(String info, String comment) {
        super(info,comment);
        super.type = "Emcombrant";
    }
}
