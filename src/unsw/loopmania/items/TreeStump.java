package unsw.loopmania.items;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped shield in the backend world
 */
public class TreeStump extends Shield {
    private static int itemCost = 7000;
    private static int itemDurability = 20;
    private static double defencePercentage = 0.3;
    private static double critDefencePercentage = 0.7;
    private static double specialDefencePercentage = 0.4;

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage, specialDefencePercentage);
    }

    @Override
    public Pair<Integer, Integer> getAppropiateSlot() {
        return new Pair<Integer, Integer>(3, 2);
    }
}