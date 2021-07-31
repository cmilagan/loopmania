package unsw.loopmania;

import javafx.fxml.FXML;

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
        backButton.switchMenu();
    }
}
