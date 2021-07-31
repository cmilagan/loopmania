package unsw.loopmania.npcs;

import java.util.List;

import unsw.loopmania.Character;
import unsw.loopmania.PathPosition;
import unsw.loopmania.items.Stake;

public class Vampire extends BasicEnemy {
    
    public Vampire(PathPosition position) {
        super(position, 20, 20, 200, 2, 3, 20);
    }

    /**
     * Implements Made Assumption:
     * 
     * When spawned, Vampire will move 3 blocks up then return to spawning position 
     * then move 3 blocks down, then return back to the spawning position and so on.
     *  
     * At each tick, the Vampire will move 1 step towards their travelling direction. 
     */
    private int tempDistanceValue = 3;
    private int distanceTravelled = 0;
    private boolean travellingUpwards = true;

    /**
     * move up until upper bound is reached
     * 
     * move down until lower bound is reached
     */
    @Override
    public int applyCharacterDamage(Character character, List<AlliedSoldier> alliedSoldiers) {
        int damageDealt = 0;
        
        // if character has stake equipped and is battling Vampire, do additional damage
        if (character.getWeapon() instanceof Stake) {
            Stake stake = (Stake) character.getWeapon();
            damageDealt = stake.getSpecialDamage();
        } else {
            damageDealt = character.getDamage();
        }

        for (AlliedSoldier s: alliedSoldiers) {
            damageDealt += s.getDamage();
        }

        int leftHealth = Math.max(0, this.getHealth() - damageDealt);
        this.setHealth(leftHealth);
        return leftHealth;
    }

    @Override
    public void move() {
        // check if Vampire is at either bound
        if ((Math.abs(distanceTravelled - tempDistanceValue) != 3) && (distanceTravelled % 3) == 0) {
            if (travellingUpwards) {
                travellingUpwards = false;
                moveDownPath();
            } else {
                travellingUpwards = true;
                moveUpPath();
            }

            tempDistanceValue = distanceTravelled;
        } else {
            // continue with existing path
            if (travellingUpwards) {
                moveUpPath();
            } else {
                moveDownPath();
            }
        }

        distanceTravelled++;
    }

}
