package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

/**
 * A class representing the action of dropping a healing vial item.
 */
public class HealingVileDropAction extends DropAction {
    /**
     * Constructor.
     *
     * @param item the item to drop
     */
    public HealingVileDropAction(Item item) {
        super(item);
    }

    /**
     * Executes the action of dropping a healing vial item.
     * When an item is dropped, it is removed from the actor's inventory and added to the current location of the actor.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string describing who has dropped which item, or {@code null} if the item was not dropped.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random random = new Random();
        if (random.nextFloat() <= 0.20) {
            // Drop a healing vial with 20% accuracy
            return super.execute(actor, map);
        }

        return null;
    }
}
