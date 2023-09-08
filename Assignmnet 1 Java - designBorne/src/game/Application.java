package game;

import java.util.Arrays;
import java.util.List;

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

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new VoidPit(), new Graveyard(), new Gate());

        List<String> abandonedVillage = Arrays.asList(
                "...........................................................",
                "...#######...................................+.............",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######.............=............",
                "..........................#_____#..........................",
                "........~~........+.......#_____#..........................",
                ".........~~~..............###_###...................+......",
                "...~~~~~~~~................................................",
                "....~~~~~.................................###..##..........",
                "~~~~~~~...................................#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~+..............+..................#######..........");

        List<String> burialGround = Arrays.asList(
                "....=......+++++++........~~~~~~++....~~",
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

        GameMap gameMap1 = new GameMap(groundFactory, abandonedVillage);
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

        GameMap gameMap2 = new GameMap(groundFactory, abandonedVillage);
        world.addGameMap(gameMap2);


        Player player = new Player("The Abstracted One", '@', 150);
        world.addPlayer(player, gameMap1.at(29, 5));

        // Create BroadSword and place it inside the building
        BroadSword broadsword = new BroadSword();
        gameMap1.at(28, 5).addItem(broadsword);

        world.run();
    }
}
