package unsw.loopmania;

import java.io.IOException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import unsw.loopmania.items.BattleItem;

public class ShopMenuControllerTwo {
    private int oneRingID = 6;
    private LoopManiaWorld world;
    private MenuSwitcher shopScreenOneSwitcher;
    private LoopManiaWorldController mainController;

    @FXML
    private Text playerGold;

    @FXML
    private Text statusField;

    public ShopMenuControllerTwo(LoopManiaWorld world, LoopManiaWorldController mainController) {
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

    private void buyItem(int itemID) {
        BattleItem boughtItem = world.buyItemByID(itemID);
        if (boughtItem != null) {
            if (itemID == oneRingID) {
                statusField.setText("Congratulations, you have bought The One Ring!");
            } 
            // else if (itemID == staffID) {
            //     statusField.setText("Thank you for purchasing a Staff!");
            // } else if (itemID == stakeID) {
            //     statusField.setText("Thank you for purchasing a Stake!");
            // } 
            mainController.onLoad(boughtItem);
        } else {
            if (itemID == oneRingID) {
                statusField.setText("Insufficient funds to buy The One Ring!");
            } 
            // else if (itemID == staffID) {
            //     statusField.setText("Insufficient funds to buy a Staff!");
            // } else if (itemID == stakeID) {
            //     statusField.setText("Insufficient funds to buy a Stake!");
            // } 
        }
    }

    private void sellItem(int itemID) {
        BattleItem itemToSell = world.getHighestUsageItem(itemID);
        if (itemToSell != null) {
            if (itemID == oneRingID) {
                statusField.setText("Thank you for selling The One Ring!");
            } 
            // else if (itemID == staffID) {
            //     statusField.setText("Thank you for selling a Staff!");
            // } else if (itemID == stakeID) {
            //     statusField.setText("Thank you for selling a Stake!");
            // } 
            world.sellItem(itemToSell);
        } else {
            if (itemID == oneRingID) {
                statusField.setText("You don't have The One Ring to sell!");
            } 
            // else if (itemID == staffID) {
            //     statusField.setText("You don't have a Staff to sell!");
            // } else if (itemID == stakeID) {
            //     statusField.setText("You don't have a Stake to sell!");
            // } 
        }
    }

    @FXML
    public void buyOneRing() {
        if (!world.checkInventoryFull()) {
            buyItem(oneRingID);
        } else {
            statusField.setText("You can't buy The One Ring, inventory is full! Try selling some items");
        }
    }

    @FXML
    public void sellOneRing() {
        sellItem(oneRingID);
    }

    @FXML
    public void buyAnduril() {

    }

    @FXML
    public void sellAnduril() {

    }

    @FXML
    public void buyTreeStump() {

    }

    @FXML
    public void sellTreeStump() {

    }

    /**
     * remembers the shop screen to return back to
     * @param shopSwitcher
     */
    public void setShopScreenOne(MenuSwitcher shopSwitcher){
        this.shopScreenOneSwitcher = shopSwitcher;
    }

    /**
     * facilitates switching to shop screen two upon button click
     * @throws IOException
     */
    @FXML
    private void switchToShopScreenOne() throws IOException {
        shopScreenOneSwitcher.switchMenu();
    }
}
