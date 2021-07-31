package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped armor in the backend world
 */
public class Armor extends DefenceItem {
    private static int itemCost = 20;
    private static int itemDurability = 10;
    private static double defencePercentage = 0.4;
    private static double critDefencePercentage = 0;

    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage);
    }

    /**
     * Method is overridden here because the crit
     * defence is 0 so we should not increment usage
     */
    @Override
    public double useCritDefence() {
        return this.getCritDefence(); 
    }
}