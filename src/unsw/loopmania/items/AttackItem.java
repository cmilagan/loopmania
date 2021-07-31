package unsw.loopmania.items;

import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;

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
    /**
     * Inflicts damage on enemy, and increases usage.
     * 
     * If usage reaches item durability, return item damage as 0.
     */
    public int inflictDamage() {
        this.incrementUsage();
        return damage;
    }

    @Override
    public Pair<Integer,Integer> getAppropiateSlot() {
        return new Pair<Integer,Integer>(0, 1);
    }
}
