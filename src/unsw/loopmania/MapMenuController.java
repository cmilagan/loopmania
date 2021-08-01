package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MapMenuController {
    /**
     * Boolean to determine what map the game displaying
     */
    private boolean originalMap;
    private boolean circleMap;
    private boolean complexMap;
    private boolean snakeMap;

    private MenuSwitcher newGameMenuSwitcher;
    private MenuSwitcher backButton;

    public MapMenuController() {
        this.originalMap = false;
        this.circleMap = false;
        this.complexMap = false;
        this.snakeMap = false;
    }

    @FXML
    private Text statusField;

    // Button that selects the map
    @FXML
    private void selectCircle() {
        statusField.setText("This map is just a circle");
        circleMap = true;
        complexMap = false;
        snakeMap = false;
        originalMap = false;
    }

    @FXML
    private void selectSnake() {
        statusField.setText("This map snakes around");
        circleMap = false;
        complexMap = false;
        snakeMap = true;
        originalMap = false;
    }

    @FXML
    private void selectOriginal() {
        statusField.setText("This map is the original");
        circleMap = false;
        complexMap = false;
        snakeMap = false;
        originalMap = true;
    }

    @FXML
    private void selectComplex() {
        statusField.setText("This map has a lot of twists and turns");
        circleMap = false;
        complexMap = true;
        snakeMap = false;
        originalMap = false;
    }

    public void setMenuSwitcher(MenuSwitcher gameSwitcher){
        this.newGameMenuSwitcher = gameSwitcher;
    }

    public void setBackMenuSwitcher(MenuSwitcher gameSwitcher){
        this.backButton = gameSwitcher;
    }

    @FXML
    private void backButton() {
        backButton.switchMenu();
    }

    @FXML
    private void switchToGame() throws IOException {
        newGameMenuSwitcher.switchMenu();
    }

    /**
     * Getters for the different maps
     */
    public boolean getOriginalMap() {
        return this.originalMap;
    }

    public boolean getCircleMap() {
        return this.circleMap;
    }

    public boolean getComplexMap() {
        return this.complexMap;
    }

    public boolean getSnakeMap() {
        return this.snakeMap;
    }
}
