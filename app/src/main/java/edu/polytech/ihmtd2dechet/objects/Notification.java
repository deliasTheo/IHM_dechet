package edu.polytech.ihmtd2dechet.objects;

public class Notification {

    private String channel;
    private int priority;
    private int smallIcon;
    private String title;
    private String text;


    public Notification(String channel, int priority, int smallIcon, String title, String text) {
        this.channel = channel;
        this.priority = priority;
        this.smallIcon = smallIcon;
        this.title = title;
        this.text = text;
    }


    public String getChannel() {
        return this.channel;
    }


    public int getPriority() {
        return this.priority;
    }


    public int getSmallIcon() {
        return this.smallIcon;
    }


    public String getTitle() {
        return this.title;
    }


    public String getText() {
        return this.text;
    }

}