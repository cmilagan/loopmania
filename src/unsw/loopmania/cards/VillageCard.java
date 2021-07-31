package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;

/**
 * represents a village card in the backend game world
 */
public class VillageCard extends Card {
    // TODO = add more types of card
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}
