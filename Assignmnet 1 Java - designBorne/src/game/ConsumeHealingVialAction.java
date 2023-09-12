package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.item.potions.HealingVial;

/**
 * A class representing the action of consuming a healing vial item to increase health.
 */
public class ConsumeHealingVialAction extends Action {
    /**
     * Executes the action of consuming a healing vial item to increase health.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string describing the result of consuming the healing vial.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int healthIncrease = (int) (0.1 * Player.STARTING_HEALTH);
        actor.heal(healthIncrease);

        Item itemToRemove = null;

        // Find and remove the healing vial from the actor's inventory
        for (Item item : actor.getItemInventory()) {
            if (item instanceof HealingVial) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            actor.removeItemFromInventory(itemToRemove);
        }

        return "Healing vial replenished " + actor + " health by " + healthIncrease;
    }

    /**
     * Returns a description of the action for the in-game menu.
     *
     * @param actor The actor performing the action.
     * @return A menu description string.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "The " + actor + " consumes Healing vial?";
    }
}
