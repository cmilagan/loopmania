package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped armor in the backend world
 */
public class Armor extends DefenceItem {
    private int usage = 0;
    private static int itemCost = 20;
    private static int itemDurability = 10;
    private static double defencePercentage = 0.4;
    private static double critDefencePercentage = 0;

    public Armor(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage);
    }    

    @Override
    public int getUsage() {
        return usage;
    }

    @Override
    public double useDefence() {
        usage++;
        return defencePercentage;
    }

    @Override
    public double useCritDefence() {
        return critDefencePercentage;
    }
}