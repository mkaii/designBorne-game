package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.UnLockAction;

import java.util.List;

public class Gate extends Item {


    private Action moveAction;
    private boolean unLocked = false;

    public static final char DISPLAY_GATE = '=';
    public static final String GATE_NAME = "Gate";


    public boolean setUnLocked(Actor actor) {

        if(unLocked)
            return true;

        for(Item item : actor.getItemInventory())
        {
            if(item.getDisplayChar() == Key.DISPLAY_KEY)
            {
                this.unLocked = true;
                return true;
            }
        }
        return false;
    }




    public boolean isUnLocked() {
        return unLocked;
    }

    public Gate() {
        super("gate", '=', false);
    }

    public void addSampleAction(Action newAction){
        this.moveAction = newAction;
    }



    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(new UnLockAction());
        if(this.isUnLocked()) {
            actions.add(moveAction);
        }
        return actions;
    }



}
