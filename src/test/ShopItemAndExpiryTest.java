package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

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
    public void testArmorStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Armor) {
                Armor armor = (Armor) item;
                assertEquals(20, armor.getItemCost());
                assertEquals(10, armor.getItemDurability());
                assertEquals(40, armor.getDefence());
                assertEquals(0, armor.getCritDefence());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(armor.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(armorID));

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
                assertFalse(testWorld.buyItemByID(armorID));

                // use for usage times
                for (int i = 0; i < (armor.getItemDurability() / 2); i++) {
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
    public void testHealthPotionStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof HealthPotion) {
                HealthPotion healthPotion = (HealthPotion) item;
                assertEquals(20, healthPotion.getItemCost());
                assertEquals(1, healthPotion.getItemDurability());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(healthPotion.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(healthPotionID));

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
                assertFalse(testWorld.buyItemByID(healthPotionID));

                // testing if Health Potion is working

                // get initial character health
                int mainCharacterHealth = newCharacter.getHealth();

                // initializing Vampire
                PathPosition vampirePathPosition = new PathPosition(characterPosition + 1, orderedPath);
                Vampire newVampire = new Vampire(vampirePathPosition);
                testWorld.addEnemy(newVampire);

                // run battle
                testWorld.runBattles();

                // check if health is less by 5
                assertEquals(mainCharacterHealth - newVampire.getDamage(), newCharacter.getHealth());

                // consume Health Potion
                potion.use(newCharacter);

                // check if health is back to max
                assertEquals(mainCharacterHealth, newCharacter.getHealth());
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testHelmetStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Helmet) {
                Helmet helmet = (Helmet) item;
                assertEquals(10, helmet.getItemCost());
                assertEquals(10, helmet.getItemDurability());
                assertEquals(10, helmet.getDefence());
                assertEquals(0, helmet.getCritDefence());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(helmet.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(helmetID));

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Helmet) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(helmetID));
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testOneRingStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof OneRing) {
                OneRing ring = (OneRing) item;
                assertEquals(500, ring.getItemCost());
                assertEquals(1, ring.getItemDurability());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(ring.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(oneRingID));

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof OneRing) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(oneRingID));
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testShieldStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Shield) {
                Shield shield = (Shield) item;
                assertEquals(10, shield.getItemCost());
                assertEquals(5, shield.getItemDurability());
                assertEquals(20, shield.getDefence());
                assertEquals(60, shield.getCritDefence());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(shield.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(shieldID));

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Shield) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(shieldID));
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testStaffStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Staff) {
                Staff staff = (Staff) item;
                assertEquals(8, staff.getItemCost());
                assertEquals(8, staff.getItemDurability());
                assertEquals(3, staff.getDamage());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(staff.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(staffID));

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Staff) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(staffID));
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testStakeStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Stake) {
                Stake stake = (Stake) item;
                assertEquals(8, stake.getItemCost());
                assertEquals(8, stake.getItemDurability());
                assertEquals(4, stake.getDamage());
                assertEquals(12, stake.getSpecialDamage());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(stake.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(stakeID));

                // item should appear in character's inventory
                boolean equipmentContains = false;
                for (Entity entities : testWorld.getCharacterInventory()) {
                    if (entities instanceof Stake) {
                        equipmentContains = true;
                    }
                }   
                assertTrue(equipmentContains);

                // cant buy another item
                assertFalse(testWorld.buyItemByID(stakeID));
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    public void testSwordStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Sword) {
                Sword sword = (Sword) item;
                assertEquals(10, sword.getItemCost());
                assertEquals(10, sword.getItemDurability());
                assertEquals(8, sword.getDamage());
                itemPresent = true;

                // character has enough gold to buy item
                newCharacter.setGold(sword.getItemCost());

                // character should be able to buy item
                assertTrue(testWorld.buyItemByID(swordID));

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
                assertFalse(testWorld.buyItemByID(swordID));

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
