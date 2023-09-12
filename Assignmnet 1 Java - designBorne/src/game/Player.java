package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.ground.VoidPit;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor implements IBottomLessVoidPitBehavior{


    /**
     * Starting health points for the player.
     */
    public static final int STARTING_HEALTH = 150;

    /**
     * Starting stamina points for the player.
     */
    public static final int STARTING_STAMINA = 200;

    /**
     * Rate at which stamina is recovered.
     */
    private static final float STAMINA_RECOVERY_RATE = 0.01f;

    /**
     * Display character for the player in the UI.
     */
    public static final char DISPLAY_CHAR = '@';

    /**
     * Name to call the player in the UI.
     */
    private static final String PLAYER_NAME = "The Abstracted One";

    /**
     * Damage inflicted by the player's limbs.
     */
    private static final int LIMB_DAMAGE = 15;

    /**
     * Hit rate for the player's limbs.
     */
    private static final int LIMB_HIT_RATE = 80;


    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player() {
        super(PLAYER_NAME, DISPLAY_CHAR, STARTING_HEALTH);
        this.addCapability(Status.HOSTILE_TO_ENEMY);


        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(STARTING_STAMINA));
    }

    /**
     * Overrides the default intrinsic weapon of the player.
     * Returns a custom intrinsic weapon representing the player's limbs.
     *
     * @return IntrinsicWeapon representing the player's limbs.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        IntrinsicWeapon baseIntrinsicWeapon = super.getIntrinsicWeapon();
       return new IntrinsicWeapon(LIMB_DAMAGE,baseIntrinsicWeapon.verb(),LIMB_HIT_RATE);
    }


    /**
     * Overrides the playTurn method to define the player's actions on each turn.
     * Displays player's status, recovers stamina, and presents a menu for player actions.
     *
     * @param actions    Collection of possible Actions for the player.
     * @param lastAction The Action the player took last turn.
     * @param map        The GameMap containing the player.
     * @param display    The I/O object to which messages may be written.
     * @return The Action to be performed.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Print player's hit points and stamina
        display.println(this.name);
        display.println("HP: "  + String.valueOf(this.getAttribute(BaseActorAttributes.HEALTH)) + "/" + String.valueOf(this.getAttributeMaximum(BaseActorAttributes.HEALTH)));
        display.println("Stamina: "  + String.valueOf(this.getAttribute(BaseActorAttributes.STAMINA)) +  "/" + String.valueOf(this.getAttributeMaximum(BaseActorAttributes.STAMINA)));


        //check if present location is a pit
        // no need to tick over items as game map ticks over them anyway
        if(isLocationBottomLessPit(map.locationOf(this)))
        {
            return new VoidPitGroundMoveAction();
        }
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();


        //increase stamina on each turn :
        increaseStamina(); // Recover stamina by 1%

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);

    }


    /**
     * Gets the player's current stamina.
     *
     * @return The player's current stamina.
     */
    public int getStamina() {
        return this.getAttribute(BaseActorAttributes.STAMINA);
    }


    /**
     * Decreases the player's stamina by the specified amount.
     *
     * @param amount The amount to decrease the player's stamina.
     */
    public void decreaseStamina(int amount) {
        modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, amount);
    }

    /**
     * Increases the player's stamina based on the recovery rate.
     */
    public void increaseStamina() {
        int staminaIncrease = Math.round(getStamina() * STAMINA_RECOVERY_RATE);
        modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, staminaIncrease);
    }

    /**
     * Overrides the allowableActions method to define the player's allowable actions when interacting with other actors.
     * Currently, only the AttackAction is allowed.
     *
     * @param otherActor The actor the player is interacting with.
     * @param direction  String representing the direction of the other actor.
     * @param map        The current GameMap.
     * @return A collection of allowable Actions.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return new ActionList(new AttackAction(this,direction));
    }
}
