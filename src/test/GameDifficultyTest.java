package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.modes.GameDifficulty;

public class GameDifficultyTest {
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    private Character newCharacter;
    private int characterPosition = 0;
    private LoopManiaWorld testWorld;
    private GameDifficulty mode = new GameDifficulty();
    
    /**
     * Check that the game difficulty is standard
     */
    @Test
    public void standardTest() {
        System.out.println("TEST - Standard Mode");
        initializeWorld();
        // Set it to standard
        mode.playStandard();
        assertTrue(mode.getStandard());
        assertFalse(mode.getBerserker());
        assertFalse(mode.getSurvival());
        System.out.println("--- Passed ---\n");
    }

    /**
     * Check that the game difficulty is survival
     */
    @Test
    public void survivalTest() {
        System.out.println("TEST - Survival Mode");
        initializeWorld();
        // Set it to survival
        mode.playSurvival();
        assertTrue(mode.getSurvival());
        assertFalse(mode.getBerserker());
        assertFalse(mode.getStandard());
        System.out.println("--- Passed ---\n");
    }

    /**
     * Check that the game difficulty is berserker
     */
    @Test
    public void berserkerTest() {
        System.out.println("TEST - Berserker Mode");
        initializeWorld();
        // Set it to survival
        mode.playBerserker();
        assertTrue(mode.getBerserker());
        assertFalse(mode.getStandard());
        assertFalse(mode.getSurvival());
        System.out.println("--- Passed ---\n");
    }

    /**
     * Setup template world
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
        PathPosition characterPathPosition = new PathPosition(characterPosition, orderedPath);
        newCharacter = new Character(characterPathPosition);
        testWorld.setCharacter(newCharacter);
    }
}
