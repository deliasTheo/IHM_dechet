package edu.polytech.ihmtd2dechet;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment fragmentMenu = new MenuFragment();
        transaction.replace(R.id.fragment_menu, fragmentMenu);
        transaction.commit();
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
                        String selectedItem = items[which];
                        if(selectedItem.equals("Encombrant")) {
                            String infoGen = "Les déchets encombrants se réfèrent généralement" +
                                    " à des articles de grande taille qui ne peuvent pas être " +
                                    "jetés dans une poubelle standard. Cela inclut des objets " +
                                    "tels que des meubles, des électroménagers, des matelas, " +
                                    "des vélos, etc.";
                            String decheterie = "Collecte Municipale : Vérifiez le calendrier de collecte des encombrants dans votre région." +
                                    " Certains services municipaux proposent des collectes spéciales pour les gros articles." +
                                    "Déchèterie ou Centre de Recyclage : Vous pouvez également apporter vos déchets encombrants à une" +
                                    " déchèterie ou à un centre de recyclage désigné.";
                            Toast.makeText(GuideActivity.this, "Vous avez sélectionné " + selectedItem, Toast.LENGTH_SHORT).show();
                            textView.setText(infoGen);
                            textView2.setText(decheterie);
                        }
                        if(selectedItem.equals("Non Encombrant")) {
                            String infoGen = "Un dechat non encombrant est un dechet que vous estimez pouvoir" +
                                    " gerer sans aide externe , attention à bien faire attention" +
                                    " au cas ou le dechet soit toxic" +
                                    "en cas de doute veuillez consulter la page des dechet toxic" +
                                    " pour plus d'information" +
                                    "N'oubliez pas de prendre en photo votre travail , cela fait" +
                                    "plaisir à la communauté et à la mairie" ;
                            Toast.makeText(GuideActivity.this, "Vous avez sélectionné " + selectedItem, Toast.LENGTH_SHORT).show();
                            textView.setText(infoGen);
                        }
                        if(selectedItem.equals("Déchet toxique")) {
                            String infoGen = "Veuillez à être très attentif avec ce type de déchets\n " +
                                    ", ne prenez action en aucun cas\n " +
                                    "informez les services adéquats\n" ;

                            Toast.makeText(GuideActivity.this, "Vous avez sélectionné " + selectedItem, Toast.LENGTH_SHORT).show();
                            textView.setText(infoGen);
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
