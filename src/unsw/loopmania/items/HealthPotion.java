package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a health potion in the backend world
 */
public class HealthPotion extends BattleItem {
    private static int itemCost = 20;
    private static int itemDurability = 1; 

    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability);
    }    
}