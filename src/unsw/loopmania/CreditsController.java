package unsw.loopmania;

import javafx.fxml.FXML;
import unsw.loopmania.soundplayer.LoopManiaSound;
import unsw.loopmania.soundplayer.LoopManiaSoundPlayer;

/**
 * controller for credits.
 * 
 */
public class CreditsController {
    /**
     * object handling switching to the main menu
     */
    private MenuSwitcher backButton;

    public void setBackMenuSwitcher(MenuSwitcher gameSwitcher){
        this.backButton = gameSwitcher;
    }

    @FXML
    private void backButton() {
        LoopManiaSoundPlayer.playSoundEffect(LoopManiaSound.CLICK);
        backButton.switchMenu();
    }
}
