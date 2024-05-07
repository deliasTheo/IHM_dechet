package edu.polytech.ihmtd2dechet;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ReportingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reporting);

        Button backButton = findViewById(R.id.bouton_retour);
        Button reportButton = findViewById(R.id.bouton_signaler);
        TextView wasteType = findViewById(R.id.report_waste_type);

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

        backButton.setOnClickListener(click ->
        {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        ((EditText)findViewById(R.id.report_description)).setText("");
        ((EditText)findViewById(R.id.report_waste_position)).setText("");
    }
}
