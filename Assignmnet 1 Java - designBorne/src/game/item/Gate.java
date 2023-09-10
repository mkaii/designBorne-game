package game.item;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

public class Gate extends Item {

    private Action martianAction;

    public Gate(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public void addSampleAction(Action newAction){
        this.martianAction = newAction;
    }

    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = super.allowableActions(location);
        actions.add(martianAction);
        return actions;
    }



}
