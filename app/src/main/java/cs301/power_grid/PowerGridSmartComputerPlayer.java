package cs301.power_grid;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class PowerGridSmartComputerPlayer extends GameComputerPlayer{
    PowerState powerState = new PowerState();
    private int selectNum = -1;
    Inventory inv;
    private int money;
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PowerGridSmartComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        /*Check if info is a GameState object.*/
        if (info instanceof PowerState) {

            powerState = (PowerState)info;
            if(powerState.getTurn() != playerNum) return;
            /*Determine the phase of the game before making a move.*/
            int phase = powerState.getGamePhase();
            //get computer inventory
            inv = powerState.getGameInventories().get(1);
            /*Make sure the dumb AI has enough money to buy anything.*/
            if (phase == 0) {

                PassAction pa = new PassAction(PowerGridSmartComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 1) {

                PassAction pa = new PassAction(PowerGridSmartComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 2) {

                PassAction pa = new PassAction(PowerGridSmartComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 3) {

                PassAction pa = new PassAction(PowerGridSmartComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 4) {

                PassAction pa = new PassAction(PowerGridSmartComputerPlayer.this);
                game.sendAction(pa);

            } else if(phase == 5) {

                PassAction pa = new PassAction(PowerGridSmartComputerPlayer.this);
                game.sendAction(pa);
            /*Pass*/
            } else if(phase == 6) {

                PassAction pa = new PassAction(PowerGridSmartComputerPlayer.this);
                game.sendAction(pa);
            }
        }

    }
}
