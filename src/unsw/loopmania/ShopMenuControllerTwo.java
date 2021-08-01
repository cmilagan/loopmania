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

    @FXML
    public void buyOneRing() {
        if (!world.checkInventoryFull()) {
            BattleItem boughtItem = world.buyItemByID(oneRingID);
            if (boughtItem != null) {
                statusField.setText("Congratulations, you have bought The One Ring!");
                mainController.onLoad(boughtItem);
            } else {
                statusField.setText("Insufficient funds to buy The One Ring!");
            }
        } else {
            statusField.setText("You can't buy The One Ring, inventory is full! Try selling some items");
        }
    }

    @FXML
    public void sellOneRing() {
        BattleItem item = world.getHighestUsageItem(oneRingID);
        if (item != null) {
            statusField.setText("Thank you for selling The One Ring!");
            world.sellItem(item);
        } else {
            statusField.setText("You don't have The One Ring to sell!");
        }
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
