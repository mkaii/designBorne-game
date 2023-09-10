package game.item.potions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import game.ConsumeHealingVialAction;
import game.HealingVileDropAction;
import game.KeyDropAction;
import game.Player;

public class HealingVial extends Item {


    public HealingVial() {
        super("Healing Vial",'a', true);
    }


    @Override
    public ActionList allowableActions(Actor player) {
        ActionList actions = super.allowableActions(player);
        if(player.getDisplayChar() == Player.DISPLAY_CHAR)
        {
            actions.add(new ConsumeHealingVialAction());
        }

        return actions;
    }

    @Override
    public DropAction getDropAction(Actor actor) {
        if(portable)
            return new HealingVileDropAction(this);
        return null;
    }
}
