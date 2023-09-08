package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;

import java.util.List;

public class Gate extends Ground {

    public Gate() {
        super('=');

    }


    @Override
    public boolean canActorEnter(Actor actor) {
        //only allow for main player
        if(actor.getDisplayChar() == '@')
        {
            List<Item> inventoryItems = actor.getItemInventory();
            for(Item item : inventoryItems)
            {
                if(item.getDisplayChar() == '-')
                {
                    return true;
                }
            }
        }
        return false;
    }
}
