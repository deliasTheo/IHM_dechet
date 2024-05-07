package edu.polytech.ihmtd2dechet.objects;

import java.util.ArrayList;
import java.util.List;

public class WasteList {

    /* Singleton pattern */

    private static final WasteList instance = new WasteList();

    public static WasteList getInstance() {
        return instance;
    }


    /* Attributes */

    private static final List<Waste> wastes = new ArrayList<>();


    /* Constructor */

    private WasteList() {}


    /* Methods */

    /**
     * @return the wastes list.
     */
    public List<Waste> getWastes() {
        return wastes;
    }


    /**
     * @param waste to add the the wastes list.
     */
    public void add(Waste waste) {
        wastes.add(waste);
    }


    /**
     * @param waste to remove from the wastes list.
     */
    public void remove(Waste waste) {
        wastes.remove(waste);
    }

}