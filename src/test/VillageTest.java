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
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.buildings.Building;



// https://edstem.org/courses/5957/discussion/522938

public class VillageTest {

    // building properties;
    private int villageHeal = 10;
    private int villageExpiry = 5;

    // world related fields
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    void testVillageHeal() {
        // village restores 10 health
        initializeWorld();

        VillageBuilding newVillage = new VillageBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(0));
        testWorld.addBuilding(newVillage);
        assertEquals(villageHeal, newVillage.getHeal());
        assertEquals(villageExpiry, newVillage.getExpiry());
        newCharacter.setHealth(50);
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        assertEquals(50 + villageHeal, newCharacter.getHealth());
    }

    @Test
    void testVillageHealMax() {
        // heals character on full health
        initializeWorld();

        VillageBuilding newVillage = new VillageBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(0));
        testWorld.addBuilding(newVillage);
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should not gain extra health for passing through village
        assertEquals(newCharacter.getMaxHealth(), newCharacter.getHealth());
    }
    @Test
    void testVillageExpiry() {
        initializeWorld();

        VillageBuilding newVillage = new VillageBuilding(new SimpleIntegerProperty(2), new SimpleIntegerProperty(0));
        testWorld.addBuilding(newVillage);
        List<Building> testWorldBuildings = testWorld.getBuildings();
        assertEquals(1, testWorldBuildings.size());
        for (int i = 0; i < 8 * villageExpiry; i++) {
            testWorld.runTickMoves();
        }
        assertEquals(0, newVillage.getExpiry());
        assertEquals(0, testWorldBuildings.size());
    }
    
    
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
