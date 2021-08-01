// package test;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.ArrayList;
// import java.util.List;

// import org.javatuples.Pair;
// import org.junit.jupiter.api.Test;

// import unsw.loopmania.Character;
// import unsw.loopmania.LoopManiaWorld;
// import unsw.loopmania.PathPosition;
// import unsw.loopmania.npcs.ElanMuske;
// import unsw.loopmania.npcs.Zombie;


// public class ElanMuskeTest {
//     private int elanPosition = 1;
//     private int characterPosition = 0;
//     private ElanMuske elan;
//     private Character newCharacter;
//     private LoopManiaWorld testWorld;
//     private List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();

//     /**
//      * Test if the Elan spawns with an initial health of 40
//      */
//     @Test
//     public void testExpectedHealth() {
//         initializeWorld();
//         addElan();
//         int initialElanHealth = 40;
//         assertEquals(initialElanHealth, elan.getHealth());
//     }

//     /**
//      * Test if the Elan deals 25 damage
//      */
//     @Test
//     public void testElanDamage() {
//         initializeWorld();
//         addElan();
//         int initialDamage = 25;
//         assertEquals(initialDamage, elan.getDamage());
//     }

//     /**
//      * Test if the Elan spawns with a battle radius of 1 (same as the Slug)
//      */
//     @Test
//     public void testElanBattleRadius() {
//         initializeWorld();
//         addElan();
//         int initialBattleRadius = 1;
//         assertEquals(initialBattleRadius, elan.getBattleRadius());
//     }

//     /**
//      * Test if the Elan spawns with a support radius of 1 (same as the Slug)
//      */
//     @Test
//     public void testElanSupportRadius() {
//         initializeWorld();
//         addElan();
//         int initialSupportRadius = 1;
//         assertEquals(initialSupportRadius, elan.getSupportRadius());
//     }

//     /**
//      * Test if the Elan gives 500 XP on defeat
//      */
//     @Test
//     public void testElanXP() {
//         initializeWorld();
//         addCharacter();
//         addElan();
//         elan.setHealth(1);
//         int currentXP = newCharacter.getXP();
//         int expectedXP = currentXP + 500;

//         testWorld.runBattles();
//         assertEquals(expectedXP, newCharacter.getXP());
//     }

//     /**
//      * Test if Elan Muske heals other enemies in his support radius
//      */
//     @Test
//     public void testCheckEnemiesHealed() {
//         initializeWorld();
//         addCharacter();
//         addElan();

//         // spawn Zombie with reduced health
//         PathPosition zombiePathPosition = new PathPosition(elanPosition, orderedPath);
//         Zombie zombie = new Zombie(zombiePathPosition);
//         zombie.setHealth(5);
//         testWorld.addEnemy(zombie);

//         assertEquals(5, zombie.getHealth());
        
//         testWorld.runTickMoves();

//         assertEquals(10, zombie.getHealth());
//     }

//     /**
//      * Test if the value of Doggie coin is 100 in the beginning
//      */
//     @Test
//     public void testDoggieCoinInitial() {
//         initializeWorld();
//         assertEquals(100, testWorld.getDoggieCoinPrice());
//     }

//     /**
//      * Test if the value of Doggie coin varies between 100 and 500 when Elan isn't
//      * around
//      */
//     public void testDoggieCoinPriceNoElan() {
//         initializeWorld();
//         int min = 100;
//         int max = 500;

//         for (int i = 0; i < 50; i++) {
//             assertTrue(testWorld.getDoggieCoinPrice() >= min && testWorld.getDoggieCoinPrice() <= max);
//         }
//     }
    
//     /**
//      * Test to check if the value of doggieCoin varies between 3000 and 10000
//      * when Elan has spawned.
//      */
//     public void testDoggieCoinPriceElan() {
//         initializeWorld();
//         addElan();
//         int min = 3000;
//         int max = 10000;

//         for (int i = 0; i < 50; i++) {
//             assertTrue(testWorld.getDoggieCoinPrice() >= min && testWorld.getDoggieCoinPrice() <= max);
//         }
//     }

//     /**
//      * Test to check if the price of doggieCoin varies from 0 and 10 for 5 rounds
//      * once Elan is defeated.
//      */
//     public void testDoggieCoinPriceElanDefeat() {
//         initializeWorld();
//         addCharacter();     
//         int min = 0;
//         int max = 10;

//         PathPosition elanPathPosition = new PathPosition(elanPosition, orderedPath);
//         ElanMuske weakElan = new ElanMuske(elanPathPosition);
//         weakElan.setHealth(5);
//         testWorld.addEnemy(weakElan);

//         testWorld.runBattles();

//         // decreased price
//         for (int i = 0; i < 5; i++) {
//             testWorld.runTickMoves();
//             assertTrue(testWorld.getDoggieCoinPrice() >= min && testWorld.getDoggieCoinPrice() <= max);
//         }
//     }

//     /**
//      * Test to check if Elan is defeated by a character wielding Anduril, Flame of the West, which does 20 damage
//      * Given that Elan has a health of 40 and damage of 25,
//      *      The character with Anduril has a health of 100 and a damage of 20, and
//      *      Elan moves first,
//      * The battle should end with the Character having a health of 50 remaining
//      */
//     @Test
//     public void testElanDamageAnduril() {
//         initializeWorld();
//         addCharacter();
//         addElan();

//         Anduril anduril = new Anduril(new SimpleIntegerProperty(), new SimpleIntegerProperty());
//         newCharacter.setWeapon(anduril);

//         testWorld.runBattles();

//         assertEquals(50, newCharacter.getHealth());
//     }

//     /**
//      * Setup template world
//      */
//     public void initializeWorld() {
//         int LOOP_SIZE = 3;

//         // setting world path
//         orderedPath.add(Pair.with(0, 0));
//         orderedPath.add(Pair.with(1, 0));
//         orderedPath.add(Pair.with(2, 0));
//         orderedPath.add(Pair.with(2, 1));
//         orderedPath.add(Pair.with(2, 2));
//         orderedPath.add(Pair.with(1, 2));
//         orderedPath.add(Pair.with(0, 2));
//         orderedPath.add(Pair.with(0, 1));
//         testWorld = new LoopManiaWorld(LOOP_SIZE, LOOP_SIZE, orderedPath);
//     }

//     public void addCharacter() {
//         // initializing and adding the character
//         PathPosition characterPathPosition = new PathPosition(characterPosition, orderedPath);
//         newCharacter = new Character(characterPathPosition);
//         testWorld.setCharacter(newCharacter);
//     }

//     public void addElan() {
//         // initializing Elan
//         PathPosition elanPathPosition = new PathPosition(elanPosition, orderedPath);
//         elan = new ElanMuske(elanPathPosition);
//         testWorld.addEnemy(elan);
//     }
// } 
