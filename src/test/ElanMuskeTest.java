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
import unsw.loopmania.npcs.Zombie;


public class ElanMuskeTest {
    private int elanPosition = 1;
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
        addElan();
        int initialElanHealth = 40;
        assertEquals(initialElanHealth, elan.getHealth());
    }

    /**
     * Test if the Elan deals 25 damage
     */
    @Test
    void testElanDamage() {
        initializeWorld();
        addElan();
        int initialDamage = 25;
        assertEquals(initialDamage, elan.getDamage());
    }

    /**
     * Test if the Elan spawns with a battle radius of 1 (same as the Slug)
     */
    @Test
    void testElanBattleRadius() {
        initializeWorld();
        addElan();
        int initialBattleRadius = 1;
        assertEquals(initialBattleRadius, elan.getBattleRadius());
    }

    /**
     * Test if the Elan spawns with a support radius of 1 (same as the Slug)
     */
    @Test
    void testElanSupportRadius() {
        initializeWorld();
        addElan();
        int initialSupportRadius = 1;
        assertEquals(initialSupportRadius, elan.getSupportRadius());
    }

    /**
     * Test if the Elan gives 500 XP on defeat
     */
    @Test
    void testElanXP() {
        initializeWorld();
        addElan();
        int currentXP = newCharacter.getXP();
        int expectedXP = currentXP + 500;

        testWorld.runBattles();
        assertEquals(expectedXP, newCharacter.getXP());
    }

    /**
     * Test if Elan Muske heals other enemies in his support radius
     */
    @Test
    void testCheckEnemiesHealed() {
        initializeWorld();

        // spawn Zombie with reduced health
        PathPosition zombiePathPosition = new PathPosition(elanPosition, orderedPath);
        Zombie zombie = new Zombie(zombiePathPosition);
        zombie.setHealth(5);
        testWorld.addEnemy(zombie);

        assertEquals(5, zombie.getHealth());
        
        testWorld.runTickMoves();

        assertEquals(10, zombie.getHealth());
    }

    // TODO: add test to check if the price of doggieCoin goes up on spawn
    // TODO: add test to check if the price of doggieCoin goes down on defeat

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

    public void addElan() {
        // initializing Elan
        PathPosition elanPathPosition = new PathPosition(elanPosition, orderedPath);
        elan = new ElanMuske(elanPathPosition);
        testWorld.addEnemy(elan);
    }
} 
