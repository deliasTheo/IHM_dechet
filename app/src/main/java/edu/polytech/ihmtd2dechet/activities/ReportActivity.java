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

import org.osmdroid.util.GeoPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.applications.NotificationApplication;
import edu.polytech.ihmtd2dechet.interfaces.PictureInterface;
import edu.polytech.ihmtd2dechet.fragments.PictureFragment;
import edu.polytech.ihmtd2dechet.interfaces.StorageInterface;
import edu.polytech.ihmtd2dechet.objects.Notification;
import edu.polytech.ihmtd2dechet.objects.Report;
import edu.polytech.ihmtd2dechet.objects.ReportsList;

public class ReportActivity extends AppCompatActivity {

    private Bitmap picture;
    private PictureFragment pictureFragment;
    Bitmap imageBitmap = null;
    private String directory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);

        Button backButton = findViewById(R.id.bouton_retour);
        Button reportButton = findViewById(R.id.bouton_signaler);
        TextView wasteType = findViewById(R.id.report_waste_type);

        // Fragment creation
        pictureFragment = (PictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_picture);
        if (pictureFragment == null) {
            pictureFragment = new PictureFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_picture, pictureFragment);
            transaction.commit();
        }

        // Select waste type
        wasteType.setOnClickListener(click ->
        {
            final String[] items = {"Encombrant", "Non Encombrant", "Déchet toxique", "Poubelle"};
            AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
            builder.setTitle("Sélectionner type de déchet");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String selectedItem = items[which];
                    if(selectedItem.equals("Encombrant")) {
                        String waste = "Encombrant";
                        wasteType.setText(waste);
                    }
                    if(selectedItem.equals("Non Encombrant")) {
                        String waste = "Non Encombrant";
                        wasteType.setText(waste);
                    }
                    if(selectedItem.equals("Déchet toxique")) {
                        String waste = "Déchet toxique";
                        wasteType.setText(waste);
                    }
                    if(selectedItem.equals("Poubelle")) {
                        String waste = "Poubelle";
                        wasteType.setText(waste);
                    }

                }
            });
            builder.show();
        });

        // Report button
        reportButton.setOnClickListener(click ->
        {
            String value_description = ((EditText)findViewById(R.id.report_description)).getText()+"";
            String value_position = ((EditText)findViewById(R.id.report_waste_position)).getText()+"";
            String value_type = wasteType.getText()+"";
            if(value_description.length() != 0 && value_position.length() != 0 && value_type.length() != 0 && imageBitmap != null)
            {
                directory = getDir("imageDir", ContextWrapper.MODE_PRIVATE).getPath();
                SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                String fileName = formater.format(new Date());
                saveToInternalStorage(imageBitmap, fileName);
                ReportsList.getInstance().add(new Report(value_description, value_type, new GeoPoint(43.7, 7.005), directory + "/" + fileName));

                if (value_type.compareTo("Déchet toxique") == 0) {
                    NotificationApplication.sendNotification(getApplicationContext(), this, new Notification(NotificationApplication.REPORTING_CHANNEL, NotificationCompat.PRIORITY_MAX, R.drawable.logo_dechets, "Nouveau déchet toxique signalé", value_description + "\n\n" + value_type + ", " +  value_position + "."));
                }
                if (value_type.compareTo("Encombrant") == 0) {
                    NotificationApplication.sendNotification(getApplicationContext(), this, new Notification(NotificationApplication.REPORTING_CHANNEL, NotificationCompat.PRIORITY_HIGH, R.drawable.logo_dechets, "Nouveau déchet encombrant signalé", value_description + "\n\n" + value_type + ", " + value_position + "."));
                }
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Remplir tous les champs SVP", Toast.LENGTH_SHORT).show();
            }
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
