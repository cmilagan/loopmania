package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;

import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.AlliedSoldier;

class AlliedSoldierTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    /**
     * Testing if Allied Soldier has health = 3 on spawn
     */
    void testAlliedHealth() {
        initializeWorld();
        
        int initialHealth = 3;
        int alliedSoldierPosition = 0;
        PathPosition initialPosition = new PathPosition(alliedSoldierPosition, orderedPath);
        
        AlliedSoldier newAlliedSoldier = new AlliedSoldier(initialPosition);
        testWorld.addEntity(newAlliedSoldier);

        assertEquals(newAlliedSoldier.getHealth(), initialHealth);
    }

    @Test
    /**
     * Test if 5 is the maximum number of Allied Soldiers allowed
     */
    void testMaxSoldiers() {
        initializeWorld();

        for (int i = 1; i <= 5; i++) {
            PathPosition pos = new PathPosition(i, orderedPath);
            AlliedSoldier soldier = new AlliedSoldier(pos);
            testWorld.addEntity(soldier);
        }

        PathPosition pos6 = new PathPosition(6, orderedPath);
        AlliedSoldier soldier6 = new AlliedSoldier(pos6);

        Throwable t = null;
        try {
            testWorld.addEntity(soldier6);
        } catch (Error err) {
            t = err;
        }

        assertTrue(t instanceof Error);
        
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
