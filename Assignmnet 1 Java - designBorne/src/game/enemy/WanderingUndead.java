package game.enemy;

import game.item.Key;
import game.item.potions.HealingVial;

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
