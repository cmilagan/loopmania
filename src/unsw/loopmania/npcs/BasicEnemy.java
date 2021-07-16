package unsw.loopmania.npcs;

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
    public void move(){
        // TODO = modify this, since this implementation doesn't provide the expected enemy behaviour
        // this basic enemy moves in a random direction... 25% chance up or down, 50% chance not at all...
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

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

    public int applyCharacterDamage(Character character) {
        int damageDealt = character.getDamage();
        return Math.min(0, this.health - damageDealt);
    }

    public boolean rollCrit() {
        return false;
    }
}
