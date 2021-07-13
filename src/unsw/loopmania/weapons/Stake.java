package unsw.loopmania.weapons;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.equipment.Equipment;

/**
 * represents an equipped or unequipped stake in the backend world
 */
public class Stake extends Weapon {
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}