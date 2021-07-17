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
import unsw.loopmania.buildings.TowerBuilding;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.buildings.VampireCastleBuilding;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.buildings.ZombieGraveyardBuilding;



// https://edstem.org/courses/5957/discussion/522938

public class BuildingPlacementTest {

    // building properties;
    // private int towerDamage = 4;
    // private int trapDamage = 8;
    // private int towerReach = 2;
    // private int campfireReach = 2;

    // private int towerExpiry = 5;
    // private int villageHeal = 10;
    // private int trapExpiry = 1;
    // private int vampireCastleExpiry = 5;
    // private int campfireExpiry = 5;
    // private int villageExpiry = 5;
    // private int zombiePitExpiry = 5;
    // private int barracksExpiry = 5;



    // world related fields
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    
    
    @Test
    void testValidAdjacentTile() {
        // tests valid placements of each building type
        initializeWorld();

        // Vampire castle -- Adjacent to path tiles (non path tile)
        VampireCastleBuilding newVCastle = new VampireCastleBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        testWorld.addBuilding(newVCastle);
        assertEquals(newVCastle.getX(), 1);
        assertEquals(newVCastle.getY(), 1);

        // Zombie castle -- Adjacent to path tiles (non path tile)
        ZombieGraveyardBuilding newGraveyard = new ZombieGraveyardBuilding(new SimpleIntegerProperty(3), new SimpleIntegerProperty(3));
        testWorld.addBuilding(newGraveyard);
        assertEquals(newGraveyard.getX(), 3);
        assertEquals(newGraveyard.getY(), 3);

        // Tower -- Adjacent to path tiles (non path tile)
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(3), new SimpleIntegerProperty(2));
        testWorld.addBuilding(newTower);
        assertEquals(newTower.getX(), 3);
        assertEquals(newTower.getY(), 2);

        List<Building> testWorldBuildings = testWorld.getBuildings();
        int count = 0;
        for (Building b: testWorldBuildings) {
            if (b instanceof TowerBuilding || b instanceof VampireCastleBuilding || b instanceof ZombieGraveyardBuilding) {
                count++;
            }
        }
        assertEquals(testWorldBuildings.size(), count);
        
    }

    @Test
    void testValidPathPlacement() {
        // testing that appropiate buildings can be placed on 
        initializeWorld();

        // Village -- Path tile
        VillageBuilding newVillage = new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));
        testWorld.addBuilding(newVillage);
        assertEquals(newVillage.getX(), 1);
        assertEquals(newVillage.getY(), 0);


        // Barracks -- Path tile
        BarracksBuilding newBarracks = new BarracksBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(0));
        testWorld.addBuilding(newBarracks);
        assertEquals(newBarracks.getX(), 2);
        assertEquals(newBarracks.getY(), 0);

        // Trap -- Path tile
        TrapBuilding newTrap = new TrapBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(1));
        testWorld.addBuilding(newTrap);
        assertEquals(newTrap.getX(), 2);
        assertEquals(newTrap.getY(), 1);

        List<Building> testWorldBuildings = testWorld.getBuildings();
        int count = 0;
        for (Building b: testWorldBuildings) {
            if (b instanceof TrapBuilding || b instanceof VillageBuilding || b instanceof BarracksBuilding) {
                count++;
            }
        }
        assertEquals(testWorldBuildings.size(), count);
        
    }

    @Test
    void testPlaceNonPath() {
        // Buildings which can be placed on any non-path tile
        initializeWorld();
        // Campfire -- Any non-path tile
        CampfireBuilding newCampfire = new CampfireBuilding(new SimpleIntegerProperty(4), new SimpleIntegerProperty(4));
        testWorld.addBuilding(newCampfire);
        assertEquals(newCampfire.getX(), 4);
        assertEquals(newCampfire.getY(), 4);
        List<Building> testWorldBuildings = testWorld.getBuildings();
        for (Building b: testWorldBuildings) {
            assertTrue(b instanceof CampfireBuilding);
        }

    }
    @Test
    void testInvalidPathPlacement() {
        initializeWorld();
        // Testing that buildings cannot be placed in invalid positions
        CampfireBuilding newCampfire = new CampfireBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(newCampfire, null);
        
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(newTower, null);

        VampireCastleBuilding newVCastle = new VampireCastleBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(newVCastle, null);

        ZombieGraveyardBuilding newZombiePit = new ZombieGraveyardBuilding(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        assertEquals(newZombiePit, null);
    }

    @Test 
    void testInvalidNonPath() {
        initializeWorld();
        // Placing invalid buildings on non-path tiles
         // Village -- Path tile
        VillageBuilding newVillage = new VillageBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertEquals(newVillage, null);

         // Barracks -- Path tile
        BarracksBuilding newBarracks = new BarracksBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertEquals(newBarracks, null);
         // Trap -- Path tile
        TrapBuilding newTrap = new TrapBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        assertEquals(newTrap, null);
    }

    void testInvalidNonAdjacent() {
        initializeWorld();

        // These buildings can only be placed adjacent to path tiles.
        TowerBuilding newTower = new TowerBuilding(new SimpleIntegerProperty(4), new SimpleIntegerProperty(4));
        assertEquals(newTower, null);

        VampireCastleBuilding newVCastle = new VampireCastleBuilding(new SimpleIntegerProperty(4), new SimpleIntegerProperty(4));
        assertEquals(newVCastle, null);

        ZombieGraveyardBuilding newZombiePit = new ZombieGraveyardBuilding(new SimpleIntegerProperty(4), new SimpleIntegerProperty(4));
        assertEquals(newZombiePit, null);
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
