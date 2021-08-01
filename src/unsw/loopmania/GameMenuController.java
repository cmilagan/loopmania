package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * controller for the main menu.
 */
public class GameMenuController {

    private LoopManiaWorld world;
    /**
     * object handling switching to the new game menu
     */
    private MenuSwitcher newGameMenuSwitcher;
    private MenuSwitcher backButton;

    @FXML
    private Text statusField;

    public GameMenuController(LoopManiaWorld world2) {
        this.world = world2;
    }
    
    public void setBackMenuSwitcher(MenuSwitcher gameSwitcher){
        this.backButton = gameSwitcher;
    }

    @FXML
    private void backButton() {
        backButton.switchMenu();
    }
    
    // Button that makes the mode standard
    @FXML
    private void selectStandard() {
        statusField.setText("This mode has no effects on the game");
        world.playStandard();
    }

    @FXML
    private void selectSurvival() {
        statusField.setText("This mode limits the potions you can buy at the castle to 1");
        world.playSurvival();
    }

    @FXML
    private void selectBerserker() {
        statusField.setText("This mode limits the protective gear you can buy at the castle to 1");
        world.playBerserker();
    }

    @FXML
    private void selectConfusing() {
        statusField.setText("This mode gives rare items another random rare item effect");
        world.playConfusing();
    }

    public void setMenuSwitcher(MenuSwitcher gameSwitcher){
        this.newGameMenuSwitcher = gameSwitcher;
    }

    @FXML
    private void switchToMapMenu() throws IOException {
        newGameMenuSwitcher.switchMenu();
    }
}
