package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.ElanMuske;

public class ElanMuskeTest {
    private int slugPosition = 1;
    private int characterPosition = 0;
    private ElanMuske elan;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /**
     * Test if the Elan spawns with an initial health of 40
     */
    @Test
    public void testExpectedHealth() {
        initializeWorld();
        int initialElanHealth = 40;
        assertEquals(initialElanHealth, elan.getHealth());
    }

    /**
     * Test if the Elan deals 25 damage
     */
    @Test
    void testElanDamage() {
        initializeWorld();
        int initialDamage = 25;
        assertEquals(initialDamage, elan.getDamage());
    }

    /**
     * Test if the Elan spawns with a battle radius of 1 (same as the Slug)
     */
    @Test
    void testElanBattleRadius() {
        initializeWorld();
        int initialBattleRadius = 1;
        assertEquals(initialBattleRadius, elan.getBattleRadius());
    }

    /**
     * Test if the Elan spawns with a support radius of 1 (same as the Slug)
     */
    @Test
    void testElanSupportRadius() {
        initializeWorld();
        int initialSupportRadius = 1;
        assertEquals(initialSupportRadius, elan.getSupportRadius());
    }

    /**
     * Test if the Elan gives 500 XP on defeat
     */
    @Test
    void testElanXP() {
        initializeWorld();
        int currentXP = newCharacter.getXP();
        int expectedXP = currentXP + 500;

        testWorld.runBattles();
        assertEquals(expectedXP, newCharacter.getXP());
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

        // initializing slug
        PathPosition elanPathPosition = new PathPosition(slugPosition, orderedPath);
        elan = new ElanMuske(elanPathPosition);
        testWorld.addEnemy(elan);
    }
}
