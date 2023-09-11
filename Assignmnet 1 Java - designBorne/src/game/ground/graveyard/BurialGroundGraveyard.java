package game.ground.graveyard;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemy.HollowMan;
import game.enemy.WanderingUndead;

import java.util.Random;

public class BurialGroundGraveyard extends Ground {

    private static final char GRAVEYARD_CHAR = 'n';

    /**
     * Constructor for the BurialGroundGraveyard.
     */
    public BurialGroundGraveyard() {
        super(GRAVEYARD_CHAR);
    }

    //spawn wandering undead everytime game ticks on a graveyard
    public void tick(Location location) {
        Random random = new Random();
        if (random.nextFloat() <= 0.10) {
            // Spawn a WanderingUndead directly on the graveyard
            HollowMan hollowMan = new HollowMan();
            try {
                location.addActor(hollowMan);
            }
            catch (Exception ignored)
            {
                //do nothing
            }
        }
    }


}
