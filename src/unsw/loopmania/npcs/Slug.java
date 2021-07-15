package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Slug extends BasicEnemy implements EnemyInterface {
    private int health = 3;
    private static int damage = 5;
    private static int experience = 50;
    private static int battleRadius = 1;
    private static int supportRadius = 1;

    public Slug(PathPosition position) {
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
