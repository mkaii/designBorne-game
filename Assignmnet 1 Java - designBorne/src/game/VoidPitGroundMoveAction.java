package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

public class VoidPitGroundMoveAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {

        //set health to zero
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, 0);

        //the following line will remove the player from the map and the game engine will terminate the game.
        map.removeActor(actor);
        return FancyMessage.YOU_DIED;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
