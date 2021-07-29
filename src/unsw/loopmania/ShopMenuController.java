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
            statusField.setText("Thank you for purchasing a Sword!");
        } else {
            statusField.setText("Insufficient funds to buy a Sword!");
        }
    }

    @FXML
    private void swordSell() {
        
    }

    @FXML
    private void staffBuy() {
        if (world.buyItemByID(staffID)) {
            statusField.setText("Thank you for purchasing a Staff!");
        } else {
            statusField.setText("Insufficient funds to buy a Staff!");
        }
    }

    @FXML
    private void staffSell() {
        
    }

    @FXML
    private void stakeBuy() {
        if (world.buyItemByID(stakeID)) {
            statusField.setText("Thank you for purchasing a Stake!");
        } else {
            statusField.setText("Insufficient funds to buy a Stake!");
        }
    }

    @FXML
    private void stakeSell() {
        
    }

    @FXML
    private void doggieCoinSell() {
        
    }

    @FXML
    private void shieldBuy() {
        if (world.buyItemByID(shieldID)) {
            statusField.setText("Thank you for purchasing a Shield!");
        } else {
            statusField.setText("Insufficient funds to buy a Shield!");
        }
    }

    @FXML
    private void shieldSell() {
        
    }

    @FXML
    private void helmetBuy() {
        if (world.buyItemByID(helmetID)) {
            statusField.setText("Thank you for purchasing a Helmet!");
        } else {
            statusField.setText("Insufficient funds to buy a Helmet!");
        }
    }

    @FXML
    private void helmetSell() {
        
    }

    @FXML
    private void armourBuy() {
        if (world.buyItemByID(swordID)) {
            statusField.setText("Thank you for purchasing Armour!");
        } else {
            statusField.setText("Insufficient funds to buy Armour!");
        }
    }

    @FXML
    private void armourSell() {
        
    }

    @FXML
    private void healthPotionBuy() {
        if (world.buyItemByID(healthPotionID)) {
            statusField.setText("Thank you for purchasing a Health Potion!");
        } else {
            statusField.setText("Insufficient funds to buy a Health Potion!");
        }
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