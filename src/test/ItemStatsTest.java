package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.items.Armor;
import unsw.loopmania.items.HealthPotion;
import unsw.loopmania.items.Helmet;
import unsw.loopmania.items.OneRing;
import unsw.loopmania.items.Shield;
import unsw.loopmania.items.Staff;
import unsw.loopmania.items.Stake;
import unsw.loopmania.items.Sword;

public class ItemStatsTest {
    private int characterPosition = 0;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    public ItemStatsTest() {
        initializeWorld();
    }

    @Test
    public void testArmorStats() {
        Armor armor = new Armor(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(20, armor.getItemCost());
        assertEquals(10, armor.getItemDurability());
        assertEquals(0.4, armor.useDefence());
        assertEquals(0, armor.useCritDefence());

        // usage should be incremented by 1
        assertEquals(1, armor.getUsage());
    }

    @Test
    public void testHealthPotionStats() {
        HealthPotion potion = new HealthPotion(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(20, potion.getItemCost());
        assertEquals(1, potion.getItemDurability());
        potion.use(newCharacter);

        // usage should be incremented by 1
        assertEquals(1, potion.getUsage());
    }

    @Test
    public void testHelmetStats() {
        Helmet helmet = new Helmet(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(10, helmet.getItemCost());
        assertEquals(10, helmet.getItemDurability());
        assertEquals(0.1, helmet.useDefence());
        assertEquals(0, helmet.useCritDefence());

        // usage should be incremented by 1
        assertEquals(1, helmet.getUsage());
    }

    @Test
    public void testOneRingStats() {
        OneRing onering = new OneRing(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(500, onering.getItemCost());
        assertEquals(1, onering.getItemDurability());
    }

    @Test
    public void testShieldStats() {
        Shield shield = new Shield(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(10, shield.getItemCost());
        assertEquals(5, shield.getItemDurability());
        assertEquals(0.2, shield.useDefence());
        assertEquals(0.6, shield.useCritDefence());

        // usage should be incremented by 2
        assertEquals(2, shield.getUsage());
    }

    @Test
    public void testStaffStats() {
        Staff staff = new Staff(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(25, staff.getItemCost());
        assertEquals(8, staff.getItemDurability());
        assertEquals(3, staff.inflictDamage());

        // usage should be incremented by 1
        assertEquals(1, staff.getUsage());
    }

    @Test
    public void testStakeStats() {
        Stake stake = new Stake(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(8, stake.getItemCost());
        assertEquals(8, stake.getItemDurability());
        assertEquals(4, stake.inflictDamage());
        assertEquals(12, stake.getSpecialDamage());

        // usage should be incremented by 2
        assertEquals(2, stake.getUsage());
    }

    @Test
    public void testSwordStats() {
        Sword sword = new Sword(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        assertEquals(10, sword.getItemCost());
        assertEquals(10, sword.getItemDurability());
        assertEquals(8, sword.inflictDamage());

        // usage should be incremented by 1
        assertEquals(1, sword.getUsage());
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
    }
}
