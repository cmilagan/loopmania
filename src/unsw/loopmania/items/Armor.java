package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

/**
 * represents an equipped or unequipped armor in the backend world
 */
public class Armor extends StaticEntity {
    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}