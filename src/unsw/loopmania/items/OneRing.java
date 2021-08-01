package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;

/**
 * represents the One Ring in the backend world
 */
public class OneRing extends Consumables {
    private static int itemCost = 5000;

    public OneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost);
    }

    /**
     * Given the dead character, set character health to max.
     */
    public void use(Character character) {
        this.incrementUsage(); 
        int maxhealth = 100;
        character.setHealth(maxhealth);
    }
}