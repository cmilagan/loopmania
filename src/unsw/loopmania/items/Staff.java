package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped staff in the backend world
 */
public class Staff extends AttackItem {
    private int usage = 0;
    private static int damage = 3;
    private static int itemCost = 8;
    private static int itemDurability = 8;

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, damage);
    }   

    @Override
    public int getUsage() {
        return usage;
    }

    /**
     * Inflicts damage on enemy, and increases usage.
     * 
     * If usage reaches item durability, return item damage as 0.
     */
    @Override
    public int inflictDamage() {
        usage++;
        return damage;
    }
}