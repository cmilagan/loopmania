package unsw.loopmania.weapons;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.equipment.Equipment;

/**
 * represents an equipped or unequipped staff in the backend world
 */
public class Staff extends Weapon {
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}