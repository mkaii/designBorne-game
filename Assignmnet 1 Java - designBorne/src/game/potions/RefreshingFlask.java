package game.potions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.ConsumeRefreshingFlask;

public class RefreshingFlask extends Item {

    public RefreshingFlask() {
        super("Refreshing flask",'u', true);
    }


    @Override
    public ActionList allowableActions(Actor player) {
        ActionList actions = super.allowableActions(player);
        if(player.getDisplayChar() == '@')
        {
            actions.add(new ConsumeRefreshingFlask());
        }

        return actions;
    }
}
