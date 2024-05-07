package edu.polytech.ihmtd2dechet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import static edu.polytech.ihmtd2dechet.applications.ApplicationEvenement.*;

import edu.polytech.ihmtd2dechet.R;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);

        ((Button)findViewById(R.id.bouton_retour)).setOnClickListener(click ->
        {
            Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
            startActivity(intent);
        });

        ((Button)findViewById(R.id.bouton_valider)).setOnClickListener(click ->
        {
            String value_titre = ((EditText)findViewById(R.id.saisi_titre)).getText()+"";
            String value_description = ((EditText)findViewById(R.id.saisi_description)).getText()+"";
            String value_date = ((EditText)findViewById(R.id.saisi_date)).getText()+"";
            String value_lieu = ((EditText)findViewById(R.id.saisi_lieu)).getText()+"";
            if(value_titre.length() != 0 && value_date.length() != 0 && value_lieu.length() != 0)
            {
                Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                intent.putExtra(VALUE_TITRE, value_titre);
                intent.putExtra(VALUE_DESCRIPTION, value_description);
                intent.putExtra(VALUE_DATE, value_date);
                intent.putExtra(VALUE_LIEU, value_lieu);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Remplir tous les champs SVP", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        ((EditText)findViewById(R.id.saisi_titre)).setText("");
        ((EditText)findViewById(R.id.saisi_description)).setText("");
        ((EditText)findViewById(R.id.saisi_date)).setText("");
        ((EditText)findViewById(R.id.saisi_lieu)).setText("");
    }
}
