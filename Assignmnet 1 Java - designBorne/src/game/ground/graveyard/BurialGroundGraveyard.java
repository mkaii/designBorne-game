package game.ground.graveyard;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemy.HollowMan;
import game.enemy.WanderingUndead;

import java.util.Random;

/**
 * A class representing a burial ground graveyard in the game.
 * Hollow Men may spawn in this graveyard when the game ticks on it.
 */
public class BurialGroundGraveyard extends Ground {

    private static final char GRAVEYARD_CHAR = 'n';

    /**
     * Constructor for creating a BurialGroundGraveyard.
     */
    public BurialGroundGraveyard() {
        super(GRAVEYARD_CHAR);
    }

    /**
     * Performs an action when the game ticks on this location. It has a chance to spawn a HollowMan.
     *
     * @param location the location where the action occurs
     */
    public void tick(Location location) {
        Random random = new Random();
        if (random.nextFloat() <= 0.10) {
            // Spawn a HollowMan directly on the graveyard
            HollowMan hollowMan = new HollowMan();
            try {
                location.addActor(hollowMan);
            } catch (Exception ignored) {
                // Handle any exceptions that may occur when adding the actor
            }
        }
    }
}
