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
import game.item.Key;
import game.potions.HealingVial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WanderingUndead extends EnemyBase {

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

    }

}
