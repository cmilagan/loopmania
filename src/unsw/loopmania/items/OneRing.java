package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents the One Ring in the backend world
 */
public class OneRing extends BattleItem {
    private static int itemCost = 500;
    private static int itemDurability = 1;

    public OneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability);
    }    
}