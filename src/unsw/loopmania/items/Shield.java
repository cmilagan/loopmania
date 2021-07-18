package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
/**
 * represents an equipped or unequipped shield in the backend world
 */
public class Shield extends DefenceItem {
    private int usage = 0;
    private static int itemCost = 10;
    private static int itemDurability = 5;
    private static double defencePercentage = 0.2;
    private static double critDefencePercentage = 0.6;

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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
        usage++;
        return critDefencePercentage;
    }
}