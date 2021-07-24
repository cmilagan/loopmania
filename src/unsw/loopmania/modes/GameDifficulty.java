package unsw.loopmania.modes;

public class GameDifficulty {
    private Boolean survivalMode;
    private Boolean standardMode;
    private Boolean berserkerMode;
    private Boolean confusingMode;

    public GameDifficulty() {
        this.standardMode = false;
        this.survivalMode = false;
        this.berserkerMode = false;
        this.confusingMode = false;
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

    public Boolean getConfusing() {
        return confusingMode;
    }

    public void playStandard() {
        standardMode = true;
        survivalMode = false;
        berserkerMode = false;
        confusingMode = false;
    }

    public void playSurvival() {
        standardMode = false;
        survivalMode = true;
        berserkerMode = false;
        confusingMode = false;
    }

    public void playBerserker() {
        standardMode = false;
        survivalMode = false;
        berserkerMode = true;
        confusingMode = false;
    }
    
    public void playConfusing() {
        standardMode = false;
        survivalMode = false;
        berserkerMode = false;
        confusingMode = true;
    }
}
