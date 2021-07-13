package unsw.loopmania.equipment;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped armor in the backend world
 */
public class Armor extends Equipment {
    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}