package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.Zombie;

public class ZombieTest {
    private int zombiePosition = 1;
    private int characterPosition = 0;
    private Zombie newZombie;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /**
     * Test if the zombie spawns with an initial health of 10
     */
    @Test
    void testInitialHealth() {
        initializeWorld();
        int initialZombieHealth = 10;
        assertEquals(initialZombieHealth, newZombie.getHealth());
    }

    /**
     * Test if the zombie spawns with damage of 8
     */
    @Test
    void testZombieDamage() {
        initializeWorld();
        int initialDamage = 8;
        assertEquals(initialDamage, newZombie.getDamage());
    }

    /**
     * Test if the zombie spawns with a battle radius of 1
     */
    @Test
    void testZombieBattleRadius() {
        initializeWorld();
        int initialBattleRadius = 1;
        assertEquals(initialBattleRadius, newZombie.getBattleRadius());
    }

    /**
     * Test if the zombie spawns with a support radius of 2
     */
    @Test
    void testZombieSupportRadius() {
        initializeWorld();
        int initialSupportRadius = 2;
        assertEquals(initialSupportRadius, newZombie.getSupportRadius());
    }

    /**
     * Test if a zombie gives 100 XP on defeat
     */
    @Test
    void testZombieXP() {
        initializeWorld();
        int currentXP = newCharacter.getXP();
        int expectedXP = currentXP + 100;
        testWorld.killEnemy(newZombie);
        assertEquals(currentXP + 100, expectedXP);
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
        PathPosition zombiePathPosition = new PathPosition(zombiePosition, orderedPath);
        newZombie = new Zombie(zombiePathPosition);
        testWorld.addEnemy(newZombie);
    }
}