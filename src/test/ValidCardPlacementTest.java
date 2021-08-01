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
import unsw.loopmania.cards.VampireCastleCard;
import unsw.loopmania.cards.BarracksCard;
import unsw.loopmania.cards.CampfireCard;
import unsw.loopmania.cards.TowerCard;
import unsw.loopmania.cards.ZombieGraveyardCard;
import unsw.loopmania.cards.VillageCard;
import unsw.loopmania.cards.TrapCard;
import unsw.loopmania.buildings.VampireCastleBuilding;
import unsw.loopmania.buildings.BarracksBuilding;
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.CampfireBuilding;
import unsw.loopmania.buildings.TowerBuilding;
import unsw.loopmania.buildings.ZombieGraveyardBuilding;
import unsw.loopmania.buildings.VillageBuilding;
import unsw.loopmania.buildings.TrapBuilding;


public class ValidCardPlacementTest {
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    public void testVampireCardToBuilding() {
        initializeWorld();
        VampireCastleCard card = testWorld.loadVampireCard();
        boolean valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 1);
        assertEquals(true, valid);
        valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 0);
        assertEquals(false, valid);
        Building building = testWorld.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 1, 1);
        assertTrue(building instanceof VampireCastleBuilding);
        assertTrue(testWorld.getBuildings().size() == 1);
        assertTrue(testWorld.getNumCards() == 0);

    }

    @Test
    public void testBarracksCardToBuilding() {
        initializeWorld();
        BarracksCard card = testWorld.loadBarracksCard();
        boolean valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 0);
        assertEquals(true, valid);
        valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 1);
        assertEquals(false, valid);
        Building building = testWorld.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 1, 0);
        assertTrue(building instanceof BarracksBuilding);
        assertTrue(testWorld.getBuildings().size() == 1);
        assertTrue(testWorld.getNumCards() == 0);

    }

    @Test
    public void testTrapCardToBuilding() {
        initializeWorld();
        TrapCard card = testWorld.loadTrapCard();
        boolean valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 0);
        assertEquals(true, valid);
        valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 1);
        assertEquals(false, valid);
        Building building = testWorld.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 1, 0);
        assertTrue(building instanceof TrapBuilding);
        assertTrue(testWorld.getBuildings().size() == 1);
        assertTrue(testWorld.getNumCards() == 0);

    }

    @Test
    public void testCampfireCardToBuilding() {
        initializeWorld();
        CampfireCard card = testWorld.loadCampfireCard();
        boolean valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 1);
        assertEquals(true, valid);
        valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 0);
        assertEquals(false, valid);
        Building building = testWorld.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 1, 1);
        assertTrue(building instanceof CampfireBuilding);
        assertTrue(testWorld.getBuildings().size() == 1);
        assertTrue(testWorld.getNumCards() == 0);

    }


    @Test
    public void testVillageCardToBuilding() {
        initializeWorld();
        VillageCard card = testWorld.loadVillageCard();
        boolean valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 0);
        assertEquals(true, valid);
        valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 1);
        assertEquals(false, valid);
        Building building = testWorld.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 1, 0);
        assertTrue(building instanceof VillageBuilding);
        assertTrue(testWorld.getBuildings().size() == 1);
        assertTrue(testWorld.getNumCards() == 0);

    }

    @Test
    public void testTowerCardToBuilding() {
        initializeWorld();
        TowerCard card = testWorld.loadTowerCard();
        boolean valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 1);
        assertEquals(true, valid);
        valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 0);
        assertEquals(false, valid);
        Building building = testWorld.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 1, 1);
        assertTrue(building instanceof TowerBuilding);
        assertTrue(testWorld.getBuildings().size() == 1);
        assertTrue(testWorld.getNumCards() == 0);

    }


    @Test
    public void testZombieGraveCardToBuilding() {
        initializeWorld();
        ZombieGraveyardCard card = testWorld.loadZombieGraveyardCard();
        boolean valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 1);
        assertEquals(true, valid);
        valid = testWorld.checkValidCardPlacement(card.getX(), card.getY(), 1, 0);
        assertEquals(false, valid);
        Building building = testWorld.convertCardToBuildingByCoordinates(card.getX(), card.getY(), 1, 1);
        assertTrue(building instanceof ZombieGraveyardBuilding);
        assertTrue(testWorld.getBuildings().size() == 1);
        assertTrue(testWorld.getNumCards() == 0);
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
