package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Vampire extends BasicEnemy implements Enemies {
    private int battleRadius;
    private int supportRadius;
    public Vampire(PathPosition position) {
        super(position);
        this.battleRadius = 2;
        this.supportRadius = 3;
    }
    
    public int getBattleRadius() {
        return battleRadius;
    }

    public int getSupportRadius() {
        return supportRadius;
    }
}
