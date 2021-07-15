package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped armor in the backend world
 */
public class Armor extends DefenceItem {
    private static int itemCost = 20;
    private static int itemDurability = 10;
    private static int defencePercentage = 40;
    private static int critDefencePercentage = 0;

    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage);
    }    
}