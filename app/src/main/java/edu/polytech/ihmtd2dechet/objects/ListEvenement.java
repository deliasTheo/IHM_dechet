package edu.polytech.ihmtd2dechet.objects;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.Evenement;

public class ListEvenement extends ArrayList<Evenement> {
    public ListEvenement(){
        add(new Evenement("Nettoyage de la plage", "Nettoyage de la plage de la ciotat", "12/12/2021", "La Ciotat", R.drawable.logo_events));
        add(new Evenement("Nettoyage de la plage", "Nettoyage de la plage de la ciotat", "12/12/2021", "La Ciotat", R.drawable.logo_events));
        add(new Evenement("Nettoyage de la plage", "Nettoyage de la plage de la ciotat", "12/12/2021", "La Ciotat", R.drawable.logo_events));
        add(new Evenement("Nettoyage de la plage", "Nettoyage de la plage de la ciotat", "12/12/2021", "La Ciotat", R.drawable.logo_events));
        add(new Evenement("Nettoyage de la plage", "Nettoyage de la plage de la ciotat", "12/12/2021", "La Ciotat", R.drawable.logo_events));
    }
}
