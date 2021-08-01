package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * Anduril, Flame of the west
 */
public class Anduril extends AttackItem {
    private static int itemCost = 7000;
    private static int itemDamage = 15;
    private static int itemDurability = 20;
    private static int itemBossDamage = 20;

    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, itemDamage);
    }

    public int getSpecialDamage() {
        this.incrementUsage();
        return itemBossDamage;
    }
}