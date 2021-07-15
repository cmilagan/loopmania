package unsw.loopmania;

import unsw.loopmania.items.AttackItem;
import unsw.loopmania.items.DefenceItem;
import unsw.loopmania.npcs.BasicEnemy;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int health = 100;
    // a list of items that are equipped by the user
    private AttackItem equippedWeapon;
    private DefenceItem equippedHelmet;
    private DefenceItem equippedArmor;
    private DefenceItem equippedShield;

    public Character(PathPosition position) {
        super(position);
    }

    public int calculateWeaponDamage() {
        if (equippedWeapon == null) return 1;
        else return equippedWeapon.getDamage();
    }

    /**
     * Function that calculates and applies enemy damage
     * Critical attack and armor effects are taken into account
     */
    public int applyEnemyDamage(BasicEnemy enemy) {
        int enemyDamage = enemy.getDamage();
        boolean enemyCrit = enemy.rollCrit();
        int damageDealt;
        if (enemyCrit) {
            damageDealt = 3 * enemyDamage * this.getTotalDefence() * this.getTotalCritDefence();
        } else {
            damageDealt = enemyDamage * this.getTotalDefence();
        }
        this.setHealth(Math.max(0, this.health - damageDealt));
        return health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    private int getTotalDefence() {
        int totalDefence = 0;
        if (this.equippedArmor != null) totalDefence += this.equippedArmor.getDefence();
        if (this.equippedHelmet != null) totalDefence += this.equippedHelmet.getDefence();
        if (this.equippedShield != null) totalDefence += this.equippedShield.getDefence();
        return totalDefence;
    }
    
    private int getTotalCritDefence() {
        int totalCritDefence = 0;
        if (this.equippedArmor != null) totalCritDefence += this.equippedArmor.getCritDefence();
        if (this.equippedHelmet != null) totalCritDefence += this.equippedHelmet.getCritDefence();
        if (this.equippedShield != null) totalCritDefence += this.equippedShield.getCritDefence();
        return totalCritDefence;
    }
}
