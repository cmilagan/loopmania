package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Vampire extends BasicEnemy implements Enemies {
    private static int damage = 8;
    private static int health = 10;
    private static int experience = 100;
    private static int battleRadius = 2;
    private static int supportRadius = 3;

    public Vampire(PathPosition position) {
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
     * When spawned, Vampire will move 3 blocks up then return to spawning position 
     * then move 3 blocks down, then return back to the spawning position and so on.
     *  
     * At each tick, the Vampire will move 1 step towards their travelling direction. 
     */
    @Override
    public void move() {
        
    }
}
