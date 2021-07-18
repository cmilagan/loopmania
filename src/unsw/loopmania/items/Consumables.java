package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * groups all Consumables Items together
 */
public class Consumables extends BattleItem {
    private static int itemDurability = 1;

    public Consumables(SimpleIntegerProperty x, SimpleIntegerProperty y, int itemCost) {
        super(x, y, itemCost, itemDurability);
    }
}
