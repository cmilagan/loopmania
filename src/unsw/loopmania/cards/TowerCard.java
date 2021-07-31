package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;

/**
 * represents a tower card in the backend game world
 */
public class TowerCard extends Card {
    // TODO = add more types of card
    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
