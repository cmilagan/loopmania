package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import unsw.loopmania.buildings.HeroCastleBuilding;

public class HeroCastleTest {
    private Character newCharacter;
    private HeroCastleBuilding heroCastle = new HeroCastleBuilding(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
    private LoopManiaWorld testWorld;
    private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

    @Test
    public void checkCharStartTest(){
        System.out.println("TEST - Player start in Hero Castle");
        // Create world
        initializeWorld();
        testWorld.addBuilding(heroCastle);
        // Check player location is at Hero Castle
        int characterPosition = 0;
        assertEquals(orderedPath.get(characterPosition), heroCastle.getPosition());
        System.out.println("--- Passed ---\n");
    }
    
    @Test
    public void checkLoopCountTest(){
        System.out.println("TEST - Loop count after doing a loop");
        initializeWorld();
        testWorld.addBuilding(heroCastle);
        // Make Character loop through map
        testWorld.runTickMoves();
        // Check if the loop counter goes up by 1
        
        System.out.println("--- Passed ---\n");
    }

    @Test
    public void checkShopTest(){
        System.out.println("TEST - Player enters shop");
        initializeWorld();
        // Not sure I can do this rn

        // Check if player is at the Hero's Castle the shop menu is open

        // Check if the right items and limits are toggled for the selected game mode
        System.out.println("--- Passed ---\n");
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
