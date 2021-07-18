package unsw.loopmania.modes;

public class BerserkerMode extends GameDifficulty{
    // We can add more conditions for winning and other restrictions
    private int winLoop;
    private int winXP;
    private int winGold;
    private int maxProtection;

    // Numbers are found in the assumptions
    public BerserkerMode() {
        this.winLoop = 18;
        this.winXP = 8000;
        this.winGold = 850;
        this.maxProtection = 1;
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
     * Gets the max protection condition
     * @return int
     */
    public int getMaxProtection() {
        return this.maxProtection;
    }
}