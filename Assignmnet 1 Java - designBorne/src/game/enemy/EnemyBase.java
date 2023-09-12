package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract base class for enemy actors in the game.
 */
public abstract class EnemyBase extends Actor implements IBottomLessVoidPitBehavior {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor for creating an enemy actor.
     *
     * @param name        the name of the enemy actor
     * @param displayChar the character that represents the enemy actor in the display
     * @param hitPoints   the enemy actor's starting hit points
     */
    public EnemyBase(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        this.behaviours.put(999, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Check if the current location is a pit
        if (isLocationBottomLessPit(map.locationOf(this))) {
            return new VoidPitGroundMoveAction();
        }

        // Check if there is an attack action in the list of actions
        for (Action action : actions) {
            if (action instanceof AttackAction) {
                return action;
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Defines the allowable actions for the enemy actor.
     *
     * @param otherActor the actor that might be performing an attack
     * @param direction  the direction in which the other actor is located
     * @param map        the current GameMap
     * @return a list of allowable actions for the enemy actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {

            boolean weaponFound = false;
            // Set the attack action with a weapon if it is in the inventory
            for (Item item : otherActor.getItemInventory()) {
                if (item.getDisplayChar() == '1') {
                    actions.add(new AttackAction(this, direction, (Weapon) item));
                    weaponFound = true;
                }
            }

            if (!weaponFound) {
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
