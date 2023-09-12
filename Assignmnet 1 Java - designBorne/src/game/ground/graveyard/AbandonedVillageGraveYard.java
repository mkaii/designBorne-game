package game.ground.graveyard;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemy.WanderingUndead;

import java.util.Random;

/**
 * A class representing an abandoned village graveyard in the game.
 * Wandering Undead may spawn in this graveyard when the game ticks on it.
 */
public class AbandonedVillageGraveYard extends Ground {
    private static final char GRAVEYARD_CHAR = 'n';

    /**
     * Constructor for creating an AbandonedVillageGraveYard.
     */
    public AbandonedVillageGraveYard() {
        super(GRAVEYARD_CHAR);
    }

    /**
     * Performs an action when the game ticks on this location. It has a chance to spawn a WanderingUndead.
     *
     * @param location the location where the action occurs
     */
    public void tick(Location location) {
        Random random = new Random();
        if (random.nextFloat() <= 0.25) {
            // Spawn a WanderingUndead directly on the graveyard
            WanderingUndead wanderingUndead = new WanderingUndead();
            try {
                location.addActor(wanderingUndead);
            } catch (Exception ignored) {
                // Handle any exceptions that may occur when adding the actor
            }
        }
    }
}
