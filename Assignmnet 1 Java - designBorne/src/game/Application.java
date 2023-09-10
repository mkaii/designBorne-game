package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.enemy.HollowMan;
import game.enemy.WanderingUndead;
import game.ground.*;
import game.item.Gate;

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

        //add hollow man in Map 2
        //add main player
        HollowMan hollowMan1 = new HollowMan();
        world.addPlayer(hollowMan1, gameMap2.at(1, 1));


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

        //enemy placement :
        gameMap1.at(29, 7).addActor(new WanderingUndead());

        //add burialGround map to the game's map



        //add main player
        Player player = new Player();
        world.addPlayer(player, gameMap1.at(29, 5));

        // Create BroadSword and place it inside the building
        BroadSword broadsword = new BroadSword();
        gameMap1.at(28, 5).addItem(broadsword);


        //add a gate :
        Gate gate1 = new Gate();
        gate1.addSampleAction(new MoveActorAction(gameMap2.at(7, 2), "to Burial Ground!"));

        //add a gate :
        Gate gate2 = new Gate();
        gate2.addSampleAction(new MoveActorAction(gameMap1.at(55, 2), "to Abandoned village!"));

        gameMap1.at(30, 12).addItem(gate1);

        gameMap2.at(1, 1).addItem(gate2);


        world.run();
    }
}
