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
    private int characterPosition = 0;
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    /** TODO: add assumption to assumptions file
     * Checks Made Assumption:
     * When enemies are spawned, they will move x blocks up then return to spawning position 
     * then move x blocks down, then return back to the spawning position and so on. 
     * At each tick, the enemy will move 1 step towards their travelling direction. 
     * The value of x varies according to the enemy type:
     * Slug: x = 1
     * Zombie: x = 2
     * Vampire: x = 3
     * 
     * Note: multiple enemies can momentarily exist on one tile
     * when their paths are crossing each other
     */
    @Test
    void testCharacterMovement() {
        initializeWorld();

        int posX = newCharacter.getX();
        int posY = newCharacter.getY(); 
        assertEquals(orderedPath.get(characterPosition), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // character should move clockwise
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(Pair.with(0, 1), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // character should keep moving clockwise
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(Pair.with(0, 2), Pair.with(posX, posY));

        // tick the world
        testWorld.runTickMoves();

        // character should change direction
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(Pair.with(1, 2), Pair.with(posX, posY));
    }

    @Test
    void testSlugMovement() {
        initializeWorld();

        int slugPosition = 2;

        // initializing slug
        PathPosition slugPathPosition = new PathPosition(slugPosition, orderedPath);
        Slug newSlug = new Slug(slugPathPosition);
        testWorld.addEntity(newSlug);

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

        // check edge case at P(0, 0)

        // initializing another Slug
        PathPosition anotherSlugPath = new PathPosition(0, orderedPath);
        Slug anotherSlug = new Slug(anotherSlugPath);
        testWorld.addEntity(anotherSlug);

        // tick the world
        testWorld.runTickMoves();

        // position should wrap around the array when moving up
        posX = anotherSlug.getX();
        posY = anotherSlug.getY(); 
        assertEquals(Pair.with(0, 1), Pair.with(posX, posY));
    }

    @Test
    void testZombieMovement() {
        initializeWorld();

        int zombiePosition = 4;

        // initialize zombie
        PathPosition zombiePathPosition = new PathPosition(zombiePosition, orderedPath);
        Zombie newZombie = new Zombie(zombiePathPosition);
        testWorld.addEntity(newZombie);

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

        // check edge case at P(0, 0)

        // initializing another Zombie
        PathPosition newPathPosition = new PathPosition(0, orderedPath);
        Zombie anotherZombie = new Zombie(newPathPosition);
        testWorld.addEntity(anotherZombie);

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // position should wrap around the array when moving up
        posX = anotherZombie.getX();
        posY = anotherZombie.getY(); 
        assertEquals(Pair.with(0, 2), Pair.with(posX, posY));
    }

    @Test
    void testVampireMovement() {
        initializeWorld();

        int vampirePosition = 6;

        // initialize vampire
        PathPosition vampirePathPosition = new PathPosition(vampirePosition, orderedPath);
        Vampire newVampire = new Vampire(vampirePathPosition);
        testWorld.addEntity(newVampire);

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

        // check edge case at P(0, 0)

        // initializing another Vampire
        PathPosition newPathPosition = new PathPosition(0, orderedPath);
        Vampire anotherVampire = new Vampire(newPathPosition);
        testWorld.addEntity(anotherVampire);

        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // position should wrap around the array when moving up
        posX = anotherVampire.getX();
        posY = anotherVampire.getY(); 
        assertEquals(Pair.with(1, 2), Pair.with(posX, posY));
    }

    /**
     * When some enemy is within the support radius of Slug,
     * check if Slug helps
     * 
     * TODO: Add assumption to assumptions file
     * Checks Assumption:
     * 
     * When some enemy X is within the support radius of another
     * enemy Y, the enemy Y teleports to X's position + 1 and the
     * battle state should calculate the the total damage dealt on MC
     * 
     * Note: such teleportation only occurs when the enemy is in battle
     */
    @Test
    void testSlugSupportRadius() {
        initializeWorld();

        int slugPosition = 2;
        int anotherSlugPosition = slugPosition + 1;

        // initializing slug
        PathPosition slugPathPosition = new PathPosition(slugPosition, orderedPath);
        Slug newSlug = new Slug(slugPathPosition);
        testWorld.addEntity(newSlug);

        // initializing another slug which is next to the original slug
        PathPosition anotherSlugPath = new PathPosition(anotherSlugPosition, orderedPath);
        Slug slugTwo = new Slug(anotherSlugPath);
        testWorld.addEntity(slugTwo);

        // tick world
        testWorld.runTickMoves();

        // since a Slug has a support radius 1, the Slugs should just keep moving
        // should move up first
        int posXOne = newSlug.getX();
        int posYOne = newSlug.getY(); 
        assertEquals(orderedPath.get(slugPosition + moveUp), Pair.with(posXOne, posYOne));

        // should move up first
        int posXTwo = slugTwo.getX();
        int posYTwo = slugTwo.getY(); 
        assertEquals(orderedPath.get(anotherSlugPosition + moveUp), Pair.with(posXTwo, posYTwo));   
    }

    /**
     * When some enemy is within the support radius of Zombie,
     * check if Zombie goes to help (given that battle is initiated)
     */
    @Test
    void testZombieSupportRadius() {
        initializeWorld();

        int mcPosition = 6;
        int slugPosition = 5;
        int zombiePosition = 3;
        int expectedPosition = 4;

        // tick the world to place character in position
        testWorld.runTickMoves();
        testWorld.runTickMoves();

        // ensure that MC is at expected location
        int locX = newCharacter.getX();
        int locY = newCharacter.getY();
        assertEquals(orderedPath.get(mcPosition), Pair.with(locX, locY));

        // initialize an enemy Slug next to the character
        PathPosition slugPathPosition = new PathPosition(slugPosition, orderedPath);
        Slug newSlug = new Slug(slugPathPosition);
        testWorld.addEntity(newSlug);

        /**
         * initialize a Zombie such that its position is within radius 2 of slug
         * 
         * ensure that Zombie is located before Slug so we can check if the teleportation
         * 
         * takes place as expected or else movement can be attributed to the tick() outcome
         */
        PathPosition zombiePathPosition = new PathPosition(zombiePosition, orderedPath);
        Zombie newZombie = new Zombie(zombiePathPosition);
        testWorld.addEntity(newZombie);

        // tick the world
        testWorld.runTickMoves();

        /**
         * check if the new location of Zombie is next to the Slug
         * 
         * note: the Zombie moves next to the Slug because the Slug is 
         * engaged in battle with the main character
         * 
         * note: 'engaged' in battle means MC is within the battle
         * radius of an enemy
         */
        locX = newZombie.getX();
        locY = newZombie.getY();
        assertEquals(orderedPath.get(expectedPosition), Pair.with(locX, locY));
    }

    /**
     * When some enemy is within the support radius of Vampire,
     * check if Vampire goes to help (given that battle is initiated)
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
}
