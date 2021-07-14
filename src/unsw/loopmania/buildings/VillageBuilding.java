package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

/**
 * a basic form of building in the world
 */
public class VillageBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    private int heal;
    
    public VillageBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.heal = 10;
    }

    public int getHeal() {
        return this.heal;
    }
}
