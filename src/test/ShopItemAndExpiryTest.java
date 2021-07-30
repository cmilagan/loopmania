package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

                // cant buy another item
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

                // cant buy another item
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
                assertTrue(testWorld.buyItemByID(helmetID) != null);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Helmet) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(helmetID) != null);
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
                assertTrue(testWorld.buyItemByID(oneRingID) != null);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof OneRing) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(oneRingID) != null);
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
                assertTrue(testWorld.buyItemByID(shieldID) != null);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Shield) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(shieldID) != null);
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
                assertTrue(testWorld.buyItemByID(staffID) != null);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Staff) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(staffID) != null);
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
                assertTrue(testWorld.buyItemByID(stakeID) != null);

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Stake) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(stakeID) != null);
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

                // cant buy another item
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
