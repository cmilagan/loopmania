package unsw.loopmania.npcs;

import java.util.Random;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;

/**
 * a basic form of enemy in the world
 */
public class BasicEnemy extends MovingEntity implements Enemies {
    // TODO = modify this, and add additional forms of enemy
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
}
