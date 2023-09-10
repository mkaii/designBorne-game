package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

public class VoidPit extends Ground {

    public final static char PIT_DISPLAY = '+';

    public VoidPit() {
        super(PIT_DISPLAY);
    }

    //already true in super
    /* public boolean canActorEnter(Actor actor) {
        return true;
        }*/
}
