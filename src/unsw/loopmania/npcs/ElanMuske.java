package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class ElanMuske extends BasicEnemy {

    public ElanMuske(PathPosition position) {
        super(position, 25, 40, 500, 1, 1);
    }

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

        // check if ElanMuske is at either bound
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
