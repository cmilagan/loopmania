package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * groups all Defence Items together
 */
public class DefenceItem extends BattleItem {
    /**
     * every defence item must have these stats
     */
    private double defencePercentage;
    private double critDefencePercentage;
    private double specialDefencePercentage;
    
    public DefenceItem(SimpleIntegerProperty x, SimpleIntegerProperty y, int itemCost, int itemDurability, double defencePercentage, double critDefencePercentage, double specialDefencePercentage) {
        super(x, y, itemCost, itemDurability);
        this.defencePercentage = defencePercentage;
        this.critDefencePercentage = critDefencePercentage;
        this.specialDefencePercentage = specialDefencePercentage;
    }

    /**
     * getDefence() and getCritDefence() is for testing purposes only
     */
    public double getDefence() {
        return defencePercentage;
    }

    public double getCritDefence() {
        return critDefencePercentage;
    }

    public double useDefence() { 
        this.incrementUsage();
        return defencePercentage;
    }

    public double useCritDefence() { 
        this.incrementUsage();
        return critDefencePercentage;
    }

    public double useSpecialDefence() {
        this.incrementUsage();
        return specialDefencePercentage;
    }
}
