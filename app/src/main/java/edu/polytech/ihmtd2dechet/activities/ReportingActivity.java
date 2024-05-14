package edu.polytech.ihmtd2dechet.activities;

import static edu.polytech.ihmtd2dechet.applications.ApplicationEvenement.VALUE_DATE;
import static edu.polytech.ihmtd2dechet.applications.ApplicationEvenement.VALUE_DESCRIPTION;
import static edu.polytech.ihmtd2dechet.applications.ApplicationEvenement.VALUE_LIEU;
import static edu.polytech.ihmtd2dechet.applications.ApplicationEvenement.VALUE_TITRE;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.interfaces.PictureInterface;
import edu.polytech.ihmtd2dechet.fragments.PictureFragment;

public class ReportingActivity extends AppCompatActivity {

    private Bitmap picture;
    private PictureFragment pictureFragment;
    Bitmap imageBitmap = null;

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
            final String[] items = {"Encombrant", "Non Encombrant", "Déchet toxique"};
            AlertDialog.Builder builder = new AlertDialog.Builder(ReportingActivity.this);
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
}
