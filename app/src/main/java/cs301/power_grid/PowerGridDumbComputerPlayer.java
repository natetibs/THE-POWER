package cs301.power_grid;

import android.os.Bundle;
import android.view.KeyEvent;
import game.Game;
import game.GameComputerPlayer;
import game.GameMainActivity;
import game.GamePlayer;
import game.infoMsg.GameInfo;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class PowerGridDumbComputerPlayer extends GameComputerPlayer {
    private GameMainActivity activity;
    PowerState powerState = new PowerState();
    private int selectNum = -1;
    private int bidValue = -1;
    private ResourceStore localStore = new ResourceStore();
    private Powerplant tempPlant;
    private Powerplant selectedPowerPlant;
    private boolean isClicked = false;
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PowerGridDumbComputerPlayer(String name) {
        super(name);
    }

    public void receiveInfo(GameInfo info) {
        /*Check if info is a GameState object.*/
        if (info instanceof PowerState) {
            powerState = (PowerState)info;
            /*Determine the phase of the game before making a move.*/
            int phase = powerState.getGamePhase();
            /*Make sure the dumb AI has enough money to buy anything.*/
            if (phase == 0) {
                /*First player chooses a power plant.
                * "OK" or "Pass" updates phase.*/
                /*Use a random integer because this is the dumb AI.*/
                int randOKPass = (int)(Math.random() * 2);
                int randPPlantToBidOnIndex = (int)(Math.random() * 4);
                if(randOKPass == 0) {
                    PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                    game.sendAction(pa);
                }
                else {
                    SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectNum);
                    game.sendAction(sppa);
                }

            /*Make sure the dumb AI has enough money to buy anything.*/
            } else if(phase == 1) {
                /*Bidding on power plant(s).
                * Bidding or "pass" updates phase.*/
                BidAction bid;
                int randOKPass = (int)(Math.random()*2);
                /*You could just put this in an else statement after evaluating randOKPass below.*/
                if(randOKPass == 0) {
                    bid = new BidAction(this, (int)Math.random()*20);
                    bid.getBid();
                    game.sendAction(bid);
                }
                else if(randOKPass == 1) {
                    /*pass*/
                    int newBid = powerState.getCurrentBid() + 1;
                    BidAction newBidAct = new BidAction(PowerGridDumbComputerPlayer.this, newBid);
                    game.sendAction(newBidAct);
                }
            /*Make sure the dumb AI has enough money to buy anything.*/
            } else if(phase == 2) {
                /*Previous passer chooses a power plant.
                * "OK" or "Pass" updates phase.*
                * Same as when phase is 0 b/c same logic.
                * We don't determine who the previous passer was in this if statement.*/
                int randOKPass = (int)(Math.random() * 2);
                int randPPlantToBidOn = (int)(Math.random() * 42);
                /*You could just put this in an else statement after evaluating randOKPass below.*/
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);
                if(randOKPass == 1) {
                    SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectNum);
                    game.sendAction(sppa);
                }
                /*Pass*/
            } else if(phase == 3) {
                /*Second player chooses resources.
                * "OK" or "Pass" updates phase.*/
                /*Unnecessary since you're just making it pass.*/
                /*You could just put this in an else statement after evaluating randOKPass below.*/
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
            /*Pass*/
            } else if(phase == 4) {
                /*First player chooses resources.
                * "OK" or "Pass" updates phase.*/
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                /*Pass*/
            } else if(phase == 5) {
                /*Second player chooses cities.
                * "OK" or "Pass" updates phase.*/
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);
            /*Pass*/
            } else if(phase == 6) {
                /*First player chooses cities.
                * "OK" or "Pass" updates phase.*/
                PassAction pa = new PassAction(PowerGridDumbComputerPlayer.this);
                game.sendAction(pa);
            } else {
                /*Other cases go here.*/
            }
        }
    }
}