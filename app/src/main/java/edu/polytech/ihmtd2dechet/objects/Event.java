package edu.polytech.ihmtd2dechet.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import edu.polytech.ihmtd2dechet.R;

public class Event implements Parcelable {
    private String uid;
    private String title;
    private String description;
    private String date;
    private String location;
    private int image;
    private String start_time;
    private String end_time;

    public Event() {
        // Constructeur par défaut requis pour les appels à DataSnapshot.getValue(Event.class)
    }

    public Event(String title, String description, String date, String location, String start_time, String end_time) {
        this.uid = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.image = R.drawable.greenwalk;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    protected Event(Parcel in) {
        uid = in.readString();
        title = in.readString();
        description = in.readString();
        date = in.readString();
        location = in.readString();
        image = in.readInt();
        start_time = in.readString();
        end_time = in.readString();
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

    public String getUid() {
        return uid;
    }

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

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(location);
        dest.writeInt(image);
        dest.writeString(start_time);
        dest.writeString(end_time);
    }

    public static void deleteEvent(String uid) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Event").child(uid);
        databaseReference.removeValue();
    }

}
