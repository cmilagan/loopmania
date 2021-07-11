package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;

/**
 * represents a trap card in the backend game world
 */
public class TrapCard extends Card {
    // TODO = add more types of card
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
