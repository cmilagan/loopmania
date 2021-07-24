package unsw.loopmania.modes;

public class ConfusingMode extends GameDifficulty {
    // We can add more conditions for winning and other restrictions
    private int winLoop;
    private int winXP;
    private int winGold;

    // Numbers are found in the assumptions
    public ConfusingMode() {
        this.winLoop = 69;
        this.winXP = 420000;
        this.winGold = 6969;
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
