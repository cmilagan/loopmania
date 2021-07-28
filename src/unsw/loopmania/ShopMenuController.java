package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ShopMenuController {
    /**
     * facilitates switching to main game
     */
    private int armorID = 0;
    private int helmetID = 1;
    private int shieldID = 2;
    private int staffID = 3;
    private int stakeID = 4;
    private int swordID = 5;
    private int oneRingID = 6;
    private int healthPotionID = 7;
    private LoopManiaWorld world;
    private MenuSwitcher gameSwitcher;

    @FXML
    private Text statusField;

    public ShopMenuController(LoopManiaWorld world) {
        this.world = world;
    }
    
    @FXML
    private void swordBuy() {
        if (world.buyItemByID(swordID)) {
            statusField.setText("You bought a Sword!");
        } else {
            statusField.setText("Insufficient funds to buy a Sword!");
        }
    }

    @FXML
    private void swordSell() {
        
    }

    @FXML
    private void staffBuy() {
        world.buyItemByID(staffID);
    }

    @FXML
    private void staffSell() {
        
    }

    @FXML
    private void stakeBuy() {
        world.buyItemByID(stakeID);
    }

    @FXML
    private void stakeSell() {
        
    }

    @FXML
    private void doggieCoinSell() {
        
    }

    @FXML
    private void shieldBuy() {
        world.buyItemByID(shieldID);
    }

    @FXML
    private void shieldSell() {
        
    }

    @FXML
    private void helmetBuy() {
        world.buyItemByID(helmetID);
    }

    @FXML
    private void helmetSell() {
        
    }

    @FXML
    private void armourBuy() {
        world.buyItemByID(armorID);
    }

    @FXML
    private void armourSell() {
        
    }

    @FXML
    private void healthPotionBuy() {
        world.buyItemByID(healthPotionID);
    }

    @FXML
    private void healthPotionSell() {
        
    }

    /**
     * remembers the game to return back to
     * @param gameSwitcher
     */
    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGameMenu() throws IOException {
        gameSwitcher.switchMenu();
    }
}