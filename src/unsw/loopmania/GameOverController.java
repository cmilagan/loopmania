package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import unsw.loopmania.soundplayer.LoopManiaSound;
import unsw.loopmania.soundplayer.LoopManiaSoundPlayer;

public class GameOverController {
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;

    
    /**
     * 
     * @param gameSwitcher
     */
    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }
    
    /**
     * Goes to main menu when button clicked
     * @throws IOException
     */
    @FXML
    public void switchToMainMenu() throws IOException {
        LoopManiaSoundPlayer.playSoundEffect(LoopManiaSound.GAME_OVER);
        gameSwitcher.switchMenu();
    }
}
