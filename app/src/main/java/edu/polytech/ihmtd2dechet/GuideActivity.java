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
                            String infoGen = "Ce sont des déchets qui peuvent être jetés avec les ordures ménagères" +
                                    " régulières. Ce sont généralement des déchets de taille et de poids normaux," +
                                    " tels que les emballages en carton, les plastiques, le verre, etc." ;
                            String decheterie =
                                    "1. Poubelle grise ou bac gris : pour les déchets non recyclables ou non compostables.\n" +
                                            "2. Poubelle jaune ou bac jaune : pour les emballages recyclables tels que le plastique, le métal et le carton.\n" +
                                            "3. Poubelle verte ou bac vert : pour les déchets organiques compostables comme les restes de fruits et légumes.\n" +
                                            "4. Poubelle bleue ou bac bleu : pour les papiers et cartons à recycler.\n" +
                                            "5. Poubelle marron ou bac marron : pour les déchets végétaux destinés au compostage.\n" ;

                            Toast.makeText(GuideActivity.this, "Vous avez sélectionné " + selectedItem, Toast.LENGTH_SHORT).show();
                            textView.setText(infoGen);
                            textView2.setText(decheterie);
                        }
                        if(selectedItem.equals("Déchet toxique")) {
                            String infoGen = "Ce sont des déchets qui contiennent des substances nocives pour" +
                                    " l'environnement ou la santé humaine. Cela inclut les piles, les produits" +
                                    " chimiques ménagers, les peintures, les solvants, etc. Ils doivent être " +
                                    "éliminés de manière spécifique pour éviter toute contamination. ";

                            String decheterie = "Nous vous déconseillons de prendre action tous seuls , si vous estimez que le déchet est dangereux " +
                                    "veuillez informer les services adéquats.\n" +
                                    "Signalez le sur l'application\n" +
                                    "Voici quelques numéros à contacter :\n" +
                                    "Service de nettoyage : 06583840284";

                            Toast.makeText(GuideActivity.this, "Vous avez sélectionné " + selectedItem, Toast.LENGTH_SHORT).show();
                            textView.setText(infoGen);
                            textView2.setText(decheterie);

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
