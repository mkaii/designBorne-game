package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Player;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
    public Floor() {
        super('_');
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        //only allow for main player
        return actor.getDisplayChar() == Player.DISPLAY_CHAR;
    }
}
