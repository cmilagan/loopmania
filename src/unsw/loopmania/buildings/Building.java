package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

public class Building extends StaticEntity {
    // Parent Building Class Type
    
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
