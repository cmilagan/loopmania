package unsw.loopmania.weapons;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.equipment.Equipment;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
