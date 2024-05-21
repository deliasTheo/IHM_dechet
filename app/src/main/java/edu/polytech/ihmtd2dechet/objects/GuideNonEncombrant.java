package edu.polytech.ihmtd2dechet.objects;

public class GuideNonEncombrant extends Guide{
    public GuideNonEncombrant(String info, String comment) {
        super(info,comment);
        super.type = "Non-emcombrant";
    }
}
