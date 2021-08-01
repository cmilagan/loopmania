package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldLoader;
import unsw.loopmania.cards.VampireCastleCard;
import unsw.loopmania.cards.BarracksCard;
import unsw.loopmania.cards.CampfireCard;
import unsw.loopmania.cards.TowerCard;
import unsw.loopmania.cards.ZombieGraveyardCard;
import unsw.loopmania.cards.VillageCard;
import unsw.loopmania.cards.TrapCard;

public class LoadCardTest {
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    @Test
    public void testVampireCastleCard() {
        initializeWorld();
        VampireCastleCard card = testWorld.loadVampireCard();
        assertEquals(card.getX(), 0);
        assertEquals(card.getY(), 0);
        assertEquals(1, testWorld.getNumCards());

    }

    @Test
    public void testBarracksCard() {
        initializeWorld();
        BarracksCard card = testWorld.loadBarracksCard();
        assertEquals(card.getX(), 0);
        assertEquals(card.getY(), 0);
        assertEquals(1, testWorld.getNumCards());

    }

    @Test
    public void testCampfireCard() {
        initializeWorld();
        CampfireCard card = testWorld.loadCampfireCard();
        assertEquals(card.getX(), 0);
        assertEquals(card.getY(), 0);
        assertEquals(1, testWorld.getNumCards());

    }

    @Test
    public void testTowerCard() {
        initializeWorld();
        TowerCard card = testWorld.loadTowerCard();
        assertEquals(card.getX(), 0);
        assertEquals(card.getY(), 0);
        assertEquals(1, testWorld.getNumCards());

    }

    @Test
    public void testZombieGraveCard() {
        initializeWorld();
        ZombieGraveyardCard card = testWorld.loadZombieGraveyardCard();
        assertEquals(card.getX(), 0);
        assertEquals(card.getY(), 0);
        assertEquals(1, testWorld.getNumCards());
    }

    @Test
    public void testVillageCard() {
        initializeWorld();
        VillageCard card = testWorld.loadVillageCard();
        assertEquals(card.getX(), 0);
        assertEquals(card.getY(), 0);
        assertEquals(1, testWorld.getNumCards());
    }

    @Test
    public void testTrapCard() {
        initializeWorld();
        TrapCard card = testWorld.loadTrapCard();
        assertEquals(card.getX(), 0);
        assertEquals(card.getY(), 0);
        assertEquals(1, testWorld.getNumCards());
    }

    @Test
    public void testLoadMax() {
        initializeWorld();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        testWorld.loadTrapCard();
        TrapCard card = testWorld.loadTrapCard();
        assertEquals(card.getX(), 9);
        assertEquals(10, testWorld.getNumCards());
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
