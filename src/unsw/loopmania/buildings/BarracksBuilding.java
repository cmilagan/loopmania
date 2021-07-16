package unsw.loopmania.buildings;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class BarracksBuilding extends Building {
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y, int id) {
        super(x, y, id);
    }
}
