package unsw.loopmania.npcs;

import unsw.loopmania.PathPosition;

public class Vampire extends BasicEnemy {
    public Vampire(PathPosition position) {
        super(position, 8, 10, 100, 2, 3);
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
