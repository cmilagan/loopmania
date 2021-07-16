package unsw.loopmania.npcs;

import java.util.List;
import java.util.Random;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity implements EnemyInterface {
    private int health = 1;
    private static int damage = 1;
    private static int experience = 1;
    private static int battleRadius = 1;
    private static int supportRadius = 1;

    public BasicEnemy(PathPosition position) {
        super(position);
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
        return 0;
    }

    public int getDamage() {
        return 0;
    }

    public int getExperience() {
        return 0;
    }
    
    public int getBattleRadius() {
        return 0;
    }

    public int getSupportRadius() {
        return 0;
    }

    public int applyCharacterDamage(Character character, List<AlliedSoldier> alliedSoldiers) {
        int damageDealt = character.getDamage();
        for (AlliedSoldier s: alliedSoldiers) {
            
        }
        return Math.min(0, this.health - damageDealt);
    }

    public boolean rollCrit() {
        return false;
    }
}
