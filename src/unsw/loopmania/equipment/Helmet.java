package unsw.loopmania.equipment;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped helmt in the backend world
 */
public class Helmet extends Equipment {
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}