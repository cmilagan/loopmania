package unsw.loopmania.npcs;

import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;

public class AlliedSoldier extends MovingEntity {
    private int health = 3;
    private int damage = 5;

    public AlliedSoldier(PathPosition position) {
        super(position);
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
    
}
