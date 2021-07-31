package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Barracks building has an expiry of 5 rounds, and spawns 1 allied soldier when
 * the Main Character passes through it.
 */
public class BarracksBuilding extends Building {
    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.setExpiry(5);
    }
}
