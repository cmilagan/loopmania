package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

/**
 * represents an equipped or unequipped helmt in the backend world
 */
public class Helmet extends DefenceItem {
    private static int itemCost = 10;
    private static int itemDurability = 10;
    private static int defencePercentage = 10;
    private static int critDefencePercentage = 0;

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage);
    }    
}