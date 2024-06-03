package edu.polytech.ihmtd2dechet.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.util.GeoPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.applications.NotificationApplication;
import edu.polytech.ihmtd2dechet.interfaces.LocalistaionInterface;
import edu.polytech.ihmtd2dechet.interfaces.PictureInterface;
import edu.polytech.ihmtd2dechet.fragments.PictureFragment;
import edu.polytech.ihmtd2dechet.objects.Notification;
import edu.polytech.ihmtd2dechet.objects.Report;
import edu.polytech.ihmtd2dechet.objects.ReportsList;

public class ReportActivity extends AppCompatActivity {

    private PictureFragment pictureFragment;
    Bitmap imageBitmap = null;
    private String directory;
    private GeoPoint wastePosition = null;
    private String wasteType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);

        Button backButton = findViewById(R.id.bouton_retour);
        Button reportButton = findViewById(R.id.bouton_signaler);
        TextView wasteTypeButton = findViewById(R.id.report_waste_type);
        TextView wastePositionButton = findViewById(R.id.report_waste_position);

        // Fragment creation
        pictureFragment = (PictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_picture);
        if (pictureFragment == null) {
            pictureFragment = new PictureFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_picture, pictureFragment);
            transaction.commit();
        }

        // Select waste position
        wastePositionButton.setOnClickListener(click -> {
            getLastKnownLocation();
        });

        // Select waste type
        wasteTypeButton.setOnClickListener(click ->
        {
            wasteTypeSelection(wasteTypeButton);
        });

        // Report button
        reportButton.setOnClickListener(click ->
        {
            wasteType = wasteTypeButton.getText() + "";
            reportWaste(wasteType);
        });

        // Back button
        backButton.setOnClickListener(click ->
        {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureInterface.REQUEST_CAMERA && resultCode == -1) {
            imageBitmap = (Bitmap) data.getExtras().get("data");
            pictureFragment.setImage(imageBitmap);
        }
    }


    private void getLastKnownLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LocalistaionInterface.REQUEST_LOCATION);
        } else {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    GeoPoint geoPoint = new GeoPoint(latitude, longitude);
                    wastePosition = geoPoint;
                    ((TextView)findViewById(R.id.report_waste_position)).setText(geoPoint.toString());
                }
            });
        }
    }


    private void wasteTypeSelection(TextView wasteTypeButton) {
        final String[] items = {"Encombrant", "Non Encombrant", "Déchet toxique", "Poubelle"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
        builder.setTitle("Sélectionner type de déchet");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedItem = items[which];
                if(selectedItem.equals("Encombrant")) {
                    String waste = "Encombrant";
                    wasteTypeButton.setText(waste);
                }
                if(selectedItem.equals("Non Encombrant")) {
                    String waste = "Non Encombrant";
                    wasteTypeButton.setText(waste);
                }
                if(selectedItem.equals("Déchet toxique")) {
                    String waste = "Déchet toxique";
                    wasteTypeButton.setText(waste);
                }
                if(selectedItem.equals("Poubelle")) {
                    String waste = "Poubelle";
                    wasteTypeButton.setText(waste);
                }

            }
        });
        builder.show();
    }


    public void reportWaste(String wasteType) {
        String value_description = ((EditText)findViewById(R.id.report_description)).getText()+"";
        if(value_description.length() != 0 && wastePosition != null && wasteType.length() != 0 && imageBitmap != null)
        {
            directory = getDir("imageDir", ContextWrapper.MODE_PRIVATE).getPath();
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String fileName = formater.format(new Date());
            saveToInternalStorage(imageBitmap, fileName);
            ReportsList.getInstance().add(new Report(value_description, wasteType, wastePosition, directory + "/" + fileName));

            if (wasteType.compareTo("Déchet toxique") == 0) {
                NotificationApplication.sendNotification(getApplicationContext(), this, new Notification(NotificationApplication.REPORTING_CHANNEL, NotificationCompat.PRIORITY_MAX, R.drawable.logo_dechets, "Nouveau déchet toxique signalé", value_description + "\n\n" + wasteType + ", " +  wastePosition + "."));
            }
            if (wasteType.compareTo("Encombrant") == 0) {
                NotificationApplication.sendNotification(getApplicationContext(), this, new Notification(NotificationApplication.REPORTING_CHANNEL, NotificationCompat.PRIORITY_HIGH, R.drawable.logo_dechets, "Nouveau déchet encombrant signalé", value_description + "\n\n" + wasteType + ", " + wastePosition + "."));
            }
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Remplir tous les champs SVP", Toast.LENGTH_SHORT).show();
        }
    }


    public void saveToInternalStorage(Bitmap picture, String fileName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/*");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        File file = new File(directory, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            picture.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
