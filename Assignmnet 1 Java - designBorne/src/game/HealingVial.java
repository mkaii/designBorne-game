package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class HealingVial extends Item {


    public HealingVial() {
        super("Healing Vial",'a', true);
    }


    @Override
    public ActionList allowableActions(Actor player) {
        ActionList actions = super.allowableActions(player);
        if(player.getDisplayChar() == '@')
        {
            actions.add(new ConsumeHealingVialAction());
        }

        return actions;
    }
}
