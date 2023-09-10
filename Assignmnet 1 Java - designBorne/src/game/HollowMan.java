package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HollowMan extends Actor {

    HealingVial healingPotion;
    RefreshingFlask refreshingFlask;


    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public HollowMan() {
        super("Hollow Soldier", '&', 200);

        //default damage should be 50
        updateDamageMultiplier(10);


        //Wandering undead should have a key and vials to begin with to begin with

        healingPotion = new HealingVial();
        refreshingFlask = new RefreshingFlask();

        //todo : add to hollow mans inventory :
        addItemToInventory(healingPotion);
        addItemToInventory(refreshingFlask);

        this.behaviours.put(999, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        //check if the actions have an attack action:
        for(Action action : actions)
        {
            if (action instanceof AttackAction) {
                return action;
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }


    /**
     * The wandering undead can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){

            boolean weaponFound = false;
            //set the attack action with a weapon if it is there in the inventory
            for(Item item : otherActor.getItemInventory())
            {
                if(item.getDisplayChar() == '1')
                {
                    actions.add(new AttackAction(this, direction, (Weapon) item));
                    weaponFound = true;
                }
            }

            if(!weaponFound) {
                actions.add(new AttackAction(this, direction));
            }
        }
        return actions;
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {

        List<Action> actionsToExecute = new ArrayList<>();

        for (Item item : new ArrayList<>(getItemInventory())) {  // Create a copy of the list to safely iterate over it
            Action action = item.getDropAction(this);
            if (action != null) {
                actionsToExecute.add(action);
            }
        }

        for (Action action : actionsToExecute) {
            action.execute(this, map);
        }
        return super.unconscious(actor, map);
    }

}
