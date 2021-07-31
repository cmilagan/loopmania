package unsw.loopmania.modes;

public class StandardMode {
    // We can add more conditions for winning and other restrictions
    private int winLoop;
    private int winXP;
    private int winGold;

    // Numbers are found in the assumptions
    public StandardMode() {
        this.winLoop = 40;
        this.winXP = 20000;
        this.winGold = 2000;
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
}
