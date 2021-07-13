package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a health potion in the backend world
 */
public class HealthPotion extends Item {
    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}