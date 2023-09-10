package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class ConsumeRefreshingFlask extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {

        int staminaIncrease = (int) (0.2 * Player.STARTING_STAMINA);

        Item itemToRemove = null;

        for (Item item : actor.getItemInventory()) {
            if (item instanceof RefreshingFlask) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            actor.removeItemFromInventory(itemToRemove);
        }

        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, staminaIncrease);
        return "Refreshing Flask replenished " + actor + " stamina by " + staminaIncrease;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "consume Refreshing flask!";
    }


}
