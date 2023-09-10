package game.item;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import game.KeyDropAction;

public class Key extends Item {

    public static final char DISPLAY_KEY = '-';
    public static final String GATE_NAME = "Key";

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Key() {
        super(GATE_NAME,DISPLAY_KEY, true);
    }


    @Override
    public DropAction getDropAction(Actor actor) {
        if(portable)
            return new KeyDropAction(this);
        return null;
    }
}
