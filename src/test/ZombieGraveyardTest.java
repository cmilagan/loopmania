package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.LoopManiaWorldLoader;
import unsw.loopmania.MovingEntity;
import unsw.loopmania.PathPosition;
import unsw.loopmania.buildings.ZombieGraveyardBuilding;



// https://edstem.org/courses/5957/discussion/522938

public class ZombieGraveyardTest {

    // building properties;
    private int zombiePitExpiry = 5;
    // world related fields
    private Character newCharacter;
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
    
    @Test
    void testZombieSpawn() {
        ZombieGraveyardBuilding newGraveyard = new ZombieGraveyardBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        testWorld.addEntity(newGraveyard);
        for (int i = 0; i < 8; i++) {
            testWorld.runTickMoves();
        }
        // TODO: How do we check an enemy spawns, implementation based~
        assertEquals(1, 2);
    }
    
    @Test
    void testZombieGraveyardExpiry() {
        ZombieGraveyardBuilding newGraveyard = new ZombieGraveyardBuilding(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        testWorld.addEntity(newGraveyard);
        for (int i = 0; i < 8 * zombiePitExpiry; i++) {
            testWorld.runTickMoves();
        }
        assertEquals(newGraveyard, null);
    }
    

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
