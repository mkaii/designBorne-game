package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class representing the action of dropping a broadsword item.
 */
public class BroadSwordDropAction extends DropAction {

    private final BroadSword broadSword;

    /**
     * Constructor for the BroadSwordDropAction.
     *
     * @param item The broadsword item to drop.
     */
    public BroadSwordDropAction(Item item) {
        super(item);
        this.broadSword = (BroadSword) item;
    }

    /**
     * Executes the action of dropping a broadsword item and deactivates its focus ability.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string describing who has dropped which item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        broadSword.deactivateFocus();
        return super.execute(actor, map);
    }
}
