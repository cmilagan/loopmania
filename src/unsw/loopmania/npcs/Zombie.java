package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Zombie extends BasicEnemy implements EnemyMethods {
    private int battleRadius;
    private int supportRadius;
    public Zombie(PathPosition position) {
        super(position);
        this.battleRadius = 1;
        this.supportRadius = 2;
    }
    
    public int getBattleRadius() {
        return battleRadius;
    }

    public int getSupportRadius() {
        return supportRadius;
    }
}
