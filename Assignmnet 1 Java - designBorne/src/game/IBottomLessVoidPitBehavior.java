package game;

import edu.monash.fit2099.engine.positions.Location;
import game.ground.VoidPit;

public interface IBottomLessVoidPitBehavior {

    public default boolean isLocationBottomLessPit(Location currentLocation)
    {
        //check if present location is a pit
        return currentLocation.getGround().getDisplayChar() == VoidPit.PIT_DISPLAY;

    }
}
