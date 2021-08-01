package unsw.loopmania.items;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped helmt in the backend world
 */
public class Helmet extends DefenceItem implements EquipItem {
    private static int itemCost = 10;
    private static int itemDurability = 10;
    private static double defencePercentage = 0.1;
    private static double critDefencePercentage = 0;

    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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

    @Override
    public Pair<Integer,Integer> getAppropiateSlot() {
        return new Pair<Integer,Integer>(1, 1);
    }

}