package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A building which participates in a battle nearby.
 */
public class TowerBuilding extends Building {
    
    private int damage;
    private int range;

    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y, int id) {
        super(x, y, id);
        this.damage = 4;
        this.range = 2;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getRange() {
        return this.range;
    }

}
