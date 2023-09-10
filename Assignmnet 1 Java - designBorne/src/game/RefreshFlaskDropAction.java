package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

public class RefreshFlaskDropAction extends DropAction {
    /**
     * Constructor.
     *
     * @param item the item to drop
     */
    public RefreshFlaskDropAction(Item item) {
        super(item);
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        Random random = new Random();
        if (random.nextFloat() <= 0.30) {
            //drop a key with 20% accuracy
            return super.execute(actor,map);
        }

        return null;
    }
}
