package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

/**
 * An extension of DropAction that represents dropping a refresh flask item.
 * This action has a chance to drop a key item when the refresh flask is dropped.
 * Created by:
 * Modified by:
 */
public class RefreshFlaskDropAction extends DropAction {
    /**
     * Constructor for RefreshFlaskDropAction.
     *
     * @param item The refresh flask item to drop.
     */
    public RefreshFlaskDropAction(Item item) {
        super(item);
    }

    /**
     * Execute the action of dropping a refresh flask item.
     * This method has a chance to drop a key item with 30% accuracy when the refresh flask is dropped.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap where the action is executed.
     * @return A string describing the result of the action, or null if the action fails.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random random = new Random();
        if (random.nextFloat() <= 0.30) {
            // Drop a key with 30% accuracy
            return super.execute(actor, map);
        }

        return null;
    }
}
