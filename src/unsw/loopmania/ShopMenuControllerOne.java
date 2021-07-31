package unsw.loopmania;

import java.io.IOException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import unsw.loopmania.items.BattleItem;

public class ShopMenuControllerOne {
    /**
     * facilitates switching to main game
     */
    private int armorID = 0;
    private int helmetID = 1;
    private int shieldID = 2;
    private int staffID = 3;
    private int stakeID = 4;
    private int swordID = 5;
    private int healthPotionID = 7;
    private LoopManiaWorld world;
    private MenuSwitcher gameSwitcher;
    private MenuSwitcher shopScreenTwoSwitcher;
    private LoopManiaWorldController mainController;

    @FXML
    private Text playerGold;

    @FXML
    private Text statusField;

    public ShopMenuControllerOne(LoopManiaWorld world, LoopManiaWorldController mainController) {
        this.world = world;
        this.mainController = mainController;

        /**
         * since character is set to null initially, we add a listener to character's x()
         * when this value changes, we know that character is now initialized and so we can
         * bi-directionally connect character's gold stats to the Text: playerGold field 
         */
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
        BattleItem item = world.getHighestUsageItem(swordID);
        if (item != null) {
            statusField.setText("Thank you for selling a Sword!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have a Sword to sell");
        }
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
        BattleItem item = world.getHighestUsageItem(staffID);
        if (item != null) {
            statusField.setText("Thank you for selling a Staff!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have a Staff to sell");
        }
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
        BattleItem item = world.getHighestUsageItem(stakeID);
        if (item != null) {
            statusField.setText("Thank you for selling a Stake!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have a Stake to sell");
        }
    }

    /**
     * doggiecoin have a usage?
     */
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
        BattleItem item = world.getHighestUsageItem(shieldID);
        if (item != null) {
            statusField.setText("Thank you for selling a Shield!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have a Shield to sell");
        }
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
        BattleItem item = world.getHighestUsageItem(helmetID);
        if (item != null) {
            statusField.setText("Thank you for selling a Helmet!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have a Helmet to sell");
        }
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
        BattleItem item = world.getHighestUsageItem(armorID);
        if (item != null) {
            statusField.setText("Thank you for selling Armour!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have Armour to sell");
        }
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
        BattleItem item = world.getHighestUsageItem(healthPotionID);
        if (item != null) {
            statusField.setText("Thank you for selling a Health Potion!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have a Health Potion to sell");
        }
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

    /**
     * remembers the shop screen to return back to
     * @param shopSwitcher
     */
    public void setShopScreenTwo(MenuSwitcher shopSwitcher){
        this.shopScreenTwoSwitcher = shopSwitcher;
    }

    /**
     * facilitates switching to shop screen two upon button click
     * @throws IOException
     */
    @FXML
    private void switchToShopScreenTwo() throws IOException {
        shopScreenTwoSwitcher.switchMenu();
    }
}