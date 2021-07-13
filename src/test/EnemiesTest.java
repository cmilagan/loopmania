package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldLoader;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.Slug;
import unsw.loopmania.npcs.Vampire;
import unsw.loopmania.npcs.Zombie;

class EnemiesTest {
    private int moveUp = -1;
    private int moveDown = 1;
    private int zombieReach = 2;
    private int vampireReach = 3;
    private int slugPosition = 2;
    private int zombiePosition = 4;
    private int vampirePosition = 6;
    private Slug newSlug;
    private Zombie newZombie;
    private Vampire newVampire;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /**
     * Checks Made Assumption:
     * When enemies are spawned, they will move x blocks up then return to spawning position 
     * then move x blocks down, then return back to the spawning position and so on. 
     * At each tick, the enemy will move 1 step towards their travelling direction. 
     * The value of x varies according to the enemy type:
     * Slug: x = 1
     * Zombie: x = 2
     * Vampire: x = 3
     */
    @Test
    void testCharacterMovement() {
        initializeWorld();

        int characterPosition = 0;
        int posX = newCharacter.getX();
        int posY = newCharacter.getY(); 
        assertEquals(orderedPath.get(characterPosition), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // character should move down
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(orderedPath.get(characterPosition + moveDown), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // character should keep moving down
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(orderedPath.get(characterPosition + moveDown * 2), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // character should change direction
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(orderedPath.get(characterPosition + moveDown * 3), Pair.with(posX, posY));
    }

    @Test
    void testSlugMovement() {
        initializeWorld();

        // check if initial location is correct
        int posX = newSlug.getX();
        int posY = newSlug.getY(); 
        assertEquals(orderedPath.get(slugPosition), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // should move up first
        posX = newSlug.getX();
        posY = newSlug.getY(); 
        assertEquals(orderedPath.get(slugPosition + moveUp), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // should return back to spawn position
        posX = newSlug.getX();
        posY = newSlug.getY(); 
        assertEquals(orderedPath.get(slugPosition), Pair.with(posX, posY));
        
        // tick the world
        testWorld.runTickMoves();

        // should move down now
        posX = newSlug.getX();
        posY = newSlug.getY(); 
        assertEquals(orderedPath.get(slugPosition + moveDown), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // should move back to spawn position
        posX = newSlug.getX();
        posY = newSlug.getY(); 
        assertEquals(orderedPath.get(slugPosition), Pair.with(posX, posY));
    }

    @Test
    void testZombieMovement() {
        initializeWorld();

        // check if initial location is correct
        int posX = newZombie.getX();
        int posY = newZombie.getY(); 
        assertEquals(orderedPath.get(zombiePosition), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should move up first
        posX = newZombie.getX();
        posY = newZombie.getY(); 
        assertEquals(orderedPath.get(zombiePosition + moveUp * zombieReach), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should return back to spawn position
        posX = newZombie.getX();
        posY = newZombie.getY(); 
        assertEquals(orderedPath.get(zombiePosition), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should move down now
        posX = newZombie.getX();
        posY = newZombie.getY(); 
        assertEquals(orderedPath.get(zombiePosition + moveDown * zombieReach), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should move back to spawn position
        posX = newZombie.getX();
        posY = newZombie.getY(); 
        assertEquals(orderedPath.get(zombiePosition), Pair.with(posX, posY));
    }

    @Test
    void testVampireMovement() {
        initializeWorld();

        // check if initial location is correct
        int posX = newVampire.getX();
        int posY = newVampire.getY(); 
        assertEquals(orderedPath.get(vampirePosition), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should move up first
        posX = newVampire.getX();
        posY = newVampire.getY(); 
        assertEquals(orderedPath.get(vampirePosition + moveUp * vampireReach), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should return back to spawn position
        posX = newVampire.getX();
        posY = newVampire.getY(); 
        assertEquals(orderedPath.get(vampirePosition), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should move down now
        posX = newVampire.getX();
        posY = newVampire.getY(); 
        assertEquals(orderedPath.get(vampirePosition + moveDown * vampireReach), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // should move back to spawn position
        posX = newVampire.getX();
        posY = newVampire.getY(); 
        assertEquals(orderedPath.get(vampirePosition), Pair.with(posX, posY));
    }

    /**
     * When some enemy is within the support radius of Slug,
     * check if Slug goes to help
     */
    @Test
    void testSlugSupportRadius() {
        initializeWorld();
    }

    /**
     * When some enemy is within the support radius of Zombie,
     * check if Zombie goes to help
     */
    @Test
    void testZombieSupportRadius() {
        initializeWorld();
    }

    /**
     * When some enemy is within the support radius of Vampire,
     * check if Vampire goes to help
     */
    @Test
    void testVampireSupportRadius() {
        initializeWorld();
    }

    
    @Test
    void testSlug() {

    }

    @Test
    void testZombie() {

    }

    @Test
    void testVampire() {

    }

    @Test
    void testAlliedSoldiers() {

    }

    // setup template world
    public void initializeWorld() {
        int LOOP_SIZE = 3;
        int characterPosition = 0;
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
        Slug newSlug = new Slug(slugPathPosition);
        testWorld.addEntity(newSlug);
        
        // initialize zombie
        PathPosition zombiePathPosition = new PathPosition(zombiePosition, orderedPath);
        Zombie newZombie = new Zombie(zombiePathPosition);
        testWorld.addEntity(newZombie);

        // initialize vampire
        PathPosition vampirePathPosition = new PathPosition(vampirePosition, orderedPath);
        Vampire newVampire = new Vampire(vampirePathPosition);
        testWorld.addEntity(newVampire);
    }
}
