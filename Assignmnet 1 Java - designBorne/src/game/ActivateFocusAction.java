package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;

public class ActivateFocusAction  extends Action {

    static BroadSword sword;


    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> items =  actor.getItemInventory();

        //filter the broadsword and call the activate focus method in broadsword

        for(Item item : items)
        {
            if(item.getDisplayChar() == '1')
            {
                sword = (BroadSword) item;
                break;
            }
        }
        // using the actor as the parameter


        //this broadsword is in the inventory
        return sword.activateFocus((Player) actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        if (sword!=null) {
            return "Activate Focus Mode for BroadSword...valid for " + sword.getFocusTurnsRemaining() + " turns!!";
        }
        else {
            return "Activate Focus Mode for your BroadSword...valid for 5 turns.";
        }
    }
}
