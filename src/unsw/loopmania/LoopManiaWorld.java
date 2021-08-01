package unsw.loopmania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.buildings.TowerBuilding;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.buildings.VampireCastleBuilding;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.buildings.ZombieGraveyardBuilding;
import unsw.loopmania.cards.BarracksCard;
import unsw.loopmania.cards.CampfireCard;
import unsw.loopmania.cards.TowerCard;
import unsw.loopmania.cards.TrapCard;
import unsw.loopmania.cards.VampireCastleCard;
import unsw.loopmania.cards.VillageCard;
import unsw.loopmania.cards.ZombieGraveyardCard;
import unsw.loopmania.items.Anduril;
import unsw.loopmania.items.Armor;
import unsw.loopmania.items.AttackItem;
import unsw.loopmania.items.BattleItem;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Helmet;
import unsw.loopmania.items.Item;
import unsw.loopmania.items.OneRing;
import unsw.loopmania.items.Shield;
import unsw.loopmania.items.Staff;
import unsw.loopmania.items.Stake;
import unsw.loopmania.items.Sword;
import unsw.loopmania.items.TreeStump;
import unsw.loopmania.modes.BerserkerMode;
import unsw.loopmania.modes.ConfusingMode;
import unsw.loopmania.modes.StandardMode;
import unsw.loopmania.modes.SurvivalMode;
import unsw.loopmania.npcs.AlliedSoldier;
import unsw.loopmania.npcs.BasicEnemy;
import unsw.loopmania.npcs.Doggie;
import unsw.loopmania.npcs.ElanMuske;
import unsw.loopmania.npcs.Slug;
import unsw.loopmania.npcs.Vampire;
import unsw.loopmania.npcs.Zombie;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one entity
 * can occupy the same square.
 */
public class LoopManiaWorld {
    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * 
     * Current number of loops completed;
     */
    private int loopCounter;

    /**
     * Previous loop count
     */
    private int prevLoop;

    /**
     * Current number of ticks;
     */
    private int tickCounter;

    /**
     * Boolean to determine what mode the game is playing at
     */
    private boolean standardMode;
    private boolean survivalMode;
    private boolean berserkerMode;
    private boolean confusingMode;
    /** 
     * Keeps track of the previous time Shop was opened
     */
    private int shopCounter;
    private int previousShopRound;

    private int doggieCoinPrice = 100;

    /**
     * Keeps track of Elan to vary DoggieCoin prices.
     * 0  =>  Elan hasn't spawned yet or it's been more than 5 rounds since his defeat
     *        DoggieCoin varies from 100 - 500 (normal)
     * 
     * 1  =>  Elan is currently alive
     *        DoggieCoin varies from 3,000 - 10,000
     * 
     * < 0 => Elan has been defeated in the last 5 rounds
     *        DoggieCoin varies from 0 - 10
     */
    private int elanTimer = 0;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    private List<BasicEnemy> enemies;

    private List<Card> cardEntities;

    private List<Entity> unequippedInventoryItems;

    private List<Item> equippedInventoryItems;

    private List<Building> buildingEntities;

    // a list of allied soldiers
    private List<AlliedSoldier> alliedSoldiers;

    // a list of enemies to battle
    private List<BasicEnemy> battleEnemies;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse
     * them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    /**
     * Determines the winning conditions and the restrictions or effect on the game
     */

    /**
     * create the world (constructor)
     * 
     * @param width       width of world in number of cells
     * @param height      height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing
     *                    position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        equippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        this.loopCounter = 0;
        alliedSoldiers = new ArrayList<>();
        standardMode = false;
        survivalMode = false;
        berserkerMode = false;
        confusingMode = false;
        previousShopRound = 1;
        shopCounter = 1;
        battleEnemies = new ArrayList<BasicEnemy>();
    }

    public List<BasicEnemy> getEnemies() {
        return enemies;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLoopCount() {
        return loopCounter;
    }

    public int getShopRoundCounter() {
        return shopCounter;
    }

    public int getPreviousShopRound() {
        return previousShopRound;
    }

    public void setShopRoundCounter(int count) {
        shopCounter = count;
    }

    public void setPreviousShopRound(int round) {
        previousShopRound = round;
    }

    public void setLoopCount(int num) {
        loopCounter = num;
    }
    
    public int getAlliedSoldiersNumber() {
        return this.alliedSoldiers.size();
    }

    public List<AlliedSoldier> getAlliedSoldiers() {
        return this.alliedSoldiers;
    }


    public void addAlliedSoldier(AlliedSoldier s) {
        if (alliedSoldiers.size() < 5) alliedSoldiers.add(s);
    }

    public int getDoggieCoinPrice() {
        return doggieCoinPrice;
    }

    /**
     * Setters for the different modes
     */
    public boolean getStandard() {
        return standardMode;
    }

    public boolean getSurvival() {
        return survivalMode;
    }

    public boolean getBerserker() {
        return berserkerMode;
    }

    public boolean getConfusing() {
        return confusingMode;
    }

    /**
     * Setters for the modes
     */
    public void playStandard() {
        standardMode = true;
        survivalMode = false;
        berserkerMode = false;
        confusingMode = false;
    }

    public void playSurvival() {
        standardMode = false;
        survivalMode = true;
        berserkerMode = false;
        confusingMode = false;
    }

    public void playBerserker() {
        standardMode = false;
        survivalMode = false;
        berserkerMode = true;
        confusingMode = false;
    }
    
    public void playConfusing() {
        standardMode = false;
        survivalMode = false;
        berserkerMode = false;
        confusingMode = true;
    }

    // If a mode isn't selected from the new game screen, it will automatically have standard
    // mode conditions
    /**
     * Gives the corresponding amount of xp needed to win
     * @return the amount of xp needed to win
     */
    public int getWinXp() {
        if (standardMode) {
            StandardMode mode = new StandardMode();
            return mode.getWinXP();
        } else if (survivalMode) {
            SurvivalMode mode = new SurvivalMode();
            return mode.getWinXP();
        } else if (berserkerMode) {
            BerserkerMode mode = new BerserkerMode();
            return mode.getWinXP();
        } else if (confusingMode) {
            ConfusingMode mode = new ConfusingMode();
            return mode.getWinXP();
        } else {
            StandardMode mode = new StandardMode();
            return mode.getWinXP();
        }
    }

    /**
     * Gives the corresponding amount of gold needed to win
     * @return the amount of gold needed to win
     */
    public int getWinGold() {
        if (standardMode) {
            StandardMode mode = new StandardMode();
            return mode.getWinGold();
        } else if (survivalMode) {
            SurvivalMode mode = new SurvivalMode();
            return mode.getWinGold();
        } else if (berserkerMode) {
            BerserkerMode mode = new BerserkerMode();
            return mode.getWinGold();
        } else if (confusingMode) {
            ConfusingMode mode = new ConfusingMode();
            return mode.getWinGold();
        } else {
            StandardMode mode = new StandardMode();
            return mode.getWinGold();
        }
    }

    /**
     * Gives the corresponding amount of loops needed to win
     * @return the amount of loops needed to win
     */
    public int getWinLoops() {
        if (standardMode) {
            StandardMode m = new StandardMode();
            return m.getWinLoop();
        } else if (survivalMode) {
            SurvivalMode m = new SurvivalMode();
            return m.getWinLoop();
        } else if (berserkerMode) {
            BerserkerMode m = new BerserkerMode();
            return m.getWinLoop();
        } else if (confusingMode) {
            ConfusingMode m = new ConfusingMode();
            return m.getWinLoop();
        } else {
            StandardMode m = new StandardMode();
            return m.getWinLoop();
        }
    }

    /**
     * checks if inventory is full. I.e., there are 
     * inventory width times height many items
     * @return
     */
    public boolean checkInventoryFull() {
        return unequippedInventoryItems.size() == 
        unequippedInventoryHeight * unequippedInventoryHeight;
    }

    /**
     * Given an ID that maps to an item in the shop, add the 
     * respective item to the MC's unquipped inventory given 
     * that the MC has sufficient gold to buy the item.
     * 
     * If MC does not have sufficient gold to buy item, return false.
     * 
     * Item Mapping:
     * 0 - Armor
     * 1 - Helmet
     * 2 - Shield
     * 3 - Staff
     * 4 - Stake
     * 5 - Sword
     * 6 - One Ring
     * 7 - Health Potion
     * 8 - Anduril, Flame of the West
     */
    public BattleItem buyItemByID(int itemID) {
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        BattleItem itemBought = null;
        List<BattleItem> battleItems = getBattleItems();
        
        // get character's total gold and item cost
        int itemCost = battleItems.get(itemID).getItemCost();
        int characterGold = character.getGold();
        // add item to character's inventory and return the item 
        if (characterGold >= itemCost) {
            character.setGold(characterGold - itemCost);

            if (itemID == 0) {
                itemBought = new Armor(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 1) {
                itemBought = new Helmet(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 2) {
                itemBought = new Shield(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 3) {
                itemBought = new Staff(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 4) {
                itemBought = new Stake(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 5) {
                itemBought = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 6) {
                itemBought = new OneRing(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 7) {
                itemBought = new HealthPotion(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), 
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 8) {
                itemBought = new Anduril(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (itemID == 9) {
                itemBought = new TreeStump(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            }

            unequippedInventoryItems.add(itemBought);
            return itemBought;
        }

        // not enough gold to buy
        return null;
    }

    /**
     * Sell Doggie Coin.
     * 
     * Note: This will only sell one Doggie Coin per activation. 
     * 
     * If transaction is successful, return true, else false.
     */
    public boolean sellDoggieCoin() {
        if (character.getDoggieCoin() > 0) {
            character.decrementDoggieCoin();
            int characterGold = character.getGold();
            character.setGold(characterGold + getDoggieCoinPrice());   
            return true;
        }
        return false;
    }

    /**
     * Given an item, remove the item from the character's 
     * inventory and recompensate with gold (selling item at shop). 
     * 
     * According to assumption, the item will be resold at 70% of its 
     * original value. 
     * 
     * Note that all values will be returned as an integer.
     */
    public void sellItem(BattleItem item) {
        double discount = 0.7;
        int itemCost = item.getItemCost();
        int characterGold = character.getGold();

        // add sold item amount to character's total gold
        character.setGold(characterGold + (int) Math.round(discount * itemCost));
        
        // remove item from unequipped inventory
        unequippedInventoryItems.remove(item);
        item.destroy();
    }

    /**
     * Given an itemID, refer to the corresponding BattleItem
     * and return the item of that class which has the highest uses
     */
    public BattleItem getHighestUsageItem(int itemID) {
        List<BattleItem> items = new ArrayList<>();

        // add all items of the desired type in an array
        for (Entity entity : unequippedInventoryItems) {
            if (itemID == 0 && entity instanceof Armor) {
                items.add((Armor) entity);
            } else if (itemID == 1 && entity instanceof Helmet) {
                items.add((Helmet) entity);
            } else if (itemID == 2 && entity instanceof Shield) {
                items.add((Shield) entity);
            } else if (itemID == 3 && entity instanceof Staff) {
                items.add((Staff) entity);
            } else if (itemID == 4 && entity instanceof Stake) {
                items.add((Stake) entity);
            } else if (itemID == 5 && entity instanceof Sword) {
                items.add((Sword) entity);
            } else if (itemID == 6 && entity instanceof OneRing) {
                items.add((OneRing) entity);
            } else if (itemID == 7 && entity instanceof HealthPotion) {
                items.add((HealthPotion) entity);
            } else if (itemID == 8 && entity instanceof Anduril) {
                items.add((Anduril) entity);
            } else if (itemID == 9 && entity instanceof TreeStump) {
                items.add((TreeStump) entity);
            }
        }

        // there is no such item type present in array
        if (items.isEmpty()) return null;

        // only one item of desired type
        if (items.size() == 1) return items.get(0);

        // return the item that has the highest usage
        items.sort(Comparator.comparing(BattleItem::getUsage));
        Collections.reverse(items);
        return items.get(0);
    }

    /**
     * Used for testing ShopItemTest.
     */
    public List<Entity> getCharacterInventory() {
        return unequippedInventoryItems;
    }

    /**
     * returns a list of all BattleItems used for shop
     */
    public List<BattleItem> getBattleItems() {
        // organize items into their respective weapon styles
        List<BattleItem> shopItems = new ArrayList<>();

        SimpleIntegerProperty newX = new SimpleIntegerProperty(0);
        SimpleIntegerProperty newY = new SimpleIntegerProperty(0);
        
        // initializing defence items
        Armor armor = new Armor(newX, newY);
        Helmet helmet = new Helmet(newX, newY);
        Shield shield = new Shield(newX, newY);
        shopItems.add(armor);
        shopItems.add(helmet);
        shopItems.add(shield);

        // initializing attack items
        Staff staff = new Staff(newX, newY);
        Stake stake = new Stake(newX, newY);
        Sword sword = new Sword(newX, newY);
        shopItems.add(staff);
        shopItems.add(stake);
        shopItems.add(sword);
        
        // adding other items
        OneRing oneRing = new OneRing(newX, newY);
        HealthPotion healthPotion = new HealthPotion(newX, newY);
        Anduril anduril = new Anduril(newX, newY);
        TreeStump treeStump = new TreeStump(newX, newY);
        shopItems.add(oneRing);
        shopItems.add(healthPotion);
        shopItems.add(anduril);
        shopItems.add(treeStump);

        return shopItems;
    }

    /**
     * returns the price of an item given the itemID
     */
    public int getItemPrice(int itemID) {
        List<BattleItem> shopItems = getBattleItems();
        int itemCost = shopItems.get(itemID).getItemCost();
        return itemCost;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity
     * out of the file
     * 
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
        character.setInventory(unequippedInventoryItems);
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the
     * world)
     * 
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        nonSpecifiedEntities.add(entity);
    }

    /**
     * add a building entity
     * 
     * @param entity
     */
    public void addBuilding(Building building) {
        // Add a building entity
        buildingEntities.add(building);
    }

    /**
     * Get the list of buildings
     * 
     * @return a list of buildings
     */
    public List<Building> getBuildings() {
        // Return the buildings
        return buildingEntities;
    }

    /**
     * add a specified enemy in the enemies array
     * @param enemy
     */
    public void addEnemy(BasicEnemy enemy) {
        enemies.add(enemy);
    }

    public List<BasicEnemy> getBattleEnemies() {
        return battleEnemies;
    }

    /**
     * 
     * @param x
     * @param y
     * @return the closest path tile
     */
    public Pair<Integer, Integer> closestPathTile(int x, int y) {
        // finding the closest path
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Pair<Integer, Integer> tile = new Pair<Integer, Integer>((x - i), (y - j));
                if (orderedPath.contains(tile)) {
                    return tile;
                }
            }
        }   
        return null;
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     * 
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies() {
        // a list of enemies to spawn
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();

        if (pos != null) {
            int indexInPath = orderedPath.indexOf(pos);
            Slug enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);

            // Spawn Doggie every 20 rounds
            if (loopCounter > 0 && loopCounter % 20 == 0) {
                Doggie doggie = new Doggie(new PathPosition(indexInPath, orderedPath));
                enemies.add(doggie);
                spawningEnemies.add(doggie);
            }

            // Spawn Elan every 40 rounds
            if (loopCounter > 0 && loopCounter % 40 == 0 && character.getXP() >= 10000) {
                ElanMuske elan = new ElanMuske(new PathPosition(indexInPath, orderedPath));
                enemies.add(elan);
                spawningEnemies.add(elan);
                elanTimer = 1;                    // elan timer = 1 => Elan is alive and Doggie coin price should go up.
            }
        }

        // spawning zombies and vampires
        for (Building b: buildingEntities) {
            Pair<Integer, Integer> buildSpawnPos = closestPathTile(b.getX(), b.getY());
            int indexInPath = orderedPath.indexOf(buildSpawnPos);
            if (b instanceof ZombieGraveyardBuilding) {
                if (prevLoop != loopCounter) {
                    Zombie newZombie = new Zombie(new PathPosition(indexInPath, orderedPath));
                    enemies.add(newZombie);
                    spawningEnemies.add(newZombie);
                    if (b.getExpiry() == 0) {
                        removeBuilding(b);
                        break;
                    }
                }
            } else if (b instanceof VampireCastleBuilding) {
                if (b.getExpiry() == 0) {
                    Vampire newVampire = new Vampire(new PathPosition(indexInPath, orderedPath));
                    enemies.add(newVampire);
                    spawningEnemies.add(newVampire);
                    removeBuilding(b);
                    break;
                }
            } 
        }

        return spawningEnemies;
    }

    /**
     * this function spawns enemies that were turned into allies,
     * we do this because the period of trance has now ended
     */
    public List<BasicEnemy> spawnOriginalEnemies() {
        List<BasicEnemy> originalEnemies = new ArrayList<BasicEnemy>();
        List<AlliedSoldier> soldiersToRemove = new ArrayList<AlliedSoldier>();
        /**
         * after one round of battle, turn the trance allied soldiers 
         * (enemies which turn into allied soldiers) back into their original
         * enemy form
         */
        for (AlliedSoldier soldier : alliedSoldiers) {
            BasicEnemy originalEnemy = soldier.getOriginalEnemy();
            
            /**
             * if originalEnemy is not null, then we know that the allied soldier
             * was actually a transformed enemy before, our goal is to bring back this
             * enemy into the world (because the trance has ended)
             */
            if (originalEnemy != null) {
                BasicEnemy enemy = null;

                if (originalEnemy instanceof Slug) {
                    enemy = new Slug(new PathPosition(soldier.getCurrentPositionIndex(), orderedPath));
                } else if (originalEnemy instanceof Zombie) {
                    enemy = new Zombie(new PathPosition(soldier.getCurrentPositionIndex(), orderedPath));
                } else if (originalEnemy instanceof Vampire) {
                    enemy = new Vampire(new PathPosition(soldier.getCurrentPositionIndex(), orderedPath));
                }

                enemies.add(enemy);
                originalEnemies.add(enemy);
                
                // allied soldier is now back into the original enemy it was
                soldier.destroy();
                soldiersToRemove.add(soldier);
            }
        }

        alliedSoldiers.removeAll(soldiersToRemove);

        return originalEnemies;
    }


    /**
     * kill an enemy
     * 
     * @param enemy enemy to be killed
     */
    public void killEnemy(BasicEnemy enemy) {
        enemy.destroy();
        enemies.remove(enemy);
    }

    /**
     * remove a building
     * 
     * @param building building to be removed
     */
    public void removeBuilding(Building building) {
        building.destroy();
        buildingEntities.remove(building);
    }

    /**
     * run the expected battles in the world, based on current world state
     * 
     * @return list of enemies which have been killed
     */
    public List<BasicEnemy> runBattles() {
        List<BasicEnemy> defeatedEnemies = new ArrayList<BasicEnemy>();

        // Collecting all enemies which the character must fight (character within battle radius of an enemy)
        List<BasicEnemy> supportEnemies = new ArrayList<BasicEnemy>();
        for (BasicEnemy e : enemies) {
            // Checking if enemy is inside battle radii
            if (Math.sqrt(Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2)) <= e.getBattleRadius()) {
                battleEnemies.add(e);
                System.out.println("adding enemy");
            }
        }

        // Check if there is a tower nearby to the battle (where the character is)
        Boolean towerSupport = false;
        // Loop through building and find a tower
        for (Building b : buildingEntities) {
            // Check if tower in radius
            if (b instanceof TowerBuilding) {
                TowerBuilding tower = (TowerBuilding) b;
                if (Math.sqrt(Math.pow((character.getX() - tower.getX()), 2) + Math.pow((character.getY() - tower.getY()), 2)) <= tower.getRange()) {
                    towerSupport = true;
                    System.out.println("Tower is nearby");
                }
            }
        }

        /**
         * Given that we have found some enemies X to fight, get enemies Y such that enemy X
         * is within the support radius of enemies Y (according to spec they should come join battle)
        */
        for (BasicEnemy supportingEnemy : enemies) {
            // checking if enemy X is within the support radius of enemy Y
            for (BasicEnemy attackingEnemy : battleEnemies) {
                // ensure that we are not fighting the same enemy twice (i.e., supportingEnemy != attackingEnemy)
                if (!supportingEnemy.equals(attackingEnemy) && !battleEnemies.contains(supportingEnemy)) {
                    if (Math.sqrt(Math.pow((attackingEnemy.getX() - supportingEnemy.getX()), 2) + Math.pow((attackingEnemy.getY() - supportingEnemy.getY()), 2)) <= supportingEnemy.getSupportRadius()) {
                        supportEnemies.add(supportingEnemy);
                        System.out.println("adding support enemy");
                        /**
                         * According to our assumption, we must move supporting enemies next to (position + 1) the 
                         * battle enemies to show that the supporting enemies have joined the battle too
                         */
                        int moveToIndex = attackingEnemy.getCurrentPositionIndex() + 1;
                        supportingEnemy.moveTo(moveToIndex);
                        System.out.println("moving support enemy next to battle enemy");
                    }
                }
            }
        }

        // Adding supportEnemies to battleEnemies as we have to fight them too
        battleEnemies.addAll(supportEnemies);


        System.out.println("initiating battle phase");

        // If tower is present, tower will deal damage to all enemies in the battle
        List<BasicEnemy> killedEnemies = new ArrayList<BasicEnemy>();
        if (towerSupport) {
            TowerBuilding t = new TowerBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
            for (BasicEnemy e : battleEnemies) {
                e.applyBuildingDamage(t.getDamage());
                // Check if enemy is killed
                if (e.getHealth() <= 0) {
                    killEnemy(e);
                    killedEnemies.add(e);
                    character.setGold(goldReward());
                }
            }
            battleEnemies.removeAll(killedEnemies);
        }

        // Conduct Fights with Valid Enemies
        while (character.getHealth() > 0 && battleEnemies.size() > 0) {
            // Continuously fight until character loses or all enemies are defeated
            List<BasicEnemy> currentBattleEnemies = new ArrayList<BasicEnemy>(battleEnemies);
            // Newly added zombies can't attack until next phase
            for (BasicEnemy enemy : currentBattleEnemies) {
                if (alliedSoldiers.size() == 0) {
                    // Calculate Character
                    int characterHealth = character.applyEnemyDamage(enemy);
                    if (characterHealth == 0) {
                        character.destroy();
                        break;
                    }
                } else {
                    ArrayList<AlliedSoldier> toRemove = new ArrayList<AlliedSoldier>();
                    AlliedSoldier firstSoldier = alliedSoldiers.get(0);
                    
                    /**
                     * If statement for testing purposes only. The health of Allied Soldier should
                     * never initially be -1 unless specifically set to be.
                     */
                    if(firstSoldier.getHealth() != -1) firstSoldier.applyEnemyDamage(enemy);

                    for (AlliedSoldier alliedSoldier : alliedSoldiers) {                    
                        int alliedSoldierHealth = alliedSoldier.getHealth();
                        
                        if (alliedSoldierHealth == 0) {
                            // Remove Allied Soldier
                            toRemove.add(alliedSoldier);
                            alliedSoldier.destroy();
                        } else if (alliedSoldierHealth == -1) {             // Only happens on critical hit from Zombie
                            // Remove Soldier and spawn Zombie
                            toRemove.add(alliedSoldier);
                            alliedSoldier.destroy();
                            int indexInPath = orderedPath.indexOf(character.getCoordinatePair());
                            battleEnemies.add(new Zombie(new PathPosition(indexInPath, orderedPath)));
                        }
                    }

                    alliedSoldiers.removeAll(toRemove);

                    // remaining allied soldiers should attack the enemy
                    for (AlliedSoldier a : alliedSoldiers) {
                        enemy.applyBuildingDamage(a.getDamage());
                    }
                }
                // Calculate enemy health
                int enemyHealth = enemy.applyCharacterDamage(character, alliedSoldiers);
                
                /**
                 * if character has a special weapon on hand, calculate the effect
                 */
                if (enemyHealth != 0) {
                    enemy.applyWeaponEffects(character, this, orderedPath);
                    enemy.applyEnemyEffects(character, true, enemies);
                }

                if (enemyHealth == 0) {
                    defeatedEnemies.add(enemy);
                    battleEnemies.remove(enemy);
                    
                    if (enemy instanceof ElanMuske) {
                        elanTimer = -5;                        // DoggieCoin price should go down for the next 5 rounds.
                    }
                    System.out.println("enemy killed");
                }
            }
        }
        System.out.println("battle encounter finished");
        
        for (BasicEnemy e : defeatedEnemies) {
            System.out.println("killing enemy");
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from
            // the enemies list
            // if we killEnemy in prior loop, we get
            // java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
            if (e instanceof Doggie) { character.incrementDoggieCoin(); }
            character.setXP(character.getXP() + e.getExperience());
            character.setGold(goldReward());
        }

        if (character.isStunned()) {
            character.toggleStun();
        }
        return defeatedEnemies;
    }

    /**
     * Calculates the gold reward for having too many cards or items or defeating an
     * enemy
     * 
     * @return the amount of gold rewarded
     */
    public int goldReward() {
        Random rand = new Random(); 
        int upperbound = 10;
        int goldGained = rand.nextInt(upperbound) * ((100 + character.getXP()) / 1000);
        int giveGold = character.getGold() + goldGained;
        System.out.println(giveGold);
        return giveGold;
    }

    /**
     * Collects all the possible item drops from a card that is destroyed
     * (If new items are added, place them here)
     * 
     * @return a list of items
     */
    public ArrayList<Item> getPosCardRewards() {
        // Organise the possible items that drop from a card being destroyed
        ArrayList<Item> itemRewards = new ArrayList<>();

        SimpleIntegerProperty newX = new SimpleIntegerProperty(0);
        SimpleIntegerProperty newY = new SimpleIntegerProperty(0);
        
        // Initialising all possible items (add more if we add more items)
        Armor armor = new Armor(newX, newY);
        Helmet helmet = new Helmet(newX, newY);
        Shield shield = new Shield(newX, newY);
        HealthPotion healthPotion = new HealthPotion(newX, newY);
        Staff staff = new Staff(newX, newY);
        Stake stake = new Stake(newX, newY);
        Sword sword = new Sword(newX, newY);
        // Append the items to the list
        itemRewards.add(armor);
        itemRewards.add(helmet);
        itemRewards.add(shield);
        itemRewards.add(staff);
        itemRewards.add(stake);
        itemRewards.add(sword);
        itemRewards.add(healthPotion);

        return itemRewards;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //                                   CARDS                                           //
    ///////////////////////////////////////////////////////////////////////////////////////

    public Item rewardDiscard() {
        // Assign XP (amount in the assumptions)
        character.setXP(character.getXP() + 200);
        // Assign gold randomly (formula in assumptions)
        character.setGold(goldReward() + character.getGold());
        // Assign an item reward
        Item loot = addUnequippedItem(1);
        unequippedInventoryItems.add(loot);
        removeCard(0);
        return loot;
    }

    /**
     * spawn a vampire castle card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard() {
        // if adding more cards than have, remove the first card...
        VampireCastleCard vampireCastleCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(vampireCastleCard);
        return vampireCastleCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VillageCard loadVillageCard() {
        VillageCard villageCard = new VillageCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(villageCard);
        return villageCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public TrapCard loadTrapCard() {
        TrapCard trapCard = new TrapCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(trapCard);
        return trapCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public ZombieGraveyardCard loadZombieGraveyardCard() {
        ZombieGraveyardCard zombieGraveCard = new ZombieGraveyardCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        cardEntities.add(zombieGraveCard);
        return zombieGraveCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public TowerCard loadTowerCard() {
        // if adding more cards than have, remove the first card...
        TowerCard towerCard = new TowerCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(towerCard);
        return towerCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public CampfireCard loadCampfireCard() {
        // if adding more cards than have, remove the first card...
        CampfireCard campfireCard = new CampfireCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(campfireCard);
        return campfireCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public BarracksCard loadBarracksCard() {
        // if adding more cards than have, remove the first card...
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(barracksCard);
        return barracksCard;
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed
     * cards)
     * 
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index) {
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * spawn an item in the world and returns the item entity
     * 
     * @return an item to be spawned in the controller as a JavaFX node
     */
    public Item addUnequippedItem(double rareBound) {
        // apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null) {
            // eject the oldest unequipped item and replace it... oldest item is that at
            // beginning of items
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
        }

        // now we insert an item, as we know we have at least made a slot
        // available...
        Random rand = new Random();
        double choice = rand.nextDouble();
        System.out.println(choice);
        Item addedItem = null;
        Random nrand2 = new Random();
        if (choice < rareBound) {
            System.out.println("basic item");
            Random nrand1 = new Random();
            double commonUncommon = nrand1.nextDouble();
            if (commonUncommon < 0.6) {
                // common item drops
                int nextChoice = nrand2.nextInt(2);
                System.out.println(nextChoice);

                if (nextChoice == 0) {
                    System.out.println("sword dropped");
                    addedItem = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                    new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } else if (nextChoice == 1) {
                    addedItem = new Stake(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                    new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } else if (nextChoice == 2) {
                    addedItem = new Helmet(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                    new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } 
            } else {
                // uncommon item drops
                int nextChoice = nrand2.nextInt(3);

                if (nextChoice == 0) {
                    addedItem = new HealthPotion(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                    new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } else if (nextChoice == 1) {
                    addedItem = new Staff(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                    new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } else if (nextChoice == 2) {
                    addedItem = new Shield(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                    new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } else if (nextChoice == 3) {
                    addedItem = new Armor(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                    new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
                } 
            }
        } else {
            // rare item drops
            int nextChoice = nrand2.nextInt(1);

            if (nextChoice == 0) {
                addedItem = new OneRing(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
            } else if (nextChoice == 1) {
                addedItem = new Anduril(new SimpleIntegerProperty(firstAvailableSlot.getValue0()),
                new SimpleIntegerProperty(firstAvailableSlot.getValue1())); 
            }
        }
        unequippedInventoryItems.add(addedItem);
        return addedItem;
    }

    /**
     * remove an item by x,y coordinates
     * 
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y) {
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * remove an equipped item by x,y coordinates
     * 
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeEquippedInventoryItemByCoordinates(int x, int y) {
        Entity item = getEquippedInventoryItembyCoordinates(x, y);
        // Setting Character Equipment Slot
        if (item instanceof AttackItem) character.setWeapon(null);
        else if (item instanceof Helmet) character.setHelmet(null);
        else if (item instanceof Armor) character.setArmor(null);
        else if (item instanceof Shield) character.setShield(null);
        // Destroying Entity
        item.destroy();
    }


    /**
     * Update building expiries when a loop is completed
     */

    public void buildingUpdateExpiry() {
        for (Building b: buildingEntities) {
            b.setExpiry(b.getExpiry() - 1);
        }
    }
    /**
     * Remove all buildings which have expired
     */
    public void removeExpiredBuildings() {
        List<Building> expired = new ArrayList<Building>();
        for (Building b: buildingEntities) {
            if (b.getExpiry() == 0 && ! (b instanceof VampireCastleBuilding) && ! (b instanceof ZombieGraveyardBuilding)) {
                b.destroy();
                expired.add(b);
            }
        }
        buildingEntities.removeAll(expired);
    }

    /**
     * Remove all expired items from character's equipped and unequipped inventory
     */
    public void removeExpiredItems() {
        List<BattleItem> expirableItems = new ArrayList<BattleItem>();
        for (Entity entities : unequippedInventoryItems) {
            if (entities instanceof BattleItem) {
                expirableItems.add((BattleItem) entities);
            }
        }

        /**
         * Check if item is expired, if it is remove from unequipped inventory and
         * remove from character's equipped inventory
         */
        for (BattleItem item : expirableItems) {
            if (item.getUsage() == item.getItemDurability()) {
                unequippedInventoryItems.remove(item);
                item.destroy();
                if (item instanceof AttackItem) {
                    character.setWeapon(null);
                } else if (item instanceof Helmet) {
                    character.setHelmet(null);
                } else if (item instanceof Shield) {
                    character.setShield(null);
                } else if (item instanceof Armor) {
                    character.setArmor(null);
                }
            }
        }
    }

    public int getTickCounter() {
        return tickCounter;
    }
    
    /**
     * Update the value of Elan timer
     */
    public void updateElanTimer() {
        if (elanTimer < 0) {
            elanTimer++;
        }
    }

    /**
     * Update the value doggie coin based on the state of Elan
     */
    public void varyDoggieCoinPrice() {
        Random random = new Random();
        int maximum = 500;
        int minimum = 10;

        // if Elan hasn't spawned yet
        if (elanTimer == 0) {
            maximum = 500;
            minimum = 10;
        }
        // if Elan is in the game
        else if (elanTimer == 1) {
            maximum = 10000;
            minimum = 3000;
        }
        // if Elan has been defeated
        else if (elanTimer < 0) {
            maximum = 10;
            minimum = 0;
        }

        int range = maximum - minimum + 1;
        doggieCoinPrice = random.nextInt(range) + minimum;
    }

    /**
     * run moves which occur with every tick without needing to spawn anything
     * immediately
     */
    public void runTickMoves() {
        
        // add to tick counter
        this.tickCounter += 1;
        // if loop completed increment
        this.prevLoop = this.loopCounter;
        if (this.tickCounter % orderedPath.size() == 0) {
            this.loopCounter += 1;
            // update building expiries
            buildingUpdateExpiry();
        }

        character.moveDownPath();
        for (BasicEnemy e : enemies) {
            e.applyEnemyEffects(character, false, enemies);
        }
        applyBuildingEffects();
        moveBasicEnemies();
        
        removeExpiredBuildings();
        removeExpiredItems();

        varyDoggieCoinPrice();

    }

    /**
     * remove an item from the unequipped inventory
     * 
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item) {
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates assumes that no 2
     * unequipped inventory items share x and y coordinates
     * 
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y) {
        for (Entity e : unequippedInventoryItems) {
            if ((e.getX() == x) && (e.getY() == y)) {
                return e;
            }
        }
        return null;
    }

    /**
     * return an equipped inventory item by x and y coordinates assumes that no 2
     * equipped inventory items share x and y coordinates
     * 
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getEquippedInventoryItembyCoordinates(int x, int y) {
        if (x == 0) return character.getWeapon();
        if (x == 1) return character.getHelmet();
        if (x == 2) return character.getArmor();
        if (x == 3) return character.getShield();
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list
     * (this is ordered based on age in the starter code)
     * 
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index) {
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the
     * unequipped inventory
     * 
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem() {
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available
        // slot defined by looking row by row
        for (int y = 0; y < unequippedInventoryHeight; y++) {
            for (int x = 0; x < unequippedInventoryWidth; x++) {
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null) {
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * 
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x) {
        for (Card c : cardEntities) {
            if (c.getX() >= x) {
                c.x().set(c.getX() - 1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveBasicEnemies() {
        for (BasicEnemy e : enemies) {
            e.move();
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * 
     * @return null if random choice is that wont be spawning an enemy or it isn't
     *         possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition() {
        // has a chance spawning a basic enemy on a tile the character isn't on or
        // immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2);
        if ((choice == 0) && (enemies.size() < 2)) {
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size()) % orderedPath.size();
            int endNotAllowed = (indexPosition + 3) % orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i = endNotAllowed; i != startNotAllowed; i = (i + 1) % orderedPath.size()) {
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates
                    .get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    //////////////////////////////////////////////////////////////////////
    //                         Buildings                                //
    //////////////////////////////////////////////////////////////////////

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }
        

        // now spawn building
        Building newBuilding = null;
        if (card instanceof VampireCastleCard) {
            newBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        } else if (card instanceof TrapCard) {
            newBuilding = new TrapBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        } else if (card instanceof VillageCard) {
            newBuilding = new VillageBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));        
        } else if (card instanceof TowerCard) {
            newBuilding = new TowerBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));        
        } else if (card instanceof ZombieGraveyardCard) {
            newBuilding = new ZombieGraveyardBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));        
        } else if (card instanceof CampfireCard) {
            newBuilding = new CampfireBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));        
        } else if (card instanceof BarracksCard) {
            newBuilding = new BarracksBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));                    
        } else {
            try {
                throw new Exception("Invalid Building Card Selected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        buildingEntities.add(newBuilding);
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);
    
        return newBuilding;
    }

    // 
    public void applyBuildingEffects() {
        int cX = character.getX();
        int cY = character.getY();
        Pair<Integer, Integer> characterPos = new Pair<Integer, Integer>(cX, cY);
        for (Building b: buildingEntities) {
            int bX = b.getX();
            int bY = b.getY();
            Pair<Integer, Integer> buildingPos = new Pair<Integer, Integer>(bX, bY);
            if (buildingPos.equals(characterPos)) {
                // if character is on a village building, heal the character for 10
                if (b instanceof VillageBuilding) {
                    System.out.println("building found\n");
                    VillageBuilding village = (VillageBuilding) b;
                    if (character.getHealth() < (character.getMaxHealth() - village.getHeal())) {
                        System.out.println("heal\n");
                        character.setHealth(character.getHealth() + village.getHeal());
                    } else {
                        // if the healing that can be done is < village.getHeal
                        character.setHealth(character.getHealth() + (character.getMaxHealth() - character.getHealth()));
                    }
                } else if (b instanceof BarracksBuilding) {
                    // spawn allied soldiers
                    System.out.print(alliedSoldiers.size());
                    // heal all allied soldiers
                    for (AlliedSoldier a : alliedSoldiers) {
                        a.setHealth(3);
                    }

                    // spawn new allied soldiers
                    if (alliedSoldiers.size() < 5) {
                        System.out.print("adding allied soldier");
                        int index = orderedPath.indexOf(buildingPos);
                        PathPosition pos = new PathPosition(index, orderedPath);
                        AlliedSoldier a = new AlliedSoldier(pos);
    
                        alliedSoldiers.add(a);
                    }
                }
            } 
            if (b instanceof TrapBuilding) {
                TrapBuilding trap = (TrapBuilding) b;
                boolean triggered = false;
                for (BasicEnemy e: enemies) {
                    Pair<Integer, Integer> enemyPos = new Pair<Integer, Integer>(e.getX(), e.getY());
                    if (enemyPos.equals(buildingPos)) {
                        // enemy steps on trap
                        triggered = true;
                        e.applyBuildingDamage(trap.getDamage());
                        if (e.getHealth() <= 0) {
                            // enemy killed
                            killEnemy(e);
                            break;
                        }
                    }
                }
                if (triggered) {
                    removeBuilding(trap);
                    break;
                }
            }
            // Check if there is a campfire
            if (b instanceof CampfireBuilding) {
                // Check if the character is in range of the campfire
                CampfireBuilding campfire = (CampfireBuilding) b;
                if (Math.pow((character.getX() - campfire.getX()), 2) + Math.pow((character.getY() - campfire.getY()), 2) <= campfire.getRange()) {
                    // Buff the damage of the character
                    character.setDamage(character.getDamage() * 2);
                } else {
                    // If not in the radius, make sure the damage goes back to normal
                    character.setDamage(character.getDamage());
                }
            }
        }
    }

    //////////////////////////////////////////////////////////////////////
    //                     Additional Methods                           //
    //////////////////////////////////////////////////////////////////////


    /**
     * 
     * @return Return the list of ordered path tiles
     */

    public List<Pair<Integer, Integer>> getOrderedPath() {
        return this.orderedPath;
    }

    /***
     * checks the conversion of card to building is valid.
     * @param cardNodeX
     * @param cardNodeY
     * @param buildingNodeX
     * @param buildingNodeY
     * @return boolean
     */
    public boolean checkValidCardPlacement(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }

        // checking that tile is not occupied
        boolean occupied = false;
        for(Building b : buildingEntities) {
            Pair<Integer, Integer> target = new Pair<Integer, Integer>(buildingNodeX, buildingNodeY);
            Pair<Integer, Integer> buildLoc = new Pair<Integer, Integer>(b.getX(),b.getY());
            if (target.equals(buildLoc)) {
                occupied = true;
            }
        }

        // checking conditions
        if (card instanceof VampireCastleCard || card instanceof TowerCard || card instanceof ZombieGraveyardCard) {
            if (!orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX,buildingNodeY))) {
                if (orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX - 1, buildingNodeY)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX, buildingNodeY - 1)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX - 1, buildingNodeY - 1)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX + 1, buildingNodeY)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX, buildingNodeY + 1)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX + 1, buildingNodeY + 1))) {
                        if (!occupied) return true;
                    }
            }
        } else if (card instanceof VillageCard || card instanceof TrapCard || card instanceof BarracksCard) {
            // can only be placed on path tiles
            if (orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX,buildingNodeY))) {
                if (!occupied) return true;
            }
        } else if (card instanceof CampfireCard) {
            if (!orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX,buildingNodeY))) {
                if (!occupied) return true;
            }
        } else {
            try {
                throw new Exception("Invalid Building Card Selected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
        
    }


    /**
     * 
     * @param itemNodeX
     * @param itemNodeY
     * @return Item
     */
    public Item getItem(int itemNodeX, int itemNodeY) {
        // get the item
        Item item = (Item) getUnequippedInventoryItemEntityByCoordinates(itemNodeX, itemNodeY);
        return item;
    }


    public Item checkValidItemSlot(int itemNodeX, int itemNodeY, int slotX, int slotY) {
        Item item = null;
        // getting the appropiate item
        for (Entity e: getCharacterInventory()) {
            if (e instanceof Item) {
                if (e.getX() == itemNodeX && e.getY() == itemNodeY) {
                    item = (Item) e;
                }
            }
        }

        Pair<Integer, Integer>target = new Pair<Integer, Integer>(slotX, slotY);
        if (item instanceof AttackItem) {
            AttackItem attackItem = (AttackItem) item;
            if (!attackItem.getAppropiateSlot().equals(target)) return null;
        } else if (item instanceof Shield) {
            Shield shieldItem = (Shield) item;
            if (!shieldItem.getAppropiateSlot().equals(target)) return null;
        } else if (item instanceof Armor) {
            Armor armourItem = (Armor) item;
            if (!armourItem.getAppropiateSlot().equals(target)) return null;
        } else if (item instanceof Helmet) {
            Helmet helmetItem = (Helmet) item;
            if (!helmetItem.getAppropiateSlot().equals(target)) return null;
        } else {
            return null;
        }
        return item;
    }
    /**
     * Equips the item
     * @param item
     */
    public Pair<Item, Item> equipItemByCoordinates(int itemNodeX, int itemNodeY, int slotX, int slotY) {
        
        Item equip = checkValidItemSlot(itemNodeX, itemNodeY, slotX, slotY);
        if (equip == null) return null;
        equip.setX(slotX);
        equip.setY(slotY);

        Item unequipped = null;
        if (equip instanceof AttackItem) {
            System.out.println("start");
            for (Item i : equippedInventoryItems) {
                if (i instanceof AttackItem) {
                    unequipped = i;
                    equippedInventoryItems.remove(i);
                    System.out.println("unequipped sword");
                    break;
                }
            }
            System.out.println("equipped sword");
            character.setWeapon((AttackItem) equip);
            equippedInventoryItems.add(equip);
        } else if (equip instanceof Shield) {
            for (Item item : equippedInventoryItems) {
                if (item instanceof Shield) {
                    unequipped = item;
                    equippedInventoryItems.remove(item);
                }
            }
            character.setShield((Shield) equip);
            equippedInventoryItems.add(equip);
        } else if (equip instanceof Armor) {
            for (Item item : equippedInventoryItems) {
                if (item instanceof Armor) {
                    unequipped = item;
                    equippedInventoryItems.remove(item);
                }
            }
            character.setArmor((Armor) equip);
            equippedInventoryItems.add(equip);
        } else if (equip instanceof Helmet) {
            for (Item item : equippedInventoryItems) {
                if (item instanceof Helmet) {
                    unequipped = item;
                    equippedInventoryItems.remove(item);
                }
            }
            character.setHelmet((Helmet) equip);
            equippedInventoryItems.add(equip);
        }
        if (unequipped == null) {
            System.out.println("no item");
        }

        return new Pair<Item,Item>(equip, unequipped);

    }

    /**
     * Gets this worlds character
     * @return Character
     */
    public Character getCharacter() {
        return this.character;
    }

    /**
     * Consumes a potion in the character inventory
     */
    public void consumePotion() {
        character.useHealthPotion();
    }

    /**
     * the amount of cards the user currently has
     * @return int
     */
    public int getNumCards() {
        return this.cardEntities.size();
    }
}
