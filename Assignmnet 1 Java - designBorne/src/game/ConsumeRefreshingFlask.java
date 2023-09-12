package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.potions.RefreshingFlask;

/**
 * A class representing the action of consuming a refreshing flask item to increase stamina.
 */
public class ConsumeRefreshingFlask extends Action {

    /**
     * Executes the action of consuming a refreshing flask item to increase stamina.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string describing the result of consuming the refreshing flask.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int staminaIncrease = (int) (0.2 * Player.STARTING_STAMINA);
        Item itemToRemove = null;

        // Find and remove the refreshing flask from the actor's inventory
        for (Item item : actor.getItemInventory()) {
            if (item instanceof RefreshingFlask) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            actor.removeItemFromInventory(itemToRemove);
        }

        // Increase the actor's stamina and provide a description of the action
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, staminaIncrease);
        return "Refreshing Flask replenished " + actor + " stamina by " + staminaIncrease;
    }

    /**
     * Returns a description of the action for the in-game menu.
     *
     * @param actor The actor performing the action.
     * @return A menu description string.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Refreshing flask!";
    }
}
