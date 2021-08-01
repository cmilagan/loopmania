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
    private int andurilID = 8;
    private int treeStumpID = 9;
    private LoopManiaWorld world;
    private MenuSwitcher shopScreenOneSwitcher;
    private LoopManiaWorldController mainController;

    @FXML
    private Text playerGold;

    @FXML
    private Text statusField;

    @FXML
    private Text oneRingBuyPrice;

    @FXML
    private Text oneRingSellPrice;

    @FXML
    private Text andurilBuyPrice;

    @FXML
    private Text andurilSellPrice;

    @FXML
    private Text treeStumpBuyPrice;

    @FXML
    private Text treeStumpSellPrice;

    public int getItemPrice(int itemID) {
        return world.getItemPrice(itemID);
    }

    public long getItemSellValue(int itemID) {
        return Math.round(0.7 * world.getItemPrice(itemID));
    }

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
                
                /**
                 * also update the cost/sell value of the weapons in the front-end 
                 */
                oneRingBuyPrice.setText(String.valueOf(getItemPrice(oneRingID)));
                oneRingSellPrice.setText(String.valueOf(getItemSellValue(oneRingID)));

                andurilBuyPrice.setText(String.valueOf(getItemPrice(andurilID)));
                andurilSellPrice.setText(String.valueOf(getItemSellValue(andurilID)));

                treeStumpBuyPrice.setText(String.valueOf(getItemPrice(treeStumpID)));
                treeStumpSellPrice.setText(String.valueOf(getItemSellValue(treeStumpID)));
            }
        });
    }

    private void buyItem(int itemID) {
        BattleItem boughtItem = world.buyItemByID(itemID);
        if (boughtItem != null) {
            if (itemID == oneRingID) {
                statusField.setText("Congratulations, you have bought The One Ring!");
            } else if (itemID == andurilID) {
                statusField.setText("Congratulations, you have bought The Anduril!");
            } else if (itemID == treeStumpID) {
                statusField.setText("Congratulations, you have bought a Tree Stump!");
            } 
            mainController.onLoad(boughtItem);
        } else {
            if (itemID == oneRingID) {
                statusField.setText("Insufficient funds to buy The One Ring!");
            } else if (itemID == andurilID) {
                statusField.setText("Insufficient funds to buy The Anduril!");
            } else if (itemID == treeStumpID) {
                statusField.setText("Insufficient funds to buy a Tree Stump!");
            } 
        }
    }

    private void sellItem(int itemID) {
        BattleItem itemToSell = world.getHighestUsageItem(itemID);
        if (itemToSell != null) {
            if (itemID == oneRingID) {
                statusField.setText("Thank you for selling The One Ring!");
            } else if (itemID == andurilID) {
                statusField.setText("Thank you for selling The Anduril!");
            } else if (itemID == treeStumpID) {
                statusField.setText("Thank you for selling a Tree Stump!");
            } 
            world.sellItem(itemToSell);
        } else {
            if (itemID == oneRingID) {
                statusField.setText("You don't have The One Ring to sell!");
            } else if (itemID == andurilID) {
                statusField.setText("You don't have The Anduril to sell!");
            } else if (itemID == treeStumpID) {
                statusField.setText("You don't have a Tree Stump to sell!");
            } 
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
        if (!world.checkInventoryFull()) {
            buyItem(andurilID);
        } else {
            statusField.setText("You can't buy The Anduril, inventory is full! Try selling some items");
        }
    }

    @FXML
    public void sellAnduril() {
        sellItem(andurilID);
    }

    @FXML
    public void buyTreeStump() {
        if (!world.checkInventoryFull()) {
            buyItem(treeStumpID);
        } else {
            statusField.setText("You can't buy a Tree Stump, inventory is full! Try selling some items");
        }
    }

    @FXML
    public void sellTreeStump() {
        sellItem(treeStumpID);
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
