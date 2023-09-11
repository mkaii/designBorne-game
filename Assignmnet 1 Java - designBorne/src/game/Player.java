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


    public static final int STARTING_HEALTH = 150;
    public static final int STARTING_STAMINA = 200;
    private static final float STAMINA_RECOVERY_RATE = 0.01f;
    public static final char DISPLAY_CHAR = '@';
    private static final String PLAYER_NAME = "The Abstracted One";


    private static final int LIMB_DAMAGE = 15;
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

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        IntrinsicWeapon baseIntrinsicWeapon = super.getIntrinsicWeapon();
       return new IntrinsicWeapon(LIMB_DAMAGE,baseIntrinsicWeapon.verb(),LIMB_HIT_RATE);
    }

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


    public int getStamina() {
        return this.getAttribute(BaseActorAttributes.STAMINA);
    }

    public void decreaseStamina(int amount) {
        modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, amount);
    }

    public void increaseStamina() {
        int staminaIncrease = Math.round(getStamina() * STAMINA_RECOVERY_RATE);
        modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, staminaIncrease);
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return new ActionList(new AttackAction(this,direction));
    }
}
