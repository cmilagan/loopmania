package unsw.loopmania.items;

import org.javatuples.Pair;

public interface EquipItem {
    /**
     * 
     * @param targetX
     * @param targetY
     * @return the coordinates of the slot to be placed
     */
    public Pair<Integer,Integer> getAppropiateSlot();
}
