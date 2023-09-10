package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.demo.mars.items.MartianItem;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new VoidPit(), new Graveyard());



        List<String> burialGround = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~...."
        );

        GameMap gameMap2 = new GameMap(groundFactory2, burialGround);
        world.addGameMap(gameMap2);

        //--------------------------------------------------


        FancyGroundFactory groundFactory1 = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new VoidPit(), new Graveyard());
        List<String> abandonedVillage = Arrays.asList(
                "...........................................................",
                "...#######...................................+.............",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######..........................",
                "..........................#_____#..........................",
                "........~~........+.......#_____#..........................",
                ".........~~~..............###_###...................+......",
                "...~~~~~~~~................................................",
                "....~~~~~.................................###..##..........",
                "~~~~~~~...................................#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~+..............+..................#######..........");

        GameMap gameMap1 = new GameMap(groundFactory1, abandonedVillage);
        world.addGameMap(gameMap1);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        gameMap1.at(23, 10).addActor(new WanderingUndead());

        //add burialGround map to the game's map




        Player player = new Player("The Abstracted One", '@');
        world.addPlayer(player, gameMap1.at(29, 5));

        // Create BroadSword and place it inside the building
        BroadSword broadsword = new BroadSword();
        gameMap1.at(28, 5).addItem(broadsword);


        //add a gate :
        Gate gate = new Gate("Gate", '=', false);
        gate.addSampleAction(new MoveActorAction(gameMap2.at(7, 2), "to Burial Ground!"));
        gameMap1.at(1, 1).addItem(gate);

        world.run();
    }
}
