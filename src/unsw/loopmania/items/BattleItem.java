package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * groups all Battle Items together
 */
public class BattleItem extends Item {
    /**
     * every battle item must have these stats
     */
    private int itemCost;
    private int itemDurability;

    public BattleItem(SimpleIntegerProperty x, SimpleIntegerProperty y, int itemCost, int itemDurability) {
        super(x, y);
        this.itemCost = itemCost;
        this.itemDurability = itemDurability;
    }

    public int getItemCost() {
        return itemCost;
    }

    public int getItemDurability() {
        return itemDurability;
    }
}

