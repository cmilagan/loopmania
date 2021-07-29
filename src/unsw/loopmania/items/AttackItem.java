package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * groups all Attack Items together
 */
public class AttackItem extends BattleItem implements EquipItem {
    /**
     * every attack item must have these stats
     */
    private int damage;

    public AttackItem(SimpleIntegerProperty x, SimpleIntegerProperty y, int itemCost, int itemDurability, int damage) {
        super(x, y, itemCost, itemDurability);
        this.damage = damage;
    }

    /**
     * getDamage() is for testing purposes only,
     * use inflictDamage() to correctly apply damage
     */
    public int getDamage() {
        return this.damage;
    }

    public int inflictDamage() { return 0; }

    @Override
    public boolean equipToCoordinates(int targetX, int targetY) {
        if (targetX == 0 && targetY == 1) return true;
        return false;
    }
}
