package unsw.loopmania;

import java.io.IOException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableIntegerValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import unsw.loopmania.items.BattleItem;

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
    private LoopManiaWorldController mainController;

    @FXML
    private Text playerGold;

    @FXML
    private Text statusField;

    public ShopMenuController(LoopManiaWorld world, LoopManiaWorldController mainController) {
        this.world = world;
        this.mainController = mainController;

        world.getCharacter().x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                IntegerProperty characterGold = world.getCharacter().getSimpleIntegerGold();
                playerGold.textProperty().bind(characterGold.asString());
            }
        });
    }
    
    @FXML
    private void swordBuy() {
        BattleItem boughtItem = world.buyItemByID(swordID);
        if (boughtItem != null) {
            statusField.setText("Thank you for purchasing a Sword!");
            mainController.onLoad(boughtItem);
        } else {
            statusField.setText("Insufficient funds to buy a Sword!");
        }
    }

    @FXML
    private void swordSell() {
        
    }

    @FXML
    private void staffBuy() {
        BattleItem boughtItem = world.buyItemByID(staffID);
        if (boughtItem != null) {
            statusField.setText("Thank you for purchasing a Staff!");
            mainController.onLoad(boughtItem);
        } else {
            statusField.setText("Insufficient funds to buy a Staff!");
        }
    }

    @FXML
    private void staffSell() {
        
    }

    @FXML
    private void stakeBuy() {
        BattleItem boughtItem = world.buyItemByID(stakeID);
        if (boughtItem != null) {
            statusField.setText("Thank you for purchasing a Stake!");
            mainController.onLoad(boughtItem);
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
        BattleItem boughtItem = world.buyItemByID(shieldID);
        if (boughtItem != null) {
            statusField.setText("Thank you for purchasing a Shield!");
            mainController.onLoad(boughtItem);
        } else {
            statusField.setText("Insufficient funds to buy a Shield!");
        }
    }

    @FXML
    private void shieldSell() {
        
    }

    @FXML
    private void helmetBuy() {
        BattleItem boughtItem = world.buyItemByID(helmetID);
        if (boughtItem != null) {
            statusField.setText("Thank you for purchasing a Helmet!");
            mainController.onLoad(boughtItem);
        } else {
            statusField.setText("Insufficient funds to buy a Helmet!");
        }
    }

    @FXML
    private void helmetSell() {
        
    }

    @FXML
    private void armourBuy() {
        BattleItem boughtItem = world.buyItemByID(armorID);
        if (boughtItem != null) {
            statusField.setText("Thank you for purchasing Armour!");
            mainController.onLoad(boughtItem);
        } else {
            statusField.setText("Insufficient funds to buy Armour!");
        }
    }

    @FXML
    private void armourSell() {
        
    }

    @FXML
    private void healthPotionBuy() {
        BattleItem boughtItem = world.buyItemByID(healthPotionID);
        if (boughtItem != null) {
            statusField.setText("Thank you for purchasing a Health Potion!");
            mainController.onLoad(boughtItem);
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