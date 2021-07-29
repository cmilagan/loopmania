package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.Doggie;

public class DoggieTest {
    private int slugPosition = 1;
    private int characterPosition = 0;
    private Doggie doggie;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /**
     * Test if the Doggie spawns with an initial health of 20
     */
    @Test
    public void testExpectedHealth() {
        initializeWorld();
        int initialDoggieHealth = 20;
        assertEquals(initialDoggieHealth, doggie.getHealth());
    }

    /**
     * Test if the Doggie has 0 damage
     */
    @Test
    void testDoggieDamage() {
        initializeWorld();
        int initialDamage = 0;
        assertEquals(initialDamage, doggie.getDamage());
    }

    /**
     * Test if the Doggie spawns with a battle radius of 1 (same as the Slug)
     */
    @Test
    void testDoggieBattleRadius() {
        initializeWorld();
        int initialBattleRadius = 1;
        assertEquals(initialBattleRadius, doggie.getBattleRadius());
    }

    /**
     * Test if the Doggie spawns with a support radius of 1 (same as the Slug)
     */
    @Test
    void testDoggieSupportRadius() {
        initializeWorld();
        int initialSupportRadius = 1;
        assertEquals(initialSupportRadius, doggie.getSupportRadius());
    }

    /**
     * Test if the Vampire gives 100 XP on defeat
     */
    @Test
    void testDoggieXP() {
        initializeWorld();
        int currentXP = newCharacter.getXP();
        int expectedXP = currentXP + 100;

        testWorld.runBattles();
        assertEquals(expectedXP, newCharacter.getXP());
    }

    /**
     * Test if the Doggie gives 1 DogeCoin on Defeat
     */
    @Test
    void testDoggieCoin() {
        initializeWorld();
        assertEquals(0, newCharacter.getDoggieCoin());

        testWorld.runBattles();

        assertEquals(1, newCharacter.getDoggieCoin());
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
        PathPosition doggiePathPosition = new PathPosition(slugPosition, orderedPath);
        doggie = new Doggie(doggiePathPosition);
        testWorld.addEnemy(doggie);
    }
}
