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
    private int protectiveGearCount = 0;
    private int healthPotionBuyCount = 0;
    private LoopManiaWorld world;
    private MenuSwitcher gameSwitcher;
    private MenuSwitcher shopScreenTwoSwitcher;
    private LoopManiaWorldController mainController;

    @FXML
    private Text playerGold;

    @FXML
    private Text doggieCoinValue;

    @FXML
    private Text statusField;

    @FXML
    private Text swordBuyPrice;

    @FXML
    private Text swordSellPrice;
    
    @FXML
    private Text staffBuyPrice;

    @FXML
    private Text staffSellPrice;

    @FXML
    private Text stakeBuyPrice;

    @FXML
    private Text stakeSellPrice;
    
    @FXML
    private Text shieldBuyPrice;

    @FXML
    private Text shieldSellPrice;

    @FXML
    private Text helmetBuyPrice;

    @FXML
    private Text helmetSellPrice;
    
    @FXML
    private Text armorBuyPrice;

    @FXML
    private Text armorSellPrice;

    @FXML
    private Text hpBuyPrice;

    @FXML
    private Text hpSellPrice;

    public int getItemPrice(int itemID) {
        return world.getItemPrice(itemID);
    }

    public long getItemSellValue(int itemID) {
        return Math.round(0.7 * world.getItemPrice(itemID));
    }

    public ShopMenuControllerOne(LoopManiaWorld world, LoopManiaWorldController mainController) {
        this.world = world;
        this.mainController = mainController;

        /**
         * since character is set to null initially, we add a listener to character's x()
         * when this value changes, we know that character is now initialized and so we can
         * bi-directionally connect character's gold stats to the Text: playerGold field 
         */
        swordBuyPrice.setText(String.valueOf(getItemPrice(swordID)));
        swordSellPrice.setText(String.valueOf(getItemSellValue(swordID)));

        staffBuyPrice.setText(String.valueOf(getItemPrice(staffID)));
        staffSellPrice.setText(String.valueOf(getItemSellValue(staffID)));

        stakeBuyPrice.setText(String.valueOf(getItemPrice(stakeID)));
        stakeSellPrice.setText(String.valueOf(getItemSellValue(stakeID)));

        shieldBuyPrice.setText(String.valueOf(getItemPrice(shieldID)));
        shieldSellPrice.setText(String.valueOf(getItemSellValue(shieldID)));

        helmetBuyPrice.setText(String.valueOf(getItemPrice(helmetID)));
        helmetSellPrice.setText(String.valueOf(getItemPrice(helmetID)));

        armorBuyPrice.setText(String.valueOf(getItemPrice(armorID)));
        armorSellPrice.setText(String.valueOf(getItemPrice(armorID)));

        hpBuyPrice.setText(String.valueOf(getItemPrice(healthPotionID)));
        hpSellPrice.setText(String.valueOf(getItemPrice(healthPotionID)));

        world.getCharacter().x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                IntegerProperty characterGold = world.getCharacter().getSimpleIntegerGold();
                IntegerProperty doggieCoin = world.getCharacter().getSimpleIntegerDoggieCoin();
                playerGold.textProperty().bind(characterGold.asString());
                doggieCoinValue.textProperty().bind(doggieCoin.asString());
            }
        });
    }

    @FXML
    private void swordBuy() {
        if (!world.checkInventoryFull()) {
            buyItem(swordID);
        } else {
            statusField.setText("You can't buy a Sword, inventory is full! Try selling some items");
        }
    }

    @FXML
    private void swordSell() {
        sellItem(swordID);
    }

    @FXML
    private void staffBuy() {
        if (!world.checkInventoryFull()) {
            buyItem(staffID);
        } else {
            statusField.setText("You can't buy a Staff, inventory is full! Try selling some items");
        }
    }

    @FXML
    private void staffSell() {
        sellItem(staffID);
    }

    @FXML
    private void stakeBuy() {
        if (!world.checkInventoryFull()) {
            buyItem(stakeID);
        } else {
            statusField.setText("You can't buy a Stake, inventory is full! Try selling some items");
        }
    }

    @FXML
    private void stakeSell() {
        sellItem(stakeID);
    }

    @FXML
    private void doggieCoinSell() {
        if (world.sellDoggieCoin()) {
            statusField.setText("Congratulations, you have sold one Doggie Coin!");
        } else {
            statusField.setText("You don't have a Doggie Coin to sell!");
        }     
    }

    private void addGearBerserkerMode(int itemID) {
        if (protectiveGearCount == 0) {
            buyItem(itemID);
            protectiveGearCount++;
        } else {
            statusField.setText("Cannot buy more than one piece of protective gear in Berserker Mode!");
        }
    }

    @FXML
    private void shieldBuy() {
        if (!world.checkInventoryFull()) {
            if (world.getBerserker()) {
                addGearBerserkerMode(shieldID);
            } else {
                buyItem(shieldID);
            }
        } else {
            statusField.setText("You can't buy a Shield, inventory is full! Try selling some items");
        }
    }

    @FXML
    private void shieldSell() {
        sellItem(shieldID);
    }

    @FXML
    private void helmetBuy() {
        if (!world.checkInventoryFull()) {
            if (world.getBerserker()) {
                addGearBerserkerMode(helmetID);
            } else {
                buyItem(helmetID);
            }
        } else {
            statusField.setText("You can't buy a Helmet, inventory is full! Try selling some items");
        }
    }

    @FXML
    private void helmetSell() {
        sellItem(helmetID);
    }

    @FXML
    private void armourBuy() {
        if (!world.checkInventoryFull()) {
            if (world.getBerserker()) {
                addGearBerserkerMode(armorID);
            } else {
                buyItem(armorID);
            } 
        } else {
            statusField.setText("You can't buy Armour, inventory is full! Try selling some items");
        }
    }

    @FXML
    private void armourSell() {
        sellItem(armorID);
    }

    @FXML
    private void healthPotionBuy() {
        if (!world.checkInventoryFull()) {
            // if survival mode is on, restrict buying Health Potion to 1
            if (world.getSurvival()) {
                if (healthPotionBuyCount == 0) {
                    buyItem(healthPotionID);
                    healthPotionBuyCount++;
                } else  {
                    statusField.setText("Cannot buy more than one Health Potion in Survival Mode!");
                } 
            } else {
                buyItem(healthPotionID);
            }
        } else {
            statusField.setText("You can't buy a Health Potion, inventory is full! Try selling some items");
        }
    }

    @FXML
    private void healthPotionSell() {
        sellItem(healthPotionID);
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
        protectiveGearCount = 0;
        healthPotionBuyCount = 0;
        statusField.setText("Welcome!");
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

    private void buyItem(int itemID) {
        BattleItem boughtItem = world.buyItemByID(itemID);
        if (boughtItem != null) {
            if (itemID == swordID) {
                statusField.setText("Thank you for purchasing a Sword!");
            } else if (itemID == staffID) {
                statusField.setText("Thank you for purchasing a Staff!");
            } else if (itemID == stakeID) {
                statusField.setText("Thank you for purchasing a Stake!");
            } else if (itemID == shieldID) {
                statusField.setText("Thank you for purchasing a Shield!");
            } else if (itemID == armorID) {
                statusField.setText("Thank you for purchasing Armour!");
            } else if (itemID == helmetID) {
                statusField.setText("Thank you for purchasing a Helmet!");
            } else if (itemID == healthPotionID) {
                statusField.setText("Thank you for purchasing a Health Potion!");
            }
            mainController.onLoad(boughtItem);
        } else {
            if (itemID == swordID) {
                statusField.setText("Insufficient funds to buy a Sword!");
            } else if (itemID == staffID) {
                statusField.setText("Insufficient funds to buy a Staff!");
            } else if (itemID == stakeID) {
                statusField.setText("Insufficient funds to buy a Stake!");
            } else if (itemID == shieldID) {
                statusField.setText("Insufficient funds to buy a Shield!");
            } else if (itemID == armorID) {
                statusField.setText("Insufficient funds to buy Armour!");
            } else if (itemID == helmetID) {
                statusField.setText("Insufficient funds to buy a Helmet!");
            } else if (itemID == healthPotionID) {
                statusField.setText("Insufficient funds to buy a Health Potion!");
            }
        }
    }

    private void sellItem(int itemID) {
        BattleItem itemToSell = world.getHighestUsageItem(itemID);
        if (itemToSell != null) {
            if (itemID == swordID) {
                statusField.setText("Thank you for selling a Sword!");
            } else if (itemID == staffID) {
                statusField.setText("Thank you for selling a Staff!");
            } else if (itemID == stakeID) {
                statusField.setText("Thank you for selling a Stake!");
            } else if (itemID == shieldID) {
                statusField.setText("Thank you for selling a Shield!");
            } else if (itemID == helmetID) {
                statusField.setText("Thank you for selling a Helmet!");
            } else if (itemID == armorID) {
                statusField.setText("Thank you for selling Armour!");
            } else if (itemID == healthPotionID) {
                statusField.setText("Thank you for selling a Health Potion!");
            }
            world.sellItem(itemToSell);
        } else {
            if (itemID == swordID) {
                statusField.setText("You don't have a Sword to sell!");
            } else if (itemID == staffID) {
                statusField.setText("You don't have a Staff to sell!");
            } else if (itemID == stakeID) {
                statusField.setText("You don't have a Stake to sell!");
            } else if (itemID == shieldID) {
                statusField.setText("You don't have a Shield to sell!");
            } else if (itemID == helmetID) {
                statusField.setText("You don't have a Helmet to sell!");
            } else if (itemID == armorID) {
                statusField.setText("You don't have Armour to sell!");
            } else if (itemID == healthPotionID) {
                statusField.setText("You don't have a Health Potion to sell!");
            }
        }
    }
}