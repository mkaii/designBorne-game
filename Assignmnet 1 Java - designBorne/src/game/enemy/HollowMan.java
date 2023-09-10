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
import game.potions.HealingVial;
import game.potions.RefreshingFlask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HollowMan extends EnemyBase {

    HealingVial healingPotion;
    RefreshingFlask refreshingFlask;



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


    }








}
