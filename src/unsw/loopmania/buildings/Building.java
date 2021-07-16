package unsw.loopmania.buildings;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

public class Building extends StaticEntity {
    // Parent Building Class Type
    private int expiry;
    private int id;
    
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y, int id) {
        super(x, y);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int num) {
        this.id = num;
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

    /**
     * determine if the placement of the building type is valid
     * @param x
     * @param y
     * @return boolean
     */
    
}
