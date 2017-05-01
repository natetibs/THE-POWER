package cs301.power_grid;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * PowerGridDumbComputerPlayer
 *
 * pretty dumb AI, makes little attempt to win
 *
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class PowerGridDumbComputerPlayer extends GameComputerPlayer {
    //instance variables
    PowerState powerState = new PowerState();

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PowerGridDumbComputerPlayer(String name) {
        super(name);
    }

    /**
     * receiveInfo
     *
     * receives a game state and sends a pass action
     *
     * @param info
     */
    public void receiveInfo(GameInfo info) {
        /*Check if info is a GameState object.*/
        if (info instanceof PowerState) {
            powerState = (PowerState)info;
            if(powerState.getTurn() != playerNum) return;
            /*Determine the phase of the game before making a move.*/
            int phase = powerState.getGamePhase();
            if (phase == 0) {
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 1) {
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 2) {
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 3) {
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 4) {
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 5) {
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 6) {
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);
            }
        }
    }
}