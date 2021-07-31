package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends AttackItem {
    private static int damage = 8;
    private static int itemCost = 10;
    private static int itemDurability = 10;

    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, damage);
    }
}
