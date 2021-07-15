package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.Character;
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

/**
 * test the battleItems array located in LoopManiaWorld 
 * check if all items are included with correct item stats
 * 
 * Note: this array is used to display the items in the Shop menu
 */
public class ShopItemTest {
    private int characterPosition = 0;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<BattleItem> battleItems;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    
    @Test
    void testArmorStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Armor) {
                Armor armor = (Armor) item;
                assertEquals(20, armor.getItemCost());
                assertEquals(10, armor.getItemDurability());
                assertEquals(40, armor.getDefence());
                assertEquals(0, armor.getCritDefence());
                itemPresent = true;
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    void testHealthPotionStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof HealthPotion) {
                HealthPotion healthPotion = (HealthPotion) item;
                assertEquals(20, healthPotion.getItemCost());
                assertEquals(1, healthPotion.getItemDurability());
                itemPresent = true;
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    void testHelmetStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Helmet) {
                Helmet helmet = (Helmet) item;
                assertEquals(10, helmet.getItemCost());
                assertEquals(10, helmet.getItemDurability());
                assertEquals(10, helmet.getDefence());
                assertEquals(0, helmet.getCritDefence());
                itemPresent = true;
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    void testOneRingStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof OneRing) {
                OneRing ring = (OneRing) item;
                assertEquals(500, ring.getItemCost());
                assertEquals(1, ring.getItemDurability());
                itemPresent = true;
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    void testShieldStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Shield) {
                Shield shield = (Shield) item;
                assertEquals(10, shield.getItemCost());
                assertEquals(5, shield.getItemDurability());
                assertEquals(20, shield.getDefence());
                assertEquals(60, shield.getCritDefence());
                itemPresent = true;
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    void testStaffStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Staff) {
                Staff staff = (Staff) item;
                assertEquals(8, staff.getItemCost());
                assertEquals(8, staff.getItemDurability());
                assertEquals(3, staff.getDamage());
                itemPresent = true;
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    void testStakeStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Stake) {
                Stake stake = (Stake) item;
                assertEquals(8, stake.getItemCost());
                assertEquals(8, stake.getItemDurability());
                assertEquals(4, stake.getDamage());
                assertEquals(12, stake.getSpecialDamage());
                itemPresent = true;
            }
        }
        assertTrue(itemPresent);
    }

    @Test
    void testSwordStats() {
        boolean itemPresent = false;
        for (BattleItem item : battleItems) {
            if (item instanceof Sword) {
                Sword sword = (Sword) item;
                assertEquals(10, sword.getItemCost());
                assertEquals(10, sword.getItemDurability());
                assertEquals(8, sword.getDamage());
                itemPresent = true;
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
