package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;

/**
 * represents a health potion in the backend world
 */
public class HealthPotion extends Consumables {
    private static int itemCost = 20;

    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost);
    }

    /**
     * Given the character, set character health to max.
     */
    public void use(Character character) {
        this.incrementUsage(); 
        int maxhealth = 100;
        character.setHealth(maxhealth);
    }
}