package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.javatuples.Pair;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.Zombie;

class MainCharacterTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /**
     * Assumptions made:
     *  - Main character spawns with health = 100
     */
    
    /**
     * When the character first spawns, he should have an initial health 100.
     */
    @Test
    void testInitialHealth() {
        initializeWorld();

        int initialHealth = 100;
        assertEquals(newCharacter.getHealth(), initialHealth);

        // Health should remain the same as no battles should occur
        testWorld.runTickMoves();
        assertEquals(newCharacter.getHealth(), initialHealth);
    }


    /**
     * When the character engages in battle with a Zombie, he should have less
     * health than when he spawned.
     */
    @Test
    void testHealthAfterBattle() {
        initializeWorld();

        PathPosition zombiePosition = new PathPosition(0, orderedPath);
        Zombie newZombie = new Zombie(zombiePosition);
        testWorld.addEntity(newZombie);
        testWorld.runBattles();

        int initialHealth = 100;

        assertTrue(newCharacter.getHealth() < initialHealth);
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
