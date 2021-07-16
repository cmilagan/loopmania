package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.Vampire;

public class VampireTest {
    private int zombiePosition = 1;
    private int characterPosition = 0;
    private Vampire newVampire;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /**
     * Test if the Vampire spawns with an initial health of 20
     */
    @Test
    void testInitialHealth() {
        initializeWorld();
        int initialVampireHealth = 20;
        assertEquals(initialVampireHealth, newVampire.getHealth());
    }

    /**
     * Test if the Vampire spawns with damage of 20
     */
    @Test
    void testVampireDamage() {
        initializeWorld();
        int initialDamage = 20;
        assertEquals(initialDamage, newVampire.getDamage());
    }

    /**
     * Test if the Vampire spawns with a battle radius of 3
     */
    @Test
    void testVampireBattleRadius() {
        initializeWorld();
        int initialBattleRadius = 3;
        assertEquals(initialBattleRadius, newVampire.getBattleRadius());
    }

    /**
     * Test if the Vampire spawns with a support radius of 4
     */
    @Test
    void testVampireSupportRadius() {
        initializeWorld();
        int initialSupportRadius = 4;
        assertEquals(initialSupportRadius, newVampire.getSupportRadius());
    }

    /**
     * Test if the Vampire gives 200 XP on defeat
     */
    @Test
    void testVampireXP() {
        initializeWorld();
        int currentXP = newCharacter.getXP();
        int expectedXP = currentXP + 200;
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

        // initializing Vampire
        PathPosition vampirePathPosition = new PathPosition(zombiePosition, orderedPath);
        newVampire = new Vampire(vampirePathPosition);
        testWorld.addEnemy(newVampire);
    }
}
