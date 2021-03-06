package unsw.loopmania.items;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
/**
 * represents an equipped or unequipped shield in the backend world
 */
public class Shield extends DefenceItem implements EquipItem {
    private static int itemCost = 10;
    private static int itemDurability = 5;
    private static double defencePercentage = 0.2;
    private static double critDefencePercentage = 0.6;

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage, 0);
    }

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y, int itemCost, int itemDurability, double defencePercentage, double critDefencePercentage, double specialDefencePercentage) {
        super(x, y, itemCost, itemDurability, defencePercentage, critDefencePercentage, specialDefencePercentage);
    }

    @Override
    public Pair<Integer,Integer> getAppropiateSlot() {
        return new Pair<Integer,Integer>(3, 2);
    }

}