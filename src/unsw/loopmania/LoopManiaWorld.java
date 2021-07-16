package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.Building;
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
import unsw.loopmania.npcs.BasicEnemy;
import unsw.loopmania.npcs.Slug;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one entity
 * can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

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
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    // TODO = add more lists for other entities, for equipped inventory items,
    // etc...

    // TODO = expand the range of enemies
    private List<BasicEnemy> enemies;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    private List<Entity> unequippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingEntities;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse
     * them
     */
    private List<Pair<Integer, Integer>> orderedPath;

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
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
     */
    public boolean buyItemByID(int itemID) {
        List<BattleItem> battleItems = getBattleItems();
        BattleItem itemBought = new BattleItem(null, null, 0, 0);
        // TODO: what should the values of X and Y be?
        SimpleIntegerProperty newX = new SimpleIntegerProperty(0);
        SimpleIntegerProperty newY = new SimpleIntegerProperty(0);

        // get character's total gold and item cost
        int itemCost = battleItems.get(itemID).getItemCost();
        int characterGold = character.getGold();
        // add item to character's inventory and return true 
        if (characterGold >= itemCost) {
            character.setGold(characterGold - itemCost);

            if (itemID == 0) {
                itemBought = new Armor(newX, newY);
            } else if (itemID == 1) {
                itemBought = new Helmet(newX, newY);
            } else if (itemID == 2) {
                itemBought = new Shield(newX, newY);
            } else if (itemID == 3) {
                itemBought = new Staff(newX, newY);
            } else if (itemID == 4) {
                itemBought = new Stake(newX, newY);
            } else if (itemID == 5) {
                itemBought = new Sword(newX, newY);
            } else if (itemID == 6) {
                itemBought = new OneRing(newX, newY);
            } else if (itemID == 7) {
                itemBought = new HealthPotion(newX, newY);
            }

            unequippedInventoryItems.add(itemBought);
            return true;
        }

        // not enough gold to buy
        return false;
    }

    /**
     * Used for testing ShopItemTest.
     */
    public List<Entity> getCharacterInventory() {
        return unequippedInventoryItems;
    }

    public List<BattleItem> getBattleItems() {
        // organize items into their respective weapon styles
        List<BattleItem> shopItems = new ArrayList<>();

        // TODO: what should the values of x and y be? 
        // Should we initialize a new pair for each item?
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
        shopItems.add(oneRing);
        shopItems.add(healthPotion);

        return shopItems;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity
     * out of the file
     * 
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the
     * world)
     * 
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods
        // like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    /**
     * add a static entity
     * 
     * @param entity
     */
    public void addBuilding(Building building) {
        // Add a static entity
        buildingEntities.add(building);
    }

    /**
     * remove building from the grid that has expired
     * 
     * @param id the id of the building, from 0 to inf
     */
    private void removeBuilding(int id) {
        // Find the building corresponding to the id
        for (Building b : buildingEntities) {
            // When found, remove the building from the list
            if (b.getId() == id) {
                b.destroy();
                buildingEntities.remove(b);
                break;
            }
        }
    }

    /**
     * Get the list of buildings
     * @return 
     * 
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

    /**
     * spawns enemies if the conditions warrant it, adds to world
     * 
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies() {
        // TODO = expand this very basic version
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
        if (pos != null) {
            int indexInPath = orderedPath.indexOf(pos);
            Slug enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }
        return spawningEnemies;
    }

    /**
     * kill an enemy
     * 
     * @param enemy enemy to be killed
     */
    private void killEnemy(BasicEnemy enemy) {
        enemy.destroy();
        enemies.remove(enemy);
    }

    /**
     * run the expected battles in the world, based on current world state
     * 
     * @return list of enemies which have been killed
     */
    public List<BasicEnemy> runBattles() {
        List<BasicEnemy> defeatedEnemies = new ArrayList<BasicEnemy>();
        boolean conductFight = false;
        // Checking If there is an enemy inside battle radii
        for (BasicEnemy e : enemies) {
            // Checking if enemy is inside battle radii
            if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) <= e.getBattleRadius()) {
                conductFight = true;
                System.out.println("starting battle encounter");
                break;
            }
        }
        if (conductFight) {
            // Collecting all enemies inside support radii
            List<BasicEnemy> battleEnemies = new ArrayList<BasicEnemy>();
            for (BasicEnemy e : enemies) {
                // Checking if enemy is inside support radii
                if (Math.pow((character.getX() - e.getX()), 2) + Math.pow((character.getY() - e.getY()), 2) <= e.getSupportRadius()) {
                    battleEnemies.add(e);
                    System.out.println("adding enemy");
                }
            }
            int numberOfEnemies = battleEnemies.size();
            // Conduct Fights with Valid Enemies
            while (character.getHealth() > 0 && defeatedEnemies.size() < numberOfEnemies) {
                System.out.println("initiating battle phase");
                // Continuously fight until character loses or all enemies are defeated
                for (BasicEnemy e : battleEnemies) {
                    // Ignore Dead Enemies
                    if (e.getHealth() <= 0) {
                        continue;
                    }
                    // Calculate Character
                    int characterHealth = character.applyEnemyDamage(e);
                    if (characterHealth == 0) {
                        System.out.println("character killed");
                        break;
                    }
                    // Calculate Enemy
                    int enemyHealth = e.applyCharacterDamage(character);
                    if (enemyHealth == 0) {
                        defeatedEnemies.add(e);
                        System.out.println("enemy killed");
                    }
                }
            }
            System.out.println("battle encounter finished");
        }
        for (BasicEnemy e : defeatedEnemies) {
            System.out.println("killing enemy");
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from
            // the enemies list
            // if we killEnemy in prior loop, we get
            // java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
            character.setXP(character.getXP() + e.getExperience());
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

    /**
     * spawn a vampire castle card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public VampireCastleCard loadVampireCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
            // Assign an item reward
            Item loot = addUnequippedItem();
            unequippedInventoryItems.add(loot);
            removeCard(0);
        }
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
        if (cardEntities.size() >= getWidth()) {
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
            // Assign an item reward
            Item loot = addUnequippedItem();
            unequippedInventoryItems.add(loot);
            removeCard(0);
        }
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
        if (cardEntities.size() >= getWidth()) {
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
            // Assign an item reward
            Item loot = addUnequippedItem();
            unequippedInventoryItems.add(loot);
            removeCard(0);
        }
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
        if (cardEntities.size() >= getWidth()) {
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
            // Assign an item reward
            Item loot = addUnequippedItem();
            unequippedInventoryItems.add(loot);
            removeCard(0);
        }
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
        if (cardEntities.size() >= getWidth()) {
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
            // Assign an item reward
            Item loot = addUnequippedItem();
            unequippedInventoryItems.add(loot);
            removeCard(0);
        }
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
    public BarracksCard loadBarracksCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
            // Assign an item reward
            Item loot = addUnequippedItem();
            unequippedInventoryItems.add(loot);
            removeCard(0);
        }
        BarracksCard barracksCard = new BarracksCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(barracksCard);
        return barracksCard;
    }

    /**
     * spawn a card in the world and return the card entity
     * 
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public CampfireCard loadCampfireCard() {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()) {
            // Assign XP (amount in the assumptions)
            character.setXP(character.getXP() + 200);
            // Assign gold randomly (formula in assumptions)
            character.setGold(goldReward());
            // Assign an item reward
            Item loot = addUnequippedItem();
            unequippedInventoryItems.add(loot);
            removeCard(0);
        }
        CampfireCard campfireCard = new CampfireCard(new SimpleIntegerProperty(cardEntities.size()),
                new SimpleIntegerProperty(0));
        cardEntities.add(campfireCard);
        return campfireCard;
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
    public Item addUnequippedItem() {
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
        ArrayList<Item> itemRewards = getPosCardRewards();
        int upperbound = itemRewards.size();
        return itemRewards.get(rand.nextInt(upperbound));
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
        // TODO: Inventory Frontend Code
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
     * run moves which occur with every tick without needing to spawn anything
     * immediately
     */
    public void runTickMoves() {
        character.moveDownPath();
        moveBasicEnemies();
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
     * 
     * TODO: Use observer pattern here
     */
    private void moveBasicEnemies() {
        // TODO = expand to more types of enemy
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
        // TODO = modify this

        // has a chance spawning a basic enemy on a tile the character isn't on or
        // immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
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
        // TODO: Adding other Card Types
        if (card instanceof VampireCastleCard) {
            newBuilding = new VampireCastleBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 0);
        } else if (card instanceof TrapCard) {
            newBuilding = new TrapBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 0);
        } else if (card instanceof VillageCard) {
            newBuilding = new VillageBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 0);        
        } else if (card instanceof TowerCard) {
            newBuilding = new TowerBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 0);        
        } else if (card instanceof ZombieGraveyardCard) {
            newBuilding = new ZombieGraveyardBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 0);        
        } else if (card instanceof CampfireCard) {
            newBuilding = new CampfireBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), 0);        
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
    public boolean checkValidPlacement(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)){
                card = c;
                break;
            }
        }

        if (card instanceof VampireCastleCard || card instanceof TowerCard || card instanceof ZombieGraveyardCard) {
            if (!orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX,buildingNodeY))) {
                if (orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX - 1, buildingNodeY)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX, buildingNodeY - 1)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX - 1, buildingNodeY - 1)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX + 1, buildingNodeY)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX, buildingNodeY + 1)) ||
                    orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX + 1, buildingNodeY + 1))) {
                        return true;
                    }
            }
        } else if (card instanceof VillageCard || card instanceof TrapCard) {
            // can only be placed on path tiles
            if (orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX,buildingNodeY))) {
                return true;
            }
        } else if (card instanceof CampfireCard) {
            if (!orderedPath.contains(new Pair<Integer, Integer>(buildingNodeX,buildingNodeY))) {
                return true;
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
}
