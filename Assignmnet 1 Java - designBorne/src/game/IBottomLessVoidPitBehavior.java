package game;

import edu.monash.fit2099.engine.positions.Location;
import game.ground.VoidPit;

/**
 * An interface representing behavior related to bottomless void pits in the game.
 */
public interface IBottomLessVoidPitBehavior {

    /**
     * Checks if the provided location contains a bottomless void pit.
     *
     * @param currentLocation The location to check.
     * @return {@code true} if the location contains a bottomless void pit, {@code false} otherwise.
     */
    default boolean isLocationBottomLessPit(Location currentLocation) {
        // Check if the present location is a pit
        return currentLocation.getGround().getDisplayChar() == VoidPit.PIT_DISPLAY;
    }
}
