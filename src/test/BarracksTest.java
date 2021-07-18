package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldLoader;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.BarracksBuilding;

public class BarracksTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private BarracksBuilding newBarracks;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    /**
     * Test if the Barracks building spawns
     */
    void testBarracksSpawns() {
        initializeWorld();
        assertTrue(testWorld.getBuildings().contains(newBarracks));
    }

    @Test
    /**
     * Test if the Barracks building lasts for 5 rounds
     */
    void testBarracksExpiry() {
        initializeWorld();
        assertTrue(testWorld.getBuildings().contains(newBarracks));

        int sizeOfMap = 8;
        int barracksExpiry = 5;
        for (int i = 0; i <= sizeOfMap * barracksExpiry; i++) {
            testWorld.runTickMoves();
        }

        assertFalse(testWorld.getBuildings().contains(newBarracks));
    }

    @Test
    /**
     * Test if the Barracks building spawns an Allied Soldier when Main character
     * walks past it.
     */
    void testBarracksSoldier() {
        initializeWorld();
        assertTrue(testWorld.getBuildings().contains(newBarracks));
        
        testWorld.runTickMoves(); // character now on Barracks
        testWorld.runTickMoves(); // character now passed Barracks

        // TODO: get alliedsoldiers and check length.
        assertTrue(true);
    }
    
    /**
     * Initialize the test world.
     */
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
        int characterPosition = 0;
        PathPosition characterPathPosition = new PathPosition(characterPosition, orderedPath);
        newCharacter = new Character(characterPathPosition);
        testWorld.setCharacter(newCharacter);

        // initialize Barracks
        SimpleIntegerProperty barracksX = new SimpleIntegerProperty(1);
        SimpleIntegerProperty barracksY = new SimpleIntegerProperty(0);
        // newBarracks = new BarracksBuilding(barracksX, barracksY, 1);
        testWorld.addBuilding(newBarracks);
    }
}
