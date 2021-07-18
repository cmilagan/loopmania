package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped helmt in the backend world
 */
public class Helmet extends DefenceItem {
    private int usage = 0;
    private static int itemCost = 10;
    private static int itemDurability = 10;
    private static double defencePercentage = 0.1;
    private static double critDefencePercentage = 0;

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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