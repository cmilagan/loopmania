package unsw.loopmania.buildings;

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

    public int getExpiry() {
        return this.expiry;
    }
    
}
