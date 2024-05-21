package edu.polytech.ihmtd2dechet.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.Guide;
import edu.polytech.ihmtd2dechet.objects.GuideFactory;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Button selectButton = findViewById(R.id.select_button);
        Button mapButton = findViewById(R.id.map_button);
        final TextView textView = findViewById(R.id.textView1);
        final TextView textView2 = findViewById(R.id.textView2);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = {"Encombrant", "Non Encombrant", "Déchet toxique"};

                AlertDialog.Builder builder = new AlertDialog.Builder(GuideActivity.this);
                builder.setTitle("Sélectionner un déchet");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Guide guide = GuideFactory.build(which+1);
                            Toast.makeText(GuideActivity.this, "Vous avez sélectionné " + guide.getType(), Toast.LENGTH_SHORT).show();
                            textView.setText(guide.getInformationGeneral());
                            textView2.setText(guide.getOuComment());
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
