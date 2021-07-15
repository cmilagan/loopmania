package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Vampire extends BasicEnemy {
    
    public Vampire(PathPosition position) {
        super(position, 20, 20, 200, 2, 3);
    }

    /**
     * Implements Made Assumption:
     * 
     * When spawned, Vampire will move 3 blocks up then return to spawning position 
     * then move 3 blocks down, then return back to the spawning position and so on.
     *  
     * At each tick, the Vampire will move 1 step towards their travelling direction. 
     */
    private int tempDistanceValue = 3;
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
        if ((Math.abs(distanceTravelled - tempDistanceValue) != 3) && (distanceTravelled % 3) == 0) {
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
}
