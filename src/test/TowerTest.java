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
import unsw.loopmania.buildings.TowerBuilding;
import unsw.loopmania.npcs.Slug;

public class TowerTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private int towerDamage = 4;
    private Slug newSlug;
    private int expectedHealth = -1;
    private int slugPosition = 0;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    public void checkTowerDamageTest(){
        System.out.println("TEST - Checking tower damage");
        // Create world
        initializeWorld();
        // Place down the tower
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(1),new SimpleIntegerProperty(1));
        testWorld.addBuilding(newTower);
        // Check if the tower is in the right location
        assertEquals(Pair.with(1, 1), Pair.with(newTower.getX(), newTower.getY()));
        // Check during battle if the tower attacks first and the amount of damage
        // Tower attacks first if the character takes no damage
        assertEquals(newTower.getDamage(), towerDamage);
        int initCharHp = newCharacter.getHealth();
        int initSlugHp = newSlug.getHealth();
        testWorld.runBattles();
        assertEquals(initCharHp, newCharacter.getHealth());
        assertEquals(initSlugHp - newTower.getDamage(), expectedHealth);
        System.out.println("--- Passed ---\n");
    }
    
    @Test
    public void checkExpiryTest(){
        System.out.println("TEST - Check the tower expires");
        initializeWorld();
        // Place the tower down
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        testWorld.addBuilding(newTower);
        // Make the tower expire
        newTower.setExpiry(0);
        // Check if the tower is gone
        testWorld.removeExpiredBuildings();
        assertTrue(testWorld.getBuildings().isEmpty());
        System.out.println("--- Passed ---\n");
    }

    @Test
    public void checkTowerRadiusTest(){
        System.out.println("TEST - Check the tower radius");
        initializeWorld();
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(1),new SimpleIntegerProperty(2));
        testWorld.addBuilding(newTower);
        // Check if the tower engaged in battle when out of range
        int initHealth = newCharacter.getHealth();
        testWorld.runBattles();
        // Check that the character did take damage
        assertEquals(initHealth - newSlug.getDamage() * 3, newCharacter.getHealth());
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

        // initializing slug
        PathPosition slugPathPosition = new PathPosition(slugPosition, orderedPath);
        newSlug = new Slug(slugPathPosition);
        testWorld.addEnemy(newSlug);
    }
}
