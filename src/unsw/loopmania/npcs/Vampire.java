package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Vampire extends BasicEnemy implements EnemyMethods {
    int battleRadius;
    int supportRadius;
    public Vampire(PathPosition position) {
        super(position);
        this.battleRadius = 3;
        this.supportRadius = 4;
    }
    
    public int getBattleRadius() {
        return battleRadius;
    }

    public int getSupportRadius() {
        return supportRadius;
    }
}
