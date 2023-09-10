package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class BroadSwordDropAction extends DropAction {

    private final BroadSword broadSword;

    /**
     * Constructor.
     *
     * @param item the item to drop
     */
    public BroadSwordDropAction(Item item) {
        super(item);
        this.broadSword = (BroadSword) item;

    }


    /**
     * When an item is dropped, remove the item from the actor's inventory and add it to the current location of the actor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing who has dropped which item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        broadSword.deactivateFocus();
        return super.execute(actor,map);
    }
}
