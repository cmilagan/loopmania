package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class TrapBuilding extends Building {
    private int damage = 8;

    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.setExpiry(1);
    }

    public int getDamage() {
        return this.damage;
    }
}
