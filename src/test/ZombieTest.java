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
import unsw.loopmania.PathPosition;
import unsw.loopmania.items.Anduril;
import unsw.loopmania.items.Sword;
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

        // giving character a Sword so he wins the battle with Zombie
        Sword sword = new Sword(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        newCharacter.setWeapon(sword);

        testWorld.runBattles();
        assertEquals(expectedXP, newCharacter.getXP());
    }
   
    /**
     * Test to check if a Zombie is defeated by a character wielding Anduril, Flame of the West, which does 20 damage
     * Given that Elan has a health of 10 and damage of 8,
     *      The character with Anduril has a health of 100 and a damage of 20, and
     *      The Zombie moves first,
     * The battle should end with the Character having a health of 92 or 76 remaining.
     * This is because of the 20% chance of a Critical hit occuring.
     */
    @Test
    public void testZombieDamageAnduril() {
        initializeWorld();

        Anduril anduril = new Anduril(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        newCharacter.setWeapon(anduril);

        testWorld.runBattles();

        int characterHealth = newCharacter.getHealth();
        assertTrue(characterHealth == 92 || characterHealth == 76);
    }

    /**
     * Test to check if a Zombie is defeated by a character holding the Tree Stump which reduces incoming damage by 40%
     * Given that the Zombie has a health of 10 and damage of 8 which is reduced by 40% to be 4.8
     *      The character with a Sword has a health of 100 and a damage of 8, and
     *      Elan moves first,
     * The battle should end with the Character having a health of 50 remaining
     */
    @Test
    public void testZombieDefenceTreeStump() {
        initializeWorld();

        TreeStump treeStump = new TreeStump(new SimpleIntegerProperty(), new SimpleIntegerProperty());
        newCharacter.setTreeStump(treeStump);

        testWorld.runBattles();

        int characterHealth = newCharacter.getHealth();
        assertTrue(characterHealth == 92 || characterHealth == 76);
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

        // initializing Zombie
        PathPosition zombiePathPosition = new PathPosition(zombiePosition, orderedPath);
        newZombie = new Zombie(zombiePathPosition);
        testWorld.addEnemy(newZombie);
    }
}
