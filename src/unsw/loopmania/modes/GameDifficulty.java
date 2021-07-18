package unsw.loopmania.modes;

public class GameDifficulty {
    private Boolean survivalMode;
    private Boolean standardMode;
    private Boolean berserkerMode;

    public GameDifficulty() {
        this.standardMode = false;
        this.survivalMode = false;
        this.berserkerMode = false;
    }

    public Boolean getStandard() {
        return standardMode;
    }

    public Boolean getSurvival() {
        return survivalMode;
    }

    public Boolean getBerserker() {
        return berserkerMode;
    }

    public void playStandard() {
        standardMode = true;
    }

    public void playSurvival() {
        survivalMode = true;
    }

    public void playBerserker() {
        berserkerMode = true;
    }
}
