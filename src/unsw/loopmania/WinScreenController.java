package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Controller for the win screen
 */
public class WinScreenController {
    /**
     * object handling switching to the main menu
     */
    private MenuSwitcher mainMenuSwitcher;
    
    public void setMainMenuSwitcher(MenuSwitcher gameSwitcher){
        this.mainMenuSwitcher = gameSwitcher;
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        mainMenuSwitcher.switchMenu();
    }
}
