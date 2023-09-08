package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;

public class BroadSword extends WeaponItem {



    private boolean isFocusActive;

    private int focusTurnsRemaining;

    public BroadSword() {
        super("BroadSword", '1', 110, "slashes", 80);
        isFocusActive = false;
        focusTurnsRemaining = 0;

    }

    public boolean isFocusActive() {
        return isFocusActive;
    }

    public int getFocusTurnsRemaining() {
        return focusTurnsRemaining;
    }

    public String activateFocus(Player myPlayer) {
        if (!isFocusActive) {
            isFocusActive = true;
            updateDamageMultiplier(1.1f); // Increase damage multiplier by 10%
            updateHitRate(90); // Set hit rate to 90%
            focusTurnsRemaining = 5;
            myPlayer.decreaseStamina(Math.round(myPlayer.getStamina() * 0.2f)); // Reduce stamina by 20%
        } else {
            // If focus is already active, reset turns to 5 and increase damage multiplier by 10%
            focusTurnsRemaining = 5;
            updateDamageMultiplier(1.1f);
        }

        return "Focus mode activated for : " + myPlayer + ". 5 turns remaining!!";
    }

    public void deactivateFocus() {
        if (isFocusActive) {
            isFocusActive = false;
            updateDamageMultiplier(1.0f); // Reset damage multiplier to 1.0f
            updateHitRate(80); // Reset hit rate to 80%
        }
    }


    @Override
    public ActionList allowableActions(Actor player) {
        ActionList actions = super.allowableActions(player);
        actions.add(new ActivateFocusAction());
        return actions;
    }

    @Override
    public void tick(Location currentLocation) {
        if (isFocusActive) {
            focusTurnsRemaining--;
            if (focusTurnsRemaining <= 0) {
                deactivateFocus(); // Deactivate focus when turns run out

            }
        }
        //reduce stamina is not required as it should only be reduced during activating focus
    }
}