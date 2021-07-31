package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.items.Armor;
import unsw.loopmania.items.AttackItem;
import unsw.loopmania.items.Helmet;
import unsw.loopmania.items.Item;
import unsw.loopmania.items.OneRing;
import unsw.loopmania.items.Shield;
import unsw.loopmania.npcs.BasicEnemy;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private SimpleIntegerProperty gold = new SimpleIntegerProperty(500);
    private int doggieCoin = 0;
    private int health = 100;
    private int maxHealth = 100;
    private AttackItem equippedWeapon;            // a list of items that are equipped by the user
    private Helmet equippedHelmet;
    private Armor equippedArmor;
    private Shield equippedShield;
    private boolean stunned = false;
    private int damage = 1;
    private int xp = 0;
    private List<Entity> inventoryItems;

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
        if (enemyCrit) damageDealt = (int)(3 * enemyDamage * this.getTotalDefenceMultiplier() * this.getTotalCritDefenceMultiplier());
        else damageDealt = (int)(enemyDamage * this.getTotalDefenceMultiplier());
        this.setHealth(Math.max(0, this.health - damageDealt));
        return health;
    }

    public void toggleStun() {
        stunned = !stunned;
    }

    public boolean isStunned() {
        return stunned;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getGold() {
        return gold.get();
    }

    public SimpleIntegerProperty getSimpleIntegerGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }

    public int getXP() {
        return xp;
    }

    public int getDoggieCoin() {
        return doggieCoin;
    }

    public void setGold(int newGold) {
        gold.set(newGold);;
    }

    public void incrementDoggieCoin() {
        doggieCoin += 1;
    }

    public void setMaxHealth(int newMaxHealth) {
        this.maxHealth = newMaxHealth;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public void setInventory(List<Entity> itemInventory) {
        inventoryItems = itemInventory;
    }

    public void setXP(int newXP) {
        xp = newXP;
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

    public double getTotalDefenceMultiplier() {
        double multiplier = 1;
        if (this.equippedArmor != null) multiplier -= this.equippedArmor.useDefence();
        if (this.equippedHelmet != null) multiplier -= this.equippedHelmet.useDefence();
        if (this.equippedShield != null) multiplier -= this.equippedShield.useDefence();
        return multiplier;
    }
    

    public double getTotalCritDefenceMultiplier() {
        double multiplier = 1;
        if (this.equippedArmor != null) multiplier -= this.equippedArmor.useCritDefence();
        if (this.equippedHelmet != null) multiplier -= this.equippedHelmet.useCritDefence();
        if (this.equippedShield != null) multiplier -= this.equippedShield.useCritDefence();
        return multiplier;
    }

    public int getDamage() {
        if (stunned) return 0;
        if (equippedWeapon == null) return 1;
        else return equippedWeapon.inflictDamage();
    }

    public int getCharDamage() {
        return damage;
    }

    public void setDamage(int newDmg) {
        this.damage = newDmg;
    }

    public Pair getCoordinatePair() {
        return new Pair<Integer, Integer>(this.getX(), this.getY());
    }

    public boolean useOneRing() {
        boolean consumed = false;
        for (Entity entity: inventoryItems) {
            if (entity instanceof OneRing) {
                OneRing ring = (OneRing) entity;
                this.setHealth(maxHealth);
                inventoryItems.remove(ring);
                ring.destroy();
                consumed = true;
                break;                
            }
        }
        return consumed;
    }
}
