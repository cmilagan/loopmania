package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Slug extends BasicEnemy {
    
    public Slug(PathPosition position) {
        super(position, 5, 3, 50, 1, 1, false);
    }

    /**
     * Implements Made Assumption:
     * 
     * When spawned, Slug will move 1 block up then return to spawning position 
     * then move 1 block down, then return back to the spawning position and so on. 
     * 
     * At each tick, the Slug will move 1 step towards their travelling direction. 
     */
    private int distanceTravelled = 0;
    private boolean travellingUpwards = true;

    /**
     * move up until upper bound is reached
     * 
     * move down until lower bound is reached
     */
    @Override
    public void move() {
        distanceTravelled++;

        // check if Slug is at either bound
        if ((distanceTravelled % 2) == 0) {
            if (travellingUpwards) {
                travellingUpwards = false;
                moveDownPath();
            } else {
                travellingUpwards = true;
                moveUpPath();
            }
        } else {
            // continue with existing path
            if (travellingUpwards) {
                moveUpPath();
            } else {
                moveDownPath();
            }
        }
    }

}
