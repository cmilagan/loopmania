package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.buildings.Building;

/**
 * A building which never expires, stays at the start and contains the shop
 * 
 */
public class HeroCastleBuilding extends Building {
    public HeroCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        // TODO change number later
        this.setExpiry(100);
    }
}
