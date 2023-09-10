package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeHealingVialAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {

        int healthIncrease = (int) (0.1 * Player.STARTING_HEALTH);
        actor.heal(healthIncrease);


        Item itemToRemove = null;

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

    @Override
    public String menuDescription(Actor actor) {
        return "The " + actor + " consumes Healing vial ?";
    }
}
