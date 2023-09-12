package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;

/**
 * An action class that represents the activation of focus for a broadsword.
 */
public class ActivateFocusAction extends Action {

    static BroadSword sword;

    /**
     * Executes the activation of focus for a broadsword.
     *
     * @param actor the actor performing the action
     * @param map   the game map where the action occurs
     * @return a description of the activation result, or null if the activation fails
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> items =  actor.getItemInventory();

        // Filter the broadsword and call the activate focus method in broadsword
        for(Item item : items) {
            if(item.getDisplayChar() == '1') {
                sword = (BroadSword) item;

                // This broadsword is in the inventory
                return sword.activateFocus((Player) actor);
            }
        }

        return null;
    }

    /**
     * Describes the menu option for activating the skill of the broadsword.
     *
     * @param actor the actor performing the action
     * @return a description of the menu option
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of the broadsword.";
    }
}
