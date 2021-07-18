package unsw.loopmania.npcs;

import java.util.List;
import java.util.Random;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity {
    private int damage;
    private int health;
    private int experience;
    private int battleRadius;
    private int supportRadius;

    /**
     * Spawn an Enemy at position, with specified damage, health, experience, battleRadius, supportRadius
     * @param position
     * @param damage
     * @param health
     * @param experience
     * @param battleRadius
     * @param supportRadius
     */
    public BasicEnemy(PathPosition position, int damage, int health, int experience, int battleRadius, int supportRadius) {
        super(position);
        this.damage = damage;
        this.health = health;
        this.experience = experience;
        this.battleRadius = battleRadius;
        this.supportRadius = supportRadius;
    }

    /**
     * move the enemy
     */
    public void move() {}

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getExperience() {
        return experience;
    }

    public int getBattleRadius() {
        return battleRadius;
    }

    public int getSupportRadius() {
        return supportRadius;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int applyCharacterDamage(Character character, List<AlliedSoldier> alliedSoldiers) {
        int damageDealt = character.getCharDamage();
        for (AlliedSoldier s: alliedSoldiers) {
            
        }
        return Math.min(0, this.health - damageDealt);
    }

    public void applyTrapDamage(int dmg) {
        setHealth(getHealth() - dmg);
    }

    public boolean rollCrit() {
        return false;
    }
}
