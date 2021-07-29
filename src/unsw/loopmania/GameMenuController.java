package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class GameMenuController {
    @FXML
    private Button standard;

    @FXML
    private Button survival;

    @FXML
    private Button berserker;

    @FXML
    private Button confusing;

    /**
     * object handling switching to the new game menu
     */
    private MenuSwitcher newGameMenuSwitcher;

    @FXML
    private Text statusField;

    // public ShopMenuController(LoopManiaWorld world) {
    //     this.world = world;
    // }
    
    
    
    @FXML
    private void selectStandard() {
        statusField.setText("This mode has no effects on the game");

    }

    @FXML
    private void selectSurvival() {
        statusField.setText("This mode limits the potions you can buy at the castle to 1");

    }

    @FXML
    private void selectBerserker() {
        statusField.setText("This mode limits the protective gear you can buy at the castle to 1");

    }

    @FXML
    private void selectConfusing() {
        statusField.setText("This mode gives rare items another random rare item effect");

    }

    public void setMenuSwitcher(MenuSwitcher gameSwitcher){
        this.newGameMenuSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToNewGameMenu() throws IOException {
        newGameMenuSwitcher.switchMenu();
    }
}
