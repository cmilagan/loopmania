package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * groups all Defence Items together
 */
public class DefenceItem extends BattleItem {
    /**
     * every defence item must have these stats
     */
    private int defencePercentage;
    private int critDefencePercentage;
    
    public DefenceItem(SimpleIntegerProperty x, SimpleIntegerProperty y, int itemCost, int itemDurability, int defencePercentage, int critDefencePercentage) {
        super(x, y, itemCost, itemDurability);
        this.defencePercentage = defencePercentage;
        this.critDefencePercentage = critDefencePercentage;
    }

    /**
     * getDefence() and getCritDefence() is for testing purposes only
     */
    public int getDefence() {
        return defencePercentage;
    }

    public int getCritDefence() {
        return critDefencePercentage;
    }

    public int useDefence() { return 0; }

    public int useCritDefence() { return 0; }
}
