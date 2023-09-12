package game.enemy;

import game.item.Key;
import game.item.potions.HealingVial;

/**
 * A class representing a Wandering Undead enemy.
 */
public class WanderingUndead extends EnemyBase {

    private Key key;
    private HealingVial healingPotion;

    /**
     * Constructor for creating a Wandering Undead enemy.
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100);

        // Default damage should be 30
        updateDamageMultiplier(6); // By default, it inflicts 5 damage

        // Wandering Undead should have a key and healing vial in their inventory
        key = new Key();
        healingPotion = new HealingVial();

        // Add items to the Wandering Undead's inventory
        addItemToInventory(key);
        addItemToInventory(healingPotion);
    }
}
