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
import unsw.loopmania.items.Staff;
import unsw.loopmania.items.Stake;
import unsw.loopmania.items.Sword;
import unsw.loopmania.npcs.BasicEnemy;
import unsw.loopmania.npcs.Slug;

class BattleTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    /**
     *  Fighting Basic Enemy Test
     *  Testing that enemies die properly
     */
    void testBattleEncounter() {
        initializeWorld();
        // Adding Enemy to World
        PathPosition enemyPathPosition = new PathPosition(0, orderedPath);
        Slug enemy = new Slug(enemyPathPosition);
        testWorld.addEnemy(enemy);
        // Fight Enemy
        List<BasicEnemy> defeatedEnemies = testWorld.runBattles();
        assertEquals(defeatedEnemies.size(), 1);
        // Check results
    }

    @Test
    /**
     *  Fighting Multiple Slugs Test
     *  Testing that only enemies inside proximity will fight
     */
    void testBattleEncounterWithMultiple1() {
        initializeWorld();
        // Adding Enemy to World
        PathPosition enemyPathPosition1 = new PathPosition(0, orderedPath);
        PathPosition enemyPathPosition2 = new PathPosition(1, orderedPath);
        Slug enemy1 = new Slug(enemyPathPosition1);
        Slug enemy2 = new Slug(enemyPathPosition2);
        testWorld.addEnemy(enemy1);
        testWorld.addEnemy(enemy2);
        // Fight Enemies
        List<BasicEnemy> defeatedEnemies = testWorld.runBattles();
        assertEquals(defeatedEnemies.size(), 2);
        // Check results
    }

    @Test
    /**
     *  Fighting one slug, with other outside of proximity
     *  Testing that only enemies inside proximity will fight
     */
    void testBattleEncounterWithMultiple2() {
        initializeWorld();
        // Adding Enemy to World
        PathPosition enemyPathPosition1 = new PathPosition(0, orderedPath);
        PathPosition enemyPathPosition2 = new PathPosition(3, orderedPath);
        Slug enemy1 = new Slug(enemyPathPosition1);
        Slug enemy2 = new Slug(enemyPathPosition2);
        testWorld.addEnemy(enemy1);
        testWorld.addEnemy(enemy2);
        // Fight Enemies
        List<BasicEnemy> defeatedEnemies = testWorld.runBattles();
        assertEquals(defeatedEnemies.size(), 1);
        // Check results
    }


    @Test
    /**
     *  Fighting one slug, with other outside of proximity
     *  Testing that only enemies inside proximity will fight
     */
    void testBattleEncounterWithCharacterDeath() {
        initializeWorld();
        // Setting Character health to 1
        newCharacter.setHealth(1);
        // Adding Enemy to World
        PathPosition enemyPathPosition1 = new PathPosition(0, orderedPath);
        Slug enemy1 = new Slug(enemyPathPosition1);
        testWorld.addEnemy(enemy1);
        // Fight Enemies
        List<BasicEnemy> defeatedEnemies = testWorld.runBattles();
        assertEquals(defeatedEnemies.size(), 0);
        assertEquals(newCharacter.getHealth(), 0);
        // Check results
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
    }
}