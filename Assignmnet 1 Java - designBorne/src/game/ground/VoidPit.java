package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing a void pit in the game.
 * Actors can enter and stand on the void pit without any restrictions.
 */
public class VoidPit extends Ground {

    /** The character used to display the void pit on the map. */
    public final static char PIT_DISPLAY = '+';

    /**
     * Constructor for creating a void pit.
     */
    public VoidPit() {
        super(PIT_DISPLAY);
    }

    //already true in super
    /* public boolean canActorEnter(Actor actor) {
        return true;
        }*/
}
