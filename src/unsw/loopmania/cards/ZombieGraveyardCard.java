package unsw.loopmania.cards;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Card;

/**
 * represents a zombie graveyard card in the backend game world
 */
public class ZombieGraveyardCard extends Card {
    // TODO = add more types of card
    public ZombieGraveyardCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    
}

