package unsw.loopmania.npcs;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;

public class ElanMuske extends BasicEnemy {

    public ElanMuske(PathPosition position) {
        super(position, 25, 40, 500, 1, 1, true);
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

    @Override
    public void applyEnemyEffects(Character character, boolean inBattle, List<BasicEnemy> enemies) {
        if (!inBattle) {
            for (BasicEnemy e : enemies) {
                boolean isInSupportRadius = Math.sqrt(Math.pow((e.getX() - this.getX()), 2) + Math.pow((e.getY() - this.getY()), 2)) <= this.getSupportRadius();
                if (!e.equals(this) && isInSupportRadius) {
                    e.heal();
                }
            }
        }
    }
}
