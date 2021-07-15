package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Zombie extends BasicEnemy implements Enemies {
    private static int damage = 8;
    private static int health = 10;
    private static int experience = 100;
    private static int battleRadius = 1;
    private static int supportRadius = 2;

    public Zombie(PathPosition position) {
        super(position);
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
}
