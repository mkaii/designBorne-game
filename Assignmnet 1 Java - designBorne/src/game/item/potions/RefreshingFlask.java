package game.item.potions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import game.ConsumeRefreshingFlask;
import game.KeyDropAction;
import game.Player;
import game.RefreshFlaskDropAction;

public class RefreshingFlask extends Item {

    public RefreshingFlask() {
        super("Refreshing flask",'u', true);
    }


    @Override
    public ActionList allowableActions(Actor player) {
        ActionList actions = super.allowableActions(player);
        if(player.getDisplayChar() == Player.DISPLAY_CHAR)
        {
            actions.add(new ConsumeRefreshingFlask());
        }

        return actions;
    }


    @Override
    public DropAction getDropAction(Actor actor) {
        if(portable)
            return new RefreshFlaskDropAction(this);
        return null;
    }
}
