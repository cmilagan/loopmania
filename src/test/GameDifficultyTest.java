package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.modes.BerserkerMode;
import unsw.loopmania.modes.ConfusingMode;
import unsw.loopmania.modes.StandardMode;
import unsw.loopmania.modes.SurvivalMode;

public class GameDifficultyTest {
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    private Character newCharacter;
    private int characterPosition = 0;
    private LoopManiaWorld testWorld;
    private StandardMode standard;
    private SurvivalMode survival;
    private BerserkerMode berserker;
    private ConfusingMode confusing;
    
    /**
     * Check that the game difficulty is standard
     */
    @Test
    public void standardTest() {
        System.out.println("TEST - Standard Mode");
        initializeWorld();
        // Set it to standard
        testWorld.playStandard();
        assertTrue(testWorld.getStandard());
        assertFalse(testWorld.getBerserker());
        assertFalse(testWorld.getSurvival());
        assertFalse(testWorld.getConfusing());
        assertEquals(standard.getWinGold(), testWorld.getWinGold());
        assertEquals(standard.getWinLoop(), testWorld.getWinLoops());
        assertEquals(standard.getWinXP(), testWorld.getWinXp());
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
        testWorld.playSurvival();
        assertTrue(testWorld.getSurvival());
        assertFalse(testWorld.getBerserker());
        assertFalse(testWorld.getStandard());
        assertFalse(testWorld.getConfusing());
        assertEquals(survival.getWinGold(), testWorld.getWinGold());
        assertEquals(survival.getWinLoop(), testWorld.getWinLoops());
        assertEquals(survival.getWinXP(), testWorld.getWinXp());
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
        testWorld.playBerserker();
        assertTrue(testWorld.getBerserker());
        assertFalse(testWorld.getStandard());
        assertFalse(testWorld.getSurvival());
        assertFalse(testWorld.getConfusing());
        assertEquals(berserker.getWinGold(), testWorld.getWinGold());
        assertEquals(berserker.getWinLoop(), testWorld.getWinLoops());
        assertEquals(berserker.getWinXP(), testWorld.getWinXp());
        System.out.println("--- Passed ---\n");
    }

    /**
     * Check that the game difficulty is confusing
     */
    @Test
    public void confusingTest() {
        System.out.println("TEST - Standard Mode");
        initializeWorld();
        // Set it to standard
        testWorld.playConfusing();
        assertTrue(testWorld.getConfusing());
        assertFalse(testWorld.getBerserker());
        assertFalse(testWorld.getSurvival());
        assertFalse(testWorld.getStandard());
        assertEquals(confusing.getWinGold(), testWorld.getWinGold());
        assertEquals(confusing.getWinLoop(), testWorld.getWinLoops());
        assertEquals(confusing.getWinXP(), testWorld.getWinXp());
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

        // initalising the modes
        standard = new StandardMode();
        survival = new SurvivalMode();
        berserker = new BerserkerMode();
        confusing = new ConfusingMode();
    }
}
