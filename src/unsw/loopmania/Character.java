package unsw.loopmania;

import unsw.loopmania.items.Armor;
import unsw.loopmania.items.AttackItem;
import unsw.loopmania.items.Helmet;
import unsw.loopmania.items.Shield;
import unsw.loopmania.npcs.BasicEnemy;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    // TODO = potentially implement relationships between this class and other classes
    private int gold = 0;
    private int health = 100;
    private int maxHealth = 100;
    private AttackItem equippedWeapon;            // a list of items that are equipped by the user
    private Helmet equippedHelmet;
    private Armor equippedArmor;
    private Shield equippedShield;
    private int xp = 0;

    public Character(PathPosition position) {
        super(position);
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
            damageDealt = 3 * (enemyDamage - this.getTotalDefence() - this.getTotalCritDefence());
        } else {
            damageDealt = enemyDamage - this.getTotalDefence();
        }
        this.setHealth(Math.max(0, this.health - damageDealt));
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }

    public void setGold(int newGold) {
        gold = newGold;
    }

    public void setMaxHealth(int newMaxHealth) {
        this.maxHealth = newMaxHealth;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public Helmet getHelmet() {
        return equippedHelmet;
    }

    public AttackItem getWeapon() {
        return equippedWeapon;
    }

    public Armor getArmor() {
        return equippedArmor;
    }

    public Shield getShield() {
        return equippedShield;
    }

    public void setHelmet(Helmet helmet) {
        this.equippedHelmet = helmet;
    }

    public void setWeapon(AttackItem weapon) {
        this.equippedWeapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.equippedArmor = armor;
    }

    public void setShield(Shield shield) {
        this.equippedShield = shield;
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

    public int getDamage() {
        if (equippedWeapon == null) return 1;
        else return equippedWeapon.getDamage();
    }
    
     public int getXP() {
        return this.xp;
    }

    public void setXP(int xp) {
        this.xp = xp;
    }
}
