package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import unsw.loopmania.StaticEntity;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.buildings.TowerBuilding;

public class CampfireTest {
    private int initDmg = 2;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    public void checkPlacementTest(){
        System.out.println("TEST - Checking tower damage");
        // Create world
        initializeWorld();
        // Place down campfire
        CampfireBuilding newCampfire = new CampfireBuilding(new SimpleIntegerProperty(1),new SimpleIntegerProperty(1), 0);
        testWorld.addBuilding(newCampfire);
        // Check it is in right location
        assertEquals(Pair.with(1, 1), newCampfire.getX(), newCampfire.getY());
        System.out.println("--- Passed ---\n");
    }
    
    @Test
    public void checkBuffTest(){
        System.out.println("TEST - Checking tower damage");
        initializeWorld();
        // Place down the campfire
        CampfireBuilding newCampfire = new CampfireBuilding(new SimpleIntegerProperty(1),new SimpleIntegerProperty(1), 0);
        testWorld.addBuilding(newCampfire);
        // Make Character loop through map
        testWorld.runTickMoves();
        // Check if the character damage increases when they go near the campfire
        assertEquals(newCharacter.getDamage(), initDmg * 2);
        System.out.println("--- Passed ---\n");
    }

    @Test
    public void checkExpiryTest(){
        System.out.println("TEST - Checking tower damage");
        // Create world
        initializeWorld();
        // Place down campfire
        CampfireBuilding newCampfire = new CampfireBuilding(new SimpleIntegerProperty(1),new SimpleIntegerProperty(1), 0);
        testWorld.addBuilding(newCampfire);
        // Check if the campfire is gone
        testWorld.runTickMoves();
        assertTrue(testWorld.getBuildings().isEmpty());
        System.out.println("--- Passed ---\n");
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