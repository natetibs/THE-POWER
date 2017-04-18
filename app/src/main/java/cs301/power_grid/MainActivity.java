package cs301.power_grid;

import java.util.ArrayList;

import game.GameMainActivity;
import game.GamePlayer;
import game.LocalGame;
import game.config.GameConfig;
import game.config.GamePlayerType;

/**
 * External Citation
 * Date:     31 March 2017
 * Problem:  setting up game configurations in MainActivity
 * Resource: Moodle/Lab
 * Solution: I used the example code from the pig lab for this class
 */
public class MainActivity extends GameMainActivity {
    //port number to use over network play
    private static final int PORT_NUMBER = 2451;

    @Override
    public GameConfig createDefaultConfig() {

        //Define player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<>();

        //Add human player, smart and dumb computer players
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new PowerGridHumanPlayer(name);}});
        playerTypes.add(new GamePlayerType("Easy Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new PowerGridDumbComputerPlayer(name);}});
        playerTypes.add(new GamePlayerType("Hard Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new PowerGridSmartComputerPlayer(name);}});

        //Create game configuration class for PowerGrid
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Power Grid", PORT_NUMBER);
        //add players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Easy Computer", 1);
        defaultConfig.addPlayer("Hard Computer", 1);
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {
        //create a local game and return it
        PowerGridLocalGame plg = new PowerGridLocalGame();
        return plg;
    }
}