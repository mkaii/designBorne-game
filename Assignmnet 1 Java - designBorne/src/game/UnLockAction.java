package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.item.Gate;

import java.util.List;

public class UnLockAction extends Action {

    private Gate gate;

    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);

        List<Item> items = actorLocation.getItems();
        for (Item item : items) {
            if (item.getDisplayChar() == Gate.DISPLAY_GATE) {
                gate = (Gate) item;
            }
        }

        boolean unLocked = gate.setUnLocked(actor);

        if (unLocked) {
            return "Gate is now unlocked.";
        } else {
            return "Gate is locked shut.";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attempts to unlock the Gate.";
    }
}
