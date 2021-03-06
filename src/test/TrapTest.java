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
import unsw.loopmania.buildings.Building;
import unsw.loopmania.buildings.TrapBuilding;
import unsw.loopmania.npcs.BasicEnemy;
import unsw.loopmania.npcs.Zombie;

class TrapTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    /**
     * Testing if Trap Test has 8 damage
     */
    void testTrapPlace() {
        initializeWorld();
        
        int initialDamage = 8;
        TrapBuilding newTrap = new TrapBuilding(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));

        testWorld.addBuilding(newTrap);

        assertEquals(newTrap.getDamage(), initialDamage);
    }

    @Test
    /**
     * Testing how much damage character takes from trap
     */
    void testTrapDamage() {
        initializeWorld();
        int trapDamage = 8;
        int initialCharacterHealth = this.newCharacter.getHealth();
        TrapBuilding newTrap = new TrapBuilding(new SimpleIntegerProperty(1),new SimpleIntegerProperty(0));
        // Triggering Trap
        Pair<Integer, Integer> trapPos = new Pair<Integer, Integer>(1,1);
        List<BasicEnemy> enemies = testWorld.possiblySpawnEnemies();
        for (BasicEnemy e: enemies) {
            testWorld.runTickMoves();
            int eX = e.getX();
            int eY = e.getY();
            Pair<Integer, Integer> enemyPos = new Pair<Integer, Integer>(eX,eY);
            if (enemyPos.equals(trapPos)) {
                // enemy steps on trap
                assertEquals(e.getHealth() - trapDamage, e.getHealth() - newTrap.getDamage());
                assertTrue(e.getHealth() < 0);
                assertTrue(newTrap == null); //
            }
        }
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