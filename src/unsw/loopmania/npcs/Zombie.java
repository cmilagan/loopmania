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
}
