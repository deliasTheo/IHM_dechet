package edu.polytech.ihmtd2dechet.objects;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.R;

public class ListEvent extends ArrayList<Event> {

    public ListEvent(){
        add(new Event("Nettoyage de la plage", "Nettoyage de la plage de la ciotat", "12/12/2021", "La Ciotat", R.drawable.greenwalk));
        add(new Event("Green Walk", "Nettoyage du sentier du littoral", "21/12/2021", "Sophia", R.drawable.greenwalk));
        add(new Event("Ramassage des déchets", "Passage du camion poubelle", "02/01/2022", "Antibes", R.drawable.greenwalk));
        add(new Event("Décheterie déchets verts", "Décheterie pour déchets verts ouvert", "05/01/2022", "Cannes", R.drawable.greenwalk));
    }

    @Override
    public void clear() {
        super.clear();
    }
}
