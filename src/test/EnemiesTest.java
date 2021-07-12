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
    private Character newCharacter;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    private LoopManiaWorld testWorld = initializeWorld(orderedPath);

    @Test
    void testCharacterMovement() {
        // test Character Movement
        int posX = newCharacter.getX();
        int posY = newCharacter.getY(); 
        assertEquals(Pair.with(0, 0), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        // character should move down
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(Pair.with(1, 0), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        // character should keep moving down
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(Pair.with(2, 0), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        // character should change direction
        posX = newCharacter.getX();
        posY = newCharacter.getY(); 
        assertEquals(Pair.with(2, 1), Pair.with(posX, posY));
    }

    @Test
    void testSlugMovement() {
        // test Slug Movement
        int slugPosition = 2;
        PathPosition slugPathPosition = new PathPosition(slugPosition, orderedPath);
        Slug newSlug = new Slug(slugPathPosition);
        testWorld.addEntity(newSlug);
        // tick the world
        testWorld.runTickMoves();
        // should move up first
        int posX = newSlug.getX();
        int posY = newSlug.getY(); 
        assertEquals(Pair.with(1, 0), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        // should return back to spawn position
        posX = newSlug.getX();
        posY = newSlug.getY(); 
        assertEquals(Pair.with(2, 0), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        // should move down now
        posX = newSlug.getX();
        posY = newSlug.getY(); 
        assertEquals(Pair.with(2, 1), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        // should move back to spawn position
        posX = newSlug.getX();
        posY = newSlug.getY(); 
        assertEquals(Pair.with(2, 0), Pair.with(posX, posY));
    }

    @Test
    void testZombieMovement() {
        // test Zombie movement
        int zombiePosition = 4;
        PathPosition zombiePathPosition = new PathPosition(zombiePosition, orderedPath);
        Zombie newZombie = new Zombie(zombiePathPosition);
        testWorld.addEntity(newZombie);
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should move up first
        int posX = newZombie.getX();
        int posY = newZombie.getY(); 
        assertEquals(Pair.with(0, 2), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should return back to spawn position
        posX = newZombie.getX();
        posY = newZombie.getY(); 
        assertEquals(Pair.with(2, 2), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should move down now
        posX = newZombie.getX();
        posY = newZombie.getY(); 
        assertEquals(Pair.with(2, 0), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should move back to spawn position
        posX = newZombie.getX();
        posY = newZombie.getY(); 
        assertEquals(Pair.with(2, 2), Pair.with(posX, posY));
    }

    @Test
    void testVampireMovement() {
        // test Vampire movement
        int vampirePosition = 6;
        PathPosition vampirePathPosition = new PathPosition(vampirePosition, orderedPath);
        Vampire newVampire = new Vampire(vampirePathPosition);
        testWorld.addEntity(newVampire);
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should move up first
        int posX = newVampire.getX();
        int posY = newVampire.getY(); 
        assertEquals(Pair.with(1, 0), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should return back to spawn position
        posX = newVampire.getX();
        posY = newVampire.getY(); 
        assertEquals(Pair.with(0, 2), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should move down now
        posX = newVampire.getX();
        posY = newVampire.getY(); 
        assertEquals(Pair.with(2, 1), Pair.with(posX, posY));
        // tick the world
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        testWorld.runTickMoves();
        // should move back to spawn position
        posX = newVampire.getX();
        posY = newVampire.getY(); 
        assertEquals(Pair.with(0, 2), Pair.with(posX, posY));
    }

    // Slug Test
    @Test
    void testSlug() {

    }

    // Zombie Test
    @Test
    void testZombie() {

    }

    // Vampire Test
    @Test
    void testVampire() {

    }

    // Allied Soldiers Test
    @Test
    void testAlliedSoldiers() {

    }

    // setup template world
    public LoopManiaWorld initializeWorld(List<Pair<Integer, Integer>> orderedPath) {
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
        LoopManiaWorld newWorld = new LoopManiaWorld(LOOP_SIZE, LOOP_SIZE, orderedPath);
        // initializing and adding the character
        PathPosition characterPathPosition = new PathPosition(characterPosition, orderedPath);
        newCharacter = new Character(characterPathPosition);
        testWorld.setCharacter(newCharacter);
        // returning template world
        return newWorld;
    }
}
