package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

public class Building extends StaticEntity {
    // Parent Building Class Type
    private int expiry;
    
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public int getExpiry() {
        return this.expiry;
    }
    
}
