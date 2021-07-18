package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
/**
 * represents an equipped or unequipped shield in the backend world
 */
public class Shield extends DefenceItem {
    private int usage = 0;
    private static int itemCost = 10;
    private static int itemDurability = 5;
    private static int defencePercentage = 20;
    private static int critDefencePercentage = 60;

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage);
    }

    @Override
    public int getUsage() {
        return usage;
    }

    @Override
    public int useDefence() {
        usage++;
        return defencePercentage;
    }

    @Override
    public int useCritDefence() {
        usage++;
        return critDefencePercentage;
    }
}