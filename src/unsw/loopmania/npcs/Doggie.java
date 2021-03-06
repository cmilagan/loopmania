package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

import java.util.List;

import unsw.loopmania.Character;

public class Doggie extends BasicEnemy {
    public Doggie(PathPosition position) {
        super(position, 0, 20, 100, 1, 1, true);
    }

    private int distanceTravelled = 0;
    private boolean travellingUpwards = true;

    /**
     * Movement similar to Slug.
     */
    @Override
    public void move() {
        distanceTravelled++;

        // check if Doggie is at either bound
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

    /**
     * Stuns the character
     */
    @Override
    public void applyEnemyEffects(Character c, boolean inBattle, List<BasicEnemy> enemies) {
        if (inBattle && this.getHealth() > 0) {
            c.toggleStun();
        }
    }
}
