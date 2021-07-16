package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int health = 100;
    private int xp = 0;
    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }
    
    public int getXP() {
        return this.xp;
    }

    public void setXP(int xp) {
        this.xp = xp;
    }
}
