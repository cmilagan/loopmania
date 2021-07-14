package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Slug extends BasicEnemy implements EnemyMethods {
    private int battleRadius;
    private int supportRadius;
    public Slug(PathPosition position) {
        super(position);
        this.battleRadius = 1;
        this.supportRadius = 1;
    }

    public int getBattleRadius() {
        return battleRadius;
    }

    public int getSupportRadius() {
        return supportRadius;
    }
}
