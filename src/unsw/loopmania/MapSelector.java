package unsw.loopmania;

public class MapSelector {
    /**
     * Boolean to determine what map the game displaying
     */
    private boolean originalMap;
    private boolean circleMap;
    private boolean complexMap;
    private boolean snakeMap;

    public MapSelector() {
        originalMap = false;
        circleMap = false;
        complexMap = false;
        snakeMap = false;
    }
    
    /**
     * Getters for the different maps
     */
    public boolean getOriginalMap() {
        return originalMap;
    }

    public boolean getCircleMap() {
        return circleMap;
    }

    public boolean getComplexMap() {
        return complexMap;
    }

    public boolean getSnakeMap() {
        return snakeMap;
    }

    /**
     * Setters for the maps
     */
    public void selectCircle() {
        circleMap = true;
        complexMap = false;
        snakeMap = false;
        originalMap = false;
    }

    public void selectComplex() {
        circleMap = false;
        complexMap = true;
        snakeMap = false;
        originalMap = false;
    }

    public void selectSnake() {
        circleMap = false;
        complexMap = false;
        snakeMap = true;
        originalMap = false;
    }
    
    public void selectOriginal() {
        circleMap = false;
        complexMap = false;
        snakeMap = false;
        originalMap = true;
    }
}
