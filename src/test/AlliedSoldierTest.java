package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;

import unsw.loopmania.PathPosition;
import unsw.loopmania.npcs.AlliedSoldier;
import unsw.loopmania.npcs.Slug;
import unsw.loopmania.npcs.Vampire;

class AlliedSoldierTest {
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    /**
     * Testing Allied Soldier spawns
     */
    void testAlliedSoldierSpawn() {
        initializeWorld();

        int alliedSoldierPosition = 1;
        PathPosition path = new PathPosition(alliedSoldierPosition, orderedPath);
        AlliedSoldier s = new AlliedSoldier(path);

        testWorld.addAlliedSoldier(s);

        assertTrue(testWorld.getAlliedSoldiersNumber() == 1);
    }
    
    @Test
    /**
     * Testing if Allied Soldier has health = 3 on spawn
     */
    void testAlliedHealth() {
        initializeWorld();
        
        int initialHealth = 3;
        int alliedSoldierPosition = 0;
        PathPosition initialPosition = new PathPosition(alliedSoldierPosition, orderedPath);
        
        AlliedSoldier newAlliedSoldier = new AlliedSoldier(initialPosition);
        testWorld.addAlliedSoldier(newAlliedSoldier);

        assertEquals(newAlliedSoldier.getHealth(), initialHealth);
    }

    @Test
    /**
     * Test if 5 is the maximum number of Allied Soldiers allowed
     */
    void testMaxSoldiers() {
        initializeWorld();

        for (int i = 1; i <= 5; i++) {
            PathPosition pos = new PathPosition(i, orderedPath);
            AlliedSoldier soldier = new AlliedSoldier(pos);
            testWorld.addAlliedSoldier(soldier);
        }

        PathPosition pos6 = new PathPosition(3, orderedPath);
        AlliedSoldier soldier6 = new AlliedSoldier(pos6);
        testWorld.addAlliedSoldier(soldier6);

        assertTrue(testWorld.getAlliedSoldiersNumber() == 5);
        
    }

    @Test
    /**
     * Test if an Allied Soldier despawns after battle where it's health = 0
     */
    void testAlliedDespawn() {
        initializeWorld();

        int alliedSoldierPosition = 1;
        PathPosition pos = new PathPosition(alliedSoldierPosition, orderedPath);
        AlliedSoldier s = new AlliedSoldier(pos);

        testWorld.addAlliedSoldier(s);

        assertEquals(1, testWorld.getAlliedSoldiersNumber());

        Vampire v = new Vampire(pos);
        testWorld.addEnemy(v);

        testWorld.runBattles();

        assertEquals(0, testWorld.getAlliedSoldiersNumber());
    }

    @Test
    /**
     * Test if an allied soldier becomes a Zombie when its health = -1 (critical hit from zombie).
     */
    void testAlliedSoldierZombie() {
        initializeWorld();

        // Run battle with only a Slug in world. Record character health.
        int index = 1;
        PathPosition pos = new PathPosition(index, orderedPath);
        
        Slug slug = new Slug(pos);
        testWorld.addEnemy(slug);
        
        testWorld.runBattles();
        
        int healthAfterSlugBattle = newCharacter.getHealth();
        
        /* Reset character health, now run battle with Slug and Allied Soldier.
         * Allied Soldier should turn into zombie and damage main character.
         * Main character health after battle should be less than previous battle.
         */
        newCharacter.setHealth(100);
        assertEquals(100, newCharacter.getHealth());
        
        AlliedSoldier s = new AlliedSoldier(pos);
        s.setHealth(-1);

        testWorld.addAlliedSoldier(s);
        testWorld.addEnemy(slug);

        testWorld.runBattles();
        int healthAfterAlliedTurns = newCharacter.getHealth();

        assertTrue(healthAfterAlliedTurns < healthAfterSlugBattle);
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
