package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class VoidPitGroundMoveAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {

        // Set health to zero
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, 0);

        // The following line will remove the main player from the map, and the game engine will terminate the game.
        map.removeActor(actor);

        if (actor.getDisplayChar() == Player.DISPLAY_CHAR) {
            return FancyMessage.YOU_DIED;
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
