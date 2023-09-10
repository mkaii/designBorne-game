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

                //this broadsword is in the inventory
                return sword.activateFocus((Player) actor);
            }
        }

        return null;

    }

    @Override
    public String menuDescription(Actor actor) {

        return actor + " activates the skill of the broadsword.";
    }
}
