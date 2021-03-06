package unsw.loopmania;

import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.items.Armor;
import unsw.loopmania.items.AttackItem;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Helmet;
import unsw.loopmania.items.OneRing;
import unsw.loopmania.items.Shield;
import unsw.loopmania.items.Staff;
import unsw.loopmania.items.TreeStump;
import unsw.loopmania.npcs.BasicEnemy;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private SimpleIntegerProperty gold = new SimpleIntegerProperty(100);
    private SimpleIntegerProperty doggieCoin = new SimpleIntegerProperty(0);
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
        if (enemyCrit) damageDealt = (int)(3 * enemyDamage * getTotalDefenceMultiplier(enemy.getIsBoss()) * getTotalCritDefenceMultiplier());
        else damageDealt = (int)(enemyDamage * this.getTotalDefenceMultiplier(enemy.getIsBoss()));
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

    /**
     * This function checks if character has Staff equipped and returns a 20% chance of inflicting a trance.
     * A trance inflicted is denoted by returning true. 
     * 
     * If character does not have Staff equipped or chance does not permit trance, return false.
     */
    public boolean inflictStaffTrance() {
        /**
         * A Staff has 20% chance of inflicting trance.
         */
        if (this.getWeapon() instanceof Staff) {
            int seed = 100;
            Random random = new Random(seed);
            int value = random.nextInt(seed);
            return value < 21;
        }

        return false;
    }

    /**
     * returns the number of DoggieCoins character has
     * @return
     */
    public int getDoggieCoin() {
        return doggieCoin.get();
    }

    public void setGold(int newGold) {
        gold.set(newGold);;
    }

    public void incrementDoggieCoin() {
        int coin = doggieCoin.get();
        doggieCoin.set(coin + 1);
    }

    public void decrementDoggieCoin() {
        if (doggieCoin.get() > 0) {
            int coin = doggieCoin.get();
            doggieCoin.set(coin - 1);
        }
    }

    public SimpleIntegerProperty getSimpleIntegerDoggieCoin() {
        return doggieCoin;
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

    public double getTotalDefenceMultiplier(boolean isBoss) {
        double multiplier = 1;
        if (this.equippedArmor != null) multiplier -= this.equippedArmor.useDefence();
        if (this.equippedHelmet != null) multiplier -= this.equippedHelmet.useDefence();
        if (this.equippedShield != null) {
            if (isBoss) multiplier -= this.equippedShield.useSpecialDefence();
            else multiplier -= this.equippedShield.useDefence();
        }
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
                ring.use(this);
                inventoryItems.remove(ring);
                ring.destroy();
                consumed = true;
                break;                
            }
        }
        return consumed;
    }

    public void useHealthPotion() {
        for (Entity entity: inventoryItems) {
            if (entity instanceof HealthPotion) {
                HealthPotion potion = (HealthPotion) entity;
                potion.use(this);
                inventoryItems.remove(potion);
                potion.destroy();
                break;                
            }
        }
    }
}
