package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * represents a non-moving entity
 * unlike the moving entities, this can be placed anywhere on the game map
 */
public abstract class StaticEntity extends Entity {
    /**
     * x and y coordinates represented by IntegerProperty, so ChangeListeners can be added
     */
    private IntegerProperty x, y;

    public StaticEntity(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super();
        this.x = x;
        this.y = y;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public void setX(int x) {
        SimpleIntegerProperty xCoordinate = new SimpleIntegerProperty(x);
        this.x = xCoordinate;
    }

    public void setY(int y) {
        SimpleIntegerProperty yCoordinate = new SimpleIntegerProperty(y);
        this.y = yCoordinate;
    }
}
