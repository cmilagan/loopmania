package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped staff in the backend world
 */
public class Staff extends AttackItem {
    private static int damage = 3;
    private static int itemCost = 25;
    private static int itemDurability = 8;

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, damage);
    }   
}