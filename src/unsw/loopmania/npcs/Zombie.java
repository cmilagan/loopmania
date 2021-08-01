package unsw.loopmania.npcs;

import java.util.Random;

import unsw.loopmania.PathPosition;

public class Zombie extends BasicEnemy {
    
    public Zombie(PathPosition position) {
        super(position, 8, 10, 100, 1, 2);
    }

    /**
     * Implements Made Assumption:
     * 
     * When spawned, Zombie will move 2 blocks up then return to spawning position 
     * then move 2 blocks down, then return back to the spawning position and so on. 
     * 
     * At each tick, the Zombie will move 1 step towards their travelling direction. 
     */
    private int tempDistanceValue = 2;
    private int distanceTravelled = 0;
    private boolean travellingUpwards = true;

    /**
     * move up until upper bound is reached
     * 
     * move down until lower bound is reached
     */
    @Override
    public void move() {
        // check if Zombie is at either bound
        if ((Math.abs(distanceTravelled - tempDistanceValue) != 2) && (distanceTravelled % 2) == 0) {
            if (travellingUpwards) {
                travellingUpwards = false;
                moveDownPath();
            } else {
                travellingUpwards = true;
                moveUpPath();
            }

            tempDistanceValue = distanceTravelled;
        } else {
            // continue with existing path
            if (travellingUpwards) {
                moveUpPath();
            } else {
                moveDownPath();
            }
        }

        distanceTravelled++;
    }

    @Override
    /**
     * A Zombie has a 20% chance of critical attack.
     */
    public boolean rollCrit() {
       return Math.random() <= 0.2; 
    }

}
