package unsw.loopmania.npcs;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;

public class AlliedSoldier extends MovingEntity {
    private int health = 3;
    private int damage = 5;

    public AlliedSoldier(PathPosition position) {
        super(position);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    /**
     * Function that calculates and applies enemy damage Critical attack and armor
     * effects are taken into account
     */
    public int applyEnemyDamage(BasicEnemy enemy) {
        int enemyDamage = enemy.getDamage();
        boolean enemyCrit = enemy.rollCrit();
        if (enemy instanceof Zombie && enemyCrit) {
            return -1;
        } 
        int damageDealt;
        if (enemyCrit) damageDealt = 3 * enemyDamage;
        else damageDealt = enemyDamage;
        this.setHealth(Math.max(0, this.health - damageDealt));
        return health;
    }

}
