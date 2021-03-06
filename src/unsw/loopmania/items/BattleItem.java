package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * groups all Battle Items together
 */
public class BattleItem extends Item {
    /**
     * every battle item must have these stats
     */
    private int usage;
    private int itemCost;
    private int itemDurability;

    public BattleItem(SimpleIntegerProperty x, SimpleIntegerProperty y, int itemCost, int itemDurability) {
        super(x, y);
        this.itemCost = itemCost;
        this.itemDurability = itemDurability;
        this.usage = 0;
    }

    public int getItemCost() {
        return itemCost;
    }

    public int getItemDurability() {
        return itemDurability;
    }

    public int getUsage() {
        return usage;
    }

    public void incrementUsage() {
        this.usage++;
    }
}

