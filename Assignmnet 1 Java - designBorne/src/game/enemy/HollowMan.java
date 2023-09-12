package game.enemy;

import game.item.potions.HealingVial;
import game.item.potions.RefreshingFlask;

/**
 * A class representing a Hollow Soldier enemy.
 */
public class HollowMan extends EnemyBase {

    private HealingVial healingPotion;
    private RefreshingFlask refreshingFlask;

    /**
     * Constructor for creating a Hollow Soldier.
     */
    public HollowMan() {
        super("Hollow Soldier", '&', 200);

        // Default damage should be 50
        updateDamageMultiplier(10);

        // Hollow Soldiers should have a healing vial and a refreshing flask in their inventory
        healingPotion = new HealingVial();
        refreshingFlask = new RefreshingFlask();

        // Add items to the Hollow Soldier's inventory
        addItemToInventory(healingPotion);
        addItemToInventory(refreshingFlask);
    }
}
