package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import unsw.loopmania.buildings.HeroCastleBuilding;
import unsw.loopmania.buildings.TowerBuilding;

public class TowerTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private int towerDamage;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    public void checkTowerDamageTest(){
        System.out.println("TEST - Checking tower damage");
        // Create world
        initializeWorld();
        // Place down the tower
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(1),new SimpleIntegerProperty(1));
        testWorld.addBuilding(newTower);
        assertEquals(newTower.getDamage(), towerDamage);
        // Check if the tower is in the right location
        assertEquals(Pair.with(1, 1), newTower.getPosition());
        // Check during battle if the tower attacks first and the amount of damage
        System.out.println("--- Passed ---\n");
    }
    
    @Test
    public void checkExpiryTest(){
        System.out.println("TEST - Check the tower expires");
        initializeWorld();
        // Place the tower down
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        testWorld.addBuilding(newTower);
        // Make Character loop through map
        testWorld.runTickMoves();
        // Check if the tower is gone

        System.out.println("--- Passed ---\n");
    }

    @Test
    public void checkTowerRadiusTest(){
        System.out.println("TEST - Check the tower radius");
        initializeWorld();
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        testWorld.addBuilding(newTower);
        // Check if the tower engaged in battle when in range

        // Check if the tower engaged in battle when out of range
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
