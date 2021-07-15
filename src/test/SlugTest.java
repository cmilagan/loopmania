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
    
    // check that the slug has expected health
    @Test
    void slugExpectedHealth() {
        assertEquals(3, newSlug.getHealth());
    }

    // check that the slug has expected damage
    @Test
    void slugExpectedDamage() {
        assertEquals(5, newSlug.getDamage());
    }

    // check that the slug has expected battle radius
    @Test
    void slugExpectedBattleRadius() {
        assertEquals(1, newSlug.getBattleRadius());
    }

    // check that the slug has expected support radius
    @Test
    void slugExpectedSupportRadius() {
        assertEquals(1, newSlug.getSupportRadius());
    }

    // check that the slug deals damage to the MC
    @Test
    void slugDealsDamage() {
        
    }

    // setup template world
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
