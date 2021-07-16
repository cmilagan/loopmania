package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class CampfireBuilding extends Building {

    private int range;
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.range = 2;
        this.setExpiry(5);
    }

    public int getRange() {
        return this.range;
    }
}