package unsw.loopmania.buildings;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

public class Building extends StaticEntity {
    // Parent Building Class Type
    private int expiry;

    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
            super(x, y);
    }

    /**
     * Gets the expiry of the current building
     * @return int
     */
    public int getExpiry() {
        return this.expiry;
    }

    /**
     * Update the building expiry 
     * @param newExpiry
     */
    public void setExpiry(int newExpiry) {
        this.expiry = newExpiry;
    }
    
}
