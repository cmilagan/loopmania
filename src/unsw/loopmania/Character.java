package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity {
    private int gold = 0;
    private int health = 100;

    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }

    public void setGold(int newGold) {
        gold = newGold;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }
}
