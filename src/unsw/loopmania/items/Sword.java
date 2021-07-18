package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends AttackItem {
    private int usage = 0;
    private static int damage = 8;
    private static int itemCost = 10;
    private static int itemDurability = 10;

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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
