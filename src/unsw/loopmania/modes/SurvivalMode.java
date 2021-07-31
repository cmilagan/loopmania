package unsw.loopmania.modes;

public class SurvivalMode {
    // We can add more conditions for winning and other restrictions
    private int winLoop;
    private int winXP;
    private int winGold;
    private int maxHealthPotion;

    // Numbers are found in the assumptions
    public SurvivalMode() {
        this.winLoop = 20;
        this.winXP = 10000;
        this.winGold = 1000;
        this.maxHealthPotion = 1;
    }

    /**
     * Gets the winning loop condition
     * @return int
     */
    public int getWinLoop() {
        return this.winLoop;
    }

    /**
     * Gets the winning XP condition
     * @return int
     */
    public int getWinXP() {
        return this.winXP;
    }

    /**
     * Gets the winning gold condition
     * @return int
     */
    public int getWinGold() {
        return this.winGold;
    }

    /**
     * Gets the max health potion condition
     * @return int
     */
    public int getMaxHealthPotion() {
        return this.maxHealthPotion;
    }
}