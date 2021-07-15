package unsw.loopmania.npcs;

/**
 * all methods which each enemy should be able to handle
 */
public interface Enemies {
    public int getHealth();
    public int getDamage();
    public int getExperience();
    public int getBattleRadius();
    public int getSupportRadius();
    public boolean rollCrit();
}
