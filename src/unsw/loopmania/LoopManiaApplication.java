package unsw.loopmania;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the main application
 * run main method from this class
 */
public class LoopManiaApplication extends Application {
    /**
     * the controller for the game. Stored as a field so can terminate it when click exit button
     */
    private LoopManiaWorldController mainController;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // set title on top of window bar
        primaryStage.setTitle("Loop Mania");

        // prevent human player resizing game window (since otherwise would see white space)
        // alternatively, you could allow rescaling of the game (you'd have to program resizing of the JavaFX nodes)
        primaryStage.setResizable(false);

        // load game over screen
        GameOverController GameOverController = new GameOverController();
        FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("GameOverView.fxml"));
        gameOverLoader.setController(GameOverController);
        Parent gameOverRoot = gameOverLoader.load();

        // load the main game
        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader("world_with_twists_and_turns.json");
        mainController = loopManiaLoader.loadController();
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("LoopManiaView.fxml"));
        gameLoader.setController(mainController);
        Parent gameRoot = gameLoader.load();

        // load the main menu
        MainMenuController mainMenuController = new MainMenuController();
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        menuLoader.setController(mainMenuController);
        Parent mainMenuRoot = menuLoader.load();

        // load the new game menu
        GameMenuController gameMenuController = new GameMenuController(mainController.getWorld());
        FXMLLoader newGameMenuLoader = new FXMLLoader(getClass().getResource("NewGameMenuView.fxml"));
        newGameMenuLoader.setController(gameMenuController);
        Parent gameMenuRoot = newGameMenuLoader.load();

        // load the credits
        CreditsController creditsController = new CreditsController();
        FXMLLoader creditsLoader = new FXMLLoader(getClass().getResource("CreditsView.fxml"));
        creditsLoader.setController(creditsController);
        Parent creditsRoot = creditsLoader.load();

        // load the win screen
        WinScreenController winController = new WinScreenController();
        FXMLLoader winLoader = new FXMLLoader(getClass().getResource("WinScreenView.fxml"));
        winLoader.setController(winController);
        Parent winRoot = winLoader.load();
        
        // load the two screens of item shop
        ShopMenuControllerOne shopMenuControllerOne = new ShopMenuControllerOne(mainController.getWorld(), mainController);
        FXMLLoader shopLoaderOne = new FXMLLoader(getClass().getResource("ShopMenuViewOne.fxml"));
        shopLoaderOne.setController(shopMenuControllerOne);
        Parent shopMenuRootOne = shopLoaderOne.load();

        ShopMenuControllerTwo shopMenuControllerTwo = new ShopMenuControllerTwo(mainController.getWorld(), mainController);
        FXMLLoader shopLoaderTwo = new FXMLLoader(getClass().getResource("ShopMenuViewTwo.fxml"));
        shopLoaderTwo.setController(shopMenuControllerTwo);
        Parent shopMenuRootTwo = shopLoaderTwo.load();

        // create new scene with the main menu (so we start with the main menu)
        Scene scene = new Scene(mainMenuRoot);
        
        // set functions which are activated when button click to switch menu is pressed
        // e.g. from main menu to start the game, or from the game to return to main menu
        mainController.setMainMenuSwitcher(() -> {switchToRoot(scene, mainMenuRoot, primaryStage);});
        mainController.setMenuSwitcher(() -> {switchToRoot(scene, gameMenuRoot, primaryStage);});
        mainController.setWinSwitcher(() -> {switchToRoot(scene, winRoot, primaryStage);});
        
        mainMenuController.setNewGameSwitcher(() -> {
            switchToRoot(scene, gameMenuRoot, primaryStage);
        });

        // Load game button
        mainController.setShopMenuSwitcher(() -> {switchToRoot(scene, shopMenuRootOne, primaryStage);});
        mainController.setGameOverSwitcher(() -> {switchToRoot(scene, gameOverRoot, primaryStage);});
        mainMenuController.setGameSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });

        // Credits button
        mainMenuController.setCreditsSwitcher(() -> {
            switchToRoot(scene, creditsRoot, primaryStage);
        });

        // Back button for credits
        creditsController.setBackMenuSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // Return to main menu for win screen
        winController.setMainMenuSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
            stop();
        });

        // when exit button is pressed, the screen shown switches to map
        shopMenuControllerOne.setGameSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });

        // when special items button is pressed, the screen switches to shop screen two
        shopMenuControllerOne.setShopScreenTwo(() -> {
            switchToRoot(scene, shopMenuRootTwo, primaryStage);
        });
        // when normal items button is pressed, the screen switches to shop screen one
        shopMenuControllerTwo.setShopScreenOne(() -> {
            switchToRoot(scene, shopMenuRootOne, primaryStage);
        });
        
        // when main menu button is pressed, screen switches to main menu
        GameOverController.setGameSwitcher(()-> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
            stop();
        });
        
        gameMenuController.setMenuSwitcher(() -> {
            switchToRoot(scene, gameRoot, primaryStage);
            mainController.startTimer();
        });

        // The back button in the new game menu screen
        gameMenuController.setBackMenuSwitcher(() -> {
            switchToRoot(scene, mainMenuRoot, primaryStage);
        });

        // deploy the main onto the stage
        gameRoot.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        // wrap up activities when exit program
        mainController.terminate();
    }

    /**
     * switch to a different Root
     */
    private void switchToRoot(Scene scene, Parent root, Stage stage) {
        scene.setRoot(root);
        root.requestFocus();
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
