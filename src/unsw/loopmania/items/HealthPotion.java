package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;

/**
 * represents a health potion in the backend world
 */
public class HealthPotion extends Consumables {
    private int usage = 0;
    private static int itemCost = 20;

    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost);
    }

    @Override
    public int getUsage() {
        return usage;
    }

    /**
     * Given the character, set character health to max.
     */
    public void use(Character character) {
        usage++;
        int maxhealth = 100;
        character.setHealth(maxhealth);
    }
}