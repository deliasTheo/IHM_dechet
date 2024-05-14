package edu.polytech.ihmtd2dechet.objects;

public class Guide {
    private String informationGeneral;
    private String ouComment;
    protected String type;
    public Guide(String info, String comment){
        this.informationGeneral = info;
        this.ouComment = comment;
    }

    public String getType() {
        return type;
    }

    public String getInformationGeneral() {
        return informationGeneral;
    }

    public String getOuComment() {
        return ouComment;
    }
}
