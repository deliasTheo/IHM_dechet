package edu.polytech.ihmtd2dechet.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Event implements Parcelable {
    private String title;
    private String description;
    private String date;
    private String location;
    private int image;

    public Event(){

    }

    public Event(String title, String description, String date, String location, int image) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.image = image;
    }

    protected Event(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = in.readString();
        location = in.readString();
        image = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(location);
        dest.writeInt(image);
    }

    public void delete() {
        List<Event> listEvent = new ArrayList<>();
        listEvent.remove(this);
    }
}
