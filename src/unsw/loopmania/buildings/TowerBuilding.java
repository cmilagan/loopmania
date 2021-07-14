package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

/**
 * a basic form of building in the world
 */
public class TowerBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    
    private int damage;
    private int range;

    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.damage = 4;
        this.range = 2;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getReach() {
        return this.range;
    }

}
