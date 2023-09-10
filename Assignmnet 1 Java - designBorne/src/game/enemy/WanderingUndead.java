package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WanderingUndead extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    Key key;
    HealingVial healingPotion;

    public WanderingUndead() {
        super("Wandering Undead", 't', 100);

        //default damage should be 30
        updateDamageMultiplier(6); //by default its total-5 damage

        //Wandering undead should have a key and vials to begin with to begin with
        key = new Key();
        healingPotion = new HealingVial();

        //add to the chars inventory
        addItemToInventory(key);
        addItemToInventory(healingPotion);

        //ideally the undead should also have attack behavior ??
        //compare the play turn of WU with player
        //there is input acceptance there
        //In WU it should be solely based on behaviors ??
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
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
