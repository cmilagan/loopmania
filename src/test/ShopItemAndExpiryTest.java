package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.Entity;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.items.Anduril;
import unsw.loopmania.items.Armor;
import unsw.loopmania.items.BattleItem;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Helmet;
import unsw.loopmania.items.OneRing;
import unsw.loopmania.items.Shield;
import unsw.loopmania.items.Staff;
import unsw.loopmania.items.Stake;
import unsw.loopmania.items.Sword;
import unsw.loopmania.npcs.Vampire;

/**
 * test the battleItems array located in LoopManiaWorld 
 * check if all items are included with correct item stats
 * 
 * Note: this array is used to display the items in the Shop menu
 */
public class ShopItemAndExpiryTest {
    private int armorID = 0;
    private int helmetID = 1;
    private int shieldID = 2;
    private int staffID = 3;
    private int stakeID = 4;
    private int swordID = 5;
    private int oneRingID = 6;
    private int healthPotionID = 7;
    private int andurilID = 8;
    private int treeStumpID = 9;
    private int characterPosition = 0;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<BattleItem> battleItems;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    public ShopItemAndExpiryTest() {
        initializeWorld();
    }

    /**
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
    @Test
    public void testBuyArmor() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Armor) {
                Armor armor = (Armor) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(armor.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(armorID) != null);

                // item should appear in character's inventory
                Armor addedArmor = (Armor) item;
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Armor) {
                        addedArmor = (Armor) entities;
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(armorID) != null);

                // use for usage times
                for (int i = 0; i < armor.getItemDurability(); i++) {
                    addedArmor.useDefence();
                    addedArmor.useCritDefence();
                    testWorld.runTickMoves();
                }

                // item should dissapear in character's inventory
                equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Armor) {
                        equipmentContains = true;
                    }
                }   
                assertFalse(equipmentContains);
                
                // initially no armor item exists in character inventory
                assertNull(testWorld.getHighestUsageItem(armorID));

                // character has enough gold to buy two items
                newCharacter.setGold(armor.getItemCost() * 2);
                Armor armor1 = (Armor) testWorld.buyItemByID(armorID);

                // highest usage armor is armor bought above
                assertTrue(testWorld.getHighestUsageItem(armorID) == armor1);

                // testing highest usage armor is returned for two pieces
                Armor armor2 = (Armor) testWorld.buyItemByID(armorID);
                armor1.useDefence();
                armor1.useDefence();
                armor2.useDefence();
                assertTrue(testWorld.getHighestUsageItem(armorID) == armor1);

                int orgCharGold = testWorld.getCharacter().getGold();
                testWorld.sellItem(armor1);
                assertFalse(testWorld.getCharacterInventory().contains(armor1));
                assertEquals(orgCharGold + (int) Math.round(0.7 * armor2.getItemCost()), 
                testWorld.getCharacter().getGold());
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyHealthPotion() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof HealthPotion) {
                HealthPotion healthPotion = (HealthPotion) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(healthPotion.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(healthPotionID) != null);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                HealthPotion potion = (HealthPotion) item;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof HealthPotion) {
                        equipmentContains = true;
                        potion = (HealthPotion) entities;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(healthPotionID) != null);

                // testing if Health Potion is working

                // get initial character health
                int mainCharacterHealth = newCharacter.getHealth();

                // initializing Vampire
                PathPosition vampirePathPosition = new PathPosition(characterPosition + 1, orderedPath);
                Vampire newVampire = new Vampire(vampirePathPosition);
                testWorld.addEnemy(newVampire);

                /**
                 * need to equip character with Stake otherwise
                 * Vampire will kill character
                 */
                Stake stake = new Stake(new SimpleIntegerProperty(), new SimpleIntegerProperty());
                newCharacter.setWeapon(stake);

                // run battle
                testWorld.runBattles();

                // check if health is less by 40
                /**
                 * Explanation:
                 * 
                 * Vampire attacks Character -> Character health: 80
                 * Character attacks Vampire with Stake -> Vampire health: 8
                 * Vampire attacks Character -> Character health: 60
                 * Character attacks Vampire with Stake -> Vampire health: 0
                 */
                assertEquals(mainCharacterHealth - newVampire.getDamage() * 2, newCharacter.getHealth());

                // consume Health Potion
                potion.use(newCharacter);

                // check if health is back to max
                assertEquals(mainCharacterHealth, newCharacter.getHealth());

                testWorld.runTickMoves();

                // initially no health potion exists in character inventory
                assertNull(testWorld.getHighestUsageItem(healthPotionID));

                // character has enough gold to buy two items
                newCharacter.setGold(healthPotion.getItemCost() * 2);
                HealthPotion hp1 = (HealthPotion) testWorld.buyItemByID(healthPotionID);

                // highest usage hp is hp bought above
                assertTrue(testWorld.getHighestUsageItem(healthPotionID) == hp1);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyHelmet() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Helmet) {
                Helmet helmet = (Helmet) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(helmet.getItemCost());

                // character should be able to buy item
                Helmet orgHelmet = (Helmet) testWorld.buyItemByID(helmetID);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Helmet) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(helmetID) != null);

                // character has enough gold to buy two items
                newCharacter.setGold(helmet.getItemCost() * 2);
                Helmet helmet1 = (Helmet) testWorld.buyItemByID(helmetID);

                // testing highest usage armor is returned for two pieces
                orgHelmet.useDefence();
                orgHelmet.useDefence();
                helmet1.useDefence();
                assertTrue(testWorld.getHighestUsageItem(helmetID) == orgHelmet);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyOneRing() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof OneRing) {
                OneRing ring = (OneRing) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(ring.getItemCost());

                // character should be able to buy item
                OneRing orgOneRing = (OneRing) testWorld.buyItemByID(oneRingID);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof OneRing) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(oneRingID) != null);

                // checking that oneRing highest usage is still 1
                assertTrue(testWorld.getHighestUsageItem(oneRingID) == orgOneRing);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyAnduril() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Anduril) {
                Anduril anduril = (Anduril) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(anduril.getItemCost());

                // character should be able to buy item
                Anduril orgAnduril = (Anduril) testWorld.buyItemByID(andurilID);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Anduril) {
                        equipmentContains = true;
                    }
                }
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(andurilID) != null);

                // checking that oneRing highest usage is still 1
                assertTrue(testWorld.getHighestUsageItem(andurilID) == orgAnduril);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyShield() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Shield) {
                Shield shield = (Shield) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(shield.getItemCost());

                // character should be able to buy item
                Shield shield1 = (Shield) testWorld.buyItemByID(shieldID);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Shield) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(shieldID) != null);

                // character has enough gold to buy two items
                newCharacter.setGold(shield1.getItemCost() * 2);
                Shield shield2 = (Shield) testWorld.buyItemByID(shieldID);

                // testing highest usage armor is returned for two pieces
                shield1.useDefence();
                shield1.useDefence();
                shield2.useDefence();
                assertTrue(testWorld.getHighestUsageItem(shieldID) == shield1);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyTreeStump() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof TreeStump) {
                TreeStump treeStump = (TreeStump) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(treeStump.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(treeStumpID) != null);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof TreeStump) {
                        equipmentContains = true;
                    }
                }
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(treeStumpID) != null);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyStaff() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Staff) {
                Staff staff = (Staff) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(staff.getItemCost());

                // character should be able to buy item
                Staff staff1 = (Staff) testWorld.buyItemByID(staffID);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Staff) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(staffID) != null);

                // character has enough gold to buy two items
                newCharacter.setGold(staff1.getItemCost() * 2);
                Staff staff2 = (Staff) testWorld.buyItemByID(staffID);

                // testing highest usage armor is returned for two pieces
                staff1.inflictDamage();
                staff1.inflictDamage();
                staff2.inflictDamage();
                assertTrue(testWorld.getHighestUsageItem(staffID) == staff1);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuyStake() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Stake) {
                Stake stake = (Stake) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(stake.getItemCost());

                // character should be able to buy item
                Stake stake1 = (Stake) testWorld.buyItemByID(stakeID);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Stake) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(stakeID) != null);

                // character has enough gold to buy two items
                newCharacter.setGold(stake1.getItemCost() * 2);
                Stake stake2 = (Stake) testWorld.buyItemByID(stakeID);

                // testing highest usage armor is returned for two pieces
                stake1.inflictDamage();
                stake1.inflictDamage();
                stake2.inflictDamage();
                assertTrue(testWorld.getHighestUsageItem(stakeID) == stake1);
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testBuySword() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Sword) {
                Sword sword = (Sword) item;
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(sword.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(swordID) != null);

                // item should appear in character's inventory
                Sword addedSword = (Sword) item;
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Sword) {
                        addedSword = (Sword) entities;
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item, insufficient gold
                assertFalse(testWorld.buyItemByID(swordID) != null);

                // use for usage times
                for (int i = 0; i < sword.getItemDurability(); i++) {
                    addedSword.inflictDamage();
                    testWorld.runTickMoves();
                }

                // item should dissapear in character's inventory
                equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Sword) {
                        equipmentContains = true;
                    }
                }   
                assertFalse(equipmentContains);

                // character has enough gold to buy two items
                newCharacter.setGold(sword.getItemCost() * 2);
                Sword sword1 = (Sword) testWorld.buyItemByID(swordID);

                // highest usage armor is armor bought above
                assertTrue(testWorld.getHighestUsageItem(swordID) == sword1);

                // testing highest usage armor is returned for two pieces
                Sword sword2 = (Sword) testWorld.buyItemByID(swordID);
                sword1.inflictDamage();
                sword1.inflictDamage();
                sword2.inflictDamage();
                assertTrue(testWorld.getHighestUsageItem(swordID) == sword1);
            }
        }
        assertTrue(itemPresent);
    }

    // setup template world
    public void initializeWorld() {
        int LOOP_SIZE = 3;

        // setting world path
        orderedPath.add(Pair.with(0, 0));
        orderedPath.add(Pair.with(1, 0));
        orderedPath.add(Pair.with(2, 0));
        orderedPath.add(Pair.with(2, 1));
        orderedPath.add(Pair.with(2, 2));
        orderedPath.add(Pair.with(1, 2));
        orderedPath.add(Pair.with(0, 2));
        orderedPath.add(Pair.with(0, 1));
        testWorld = new LoopManiaWorld(LOOP_SIZE, LOOP_SIZE, orderedPath);
        
        // initializing and adding the character
        PathPosition characterPathPosition = new PathPosition(characterPosition, orderedPath);
        newCharacter = new Character(characterPathPosition);
        testWorld.setCharacter(newCharacter);

        battleItems = testWorld.getBattleItems();
    }
}
