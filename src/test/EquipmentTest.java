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

import unsw.loopmania.PathPosition;
import unsw.loopmania.weapons.Staff;
import unsw.loopmania.weapons.Stake;
import unsw.loopmania.weapons.Sword;

class EquipmentTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    /**
     *  Equiping a sword, and fighting enemy test
     */
    void testBattleWithSwordEncounter() {
        initializeWorld();
        Sword sword = new Sword(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        // world.equip(sword);
        // Spawn Enemy

        // Fight Enemy

        // Check health of enemy and character

        // Check results
        // world.unequip(sword);
    }

    @Test
    /**
     *  Equiping a staff, and fighting enemy test
     */
    void testBattleWithStaffEncounter() {
        initializeWorld();
        Staff staff = new Staff(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        // world.equip(armor);
        // Spawn Enemy

        // Fight Enemy

        // Check health of enemy and character

        // Check results
        // world.unequip(armor);
    }

    @Test
    /**
     *  Equiping a stake, and fighting enemy test
     */
    void testBattleWithStakeEncounter() {
        initializeWorld();
        Stake stake = new Stake(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        // world.equip(stake);
        // Spawn Enemy

        // Fight Enemy

        // Check health of enemy and character

        // Check results
        // world.unequip(stake);
    }

    // setup template world
    public void initializeWorld() {
        int LOOP_SIZE = 3;
        int characterPosition = 0;
        
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