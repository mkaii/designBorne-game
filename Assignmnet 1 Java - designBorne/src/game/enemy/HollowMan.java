package game.enemy;

import game.item.potions.HealingVial;
import game.item.potions.RefreshingFlask;

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
