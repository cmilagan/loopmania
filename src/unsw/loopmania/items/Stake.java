package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped stake in the backend world
 */
public class Stake extends AttackItem {
    private static int itemCost = 8;
    private static int itemDamage = 4;
    private static int itemDurability = 8;
    private static int itemVampireDamage = 12; 

    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, itemDamage);
    }

    public int getSpecialDamage() {
        return itemVampireDamage;
    }
}