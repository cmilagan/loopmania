package unsw.loopmania.items;

public interface EquipItem {
    /**
     * 
     * @param targetX
     * @param targetY
     * @return boolean: if item can be placed in targeted slot
     */
    public boolean equipToCoordinates(int targetX, int targetY);
}
