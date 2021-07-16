package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.Slug;

public class SlugTest {
    private int slugPosition = 1;
    private int characterPosition = 0;
    private Slug newSlug;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /**
     * Initialize world state.
     */
    public SlugTest() {
        initializeWorld();
    }
    
    /**
     * Check that the slug has expected health.
     */
    @Test
    public void testExpectedHealth() {
        assertEquals(3, newSlug.getHealth());
    }

    /**
     * Check that the slug has expected damage.
     */
    @Test
    public void testExpectedDamage() {
        assertEquals(5, newSlug.getDamage());
    }

    /**
     * Check that the slug has expected battle radius.
     */
    @Test
    public void testExpectedBattleRadius() {
        assertEquals(1, newSlug.getBattleRadius());
    }

    /**
     * Check that the slug has expected support radius.
     */
    @Test
    public void testExpectedSupportRadius() {
        assertEquals(1, newSlug.getSupportRadius());
    }

    /**
     * When the character engages in battle with a Slug, he should have less
     * health than when he spawned.
     */
    @Test
    public void testSlugDealsDamage() {
        // get initial character health
        int mainCharacterHealth = newCharacter.getHealth();

        // run battle
        testWorld.runBattles();

        // check if health is less by 5
        assertEquals(mainCharacterHealth - newSlug.getDamage(), newCharacter.getHealth());
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
        PathPosition slugPathPosition = new PathPosition(slugPosition, orderedPath);
        newSlug = new Slug(slugPathPosition);
        testWorld.addEnemy(newSlug);
    }
}
