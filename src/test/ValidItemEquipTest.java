package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.items.AttackItem;
import unsw.loopmania.items.Sword;
import unsw.loopmania.items.Helmet;
import unsw.loopmania.items.Item;
import unsw.loopmania.items.Shield;
import unsw.loopmania.items.Armor;

public class ValidItemEquipTest {
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    public void testValidAttackItemEquip() {
        initializeWorld();
        Item returned;
        AttackItem item = new AttackItem(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1), 0, 0, 0);
        testWorld.addUnequippedItem(item);
        returned = testWorld.checkValidItemSlot(1, 1, 0, 0);
        assertTrue(returned == null);
        returned = testWorld.checkValidItemSlot(1, 1, 0, 2);
        assertTrue(returned != null);

    }

    @Test
    public void testValidHelmetEquip() {
        initializeWorld();
        Item returned;
        Helmet item = new Helmet(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        testWorld.addUnequippedItem(item);
        returned = testWorld.checkValidItemSlot(1, 1, 0, 0);
        assertTrue(returned == null);
        returned = testWorld.checkValidItemSlot(1, 1, 1, 1);
        assertTrue(returned != null);
    }

    @Test
    public void testValidShieldEquip() {
        initializeWorld();
        Item returned;
        Shield item = new Shield(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        testWorld.addUnequippedItem(item);
        returned = testWorld.checkValidItemSlot(1, 1, 0, 0);
        assertTrue(returned == null);
        returned = testWorld.checkValidItemSlot(1, 1, 3, 2);
        assertTrue(returned != null);
    }

    @Test
    public void testValidArmorEquip() {
        initializeWorld();
        Item returned;
        Armor item = new Armor(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        testWorld.addUnequippedItem(item);
        returned = testWorld.checkValidItemSlot(1, 1, 0, 0);
        assertTrue(returned == null);
        returned = testWorld.checkValidItemSlot(1, 1, 1, 2);
        assertTrue(returned != null);
    }

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
    }
}
