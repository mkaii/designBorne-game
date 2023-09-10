package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemy.WanderingUndead;

import java.util.Random;

public class Graveyard extends Ground {

    private static final char GRAVEYARD_CHAR = 'n';

    /**
     * Constructor for the Graveyard.
     */
    public Graveyard() {
        super(GRAVEYARD_CHAR);
    }

    //spawn wandering undead everytime game ticks on a graveyard
    public void tick(Location location) {
        Random random = new Random();
        if (random.nextFloat() <= 0.25) {
            // Spawn a WanderingUndead directly on the graveyard
            WanderingUndead wanderingUndead = new WanderingUndead();
            location.addActor(wanderingUndead);
        }
    }


}
