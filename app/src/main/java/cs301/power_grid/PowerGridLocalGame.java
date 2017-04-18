package cs301.power_grid;

import java.util.ArrayList;

import game.GamePlayer;
import game.LocalGame;
import game.actionMsg.GameAction;
import game.infoMsg.GameState;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 *
 * Contains game logic in makeMove class
 */
public class PowerGridLocalGame extends LocalGame{
    //instance variables
    private PowerState powerState = new PowerState();
    private int price;
    private int phase;
    private boolean has10Cities;

    @Override
    //Sends copy of updated state to given player
    protected void sendUpdatedStateTo(GamePlayer p) {
        //new copy of GameState using copy constructor
        PowerState newpgs = new PowerState(powerState);
        p.sendInfo((GameState)newpgs);
    }

    @Override
    //determines if a player can move
    protected boolean canMove(int playerIdx) {
        if (playerIdx == powerState.getTurn()){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    //checks to see if the game is over
    protected String checkIfGameOver() {
        int myOwnedCities = powerState.getGameInventories().get(1).getMyCities().size();
        int opponentOwnedCities = powerState.getGameInventories().get(1).getMyCities().size();

        if(myOwnedCities >= 10) {
            has10Cities = true;
        }
        else if(opponentOwnedCities >= 10) {
            has10Cities = true;
        }
        if(has10Cities && (powerState.getGamePhase() == 6)) {

        }
        return "";
    }

    /**
     * Make Move
     *
     * Contains all Game Logic
     *
     * receives an action:
     * BidAction, BuyCityAction, BuyCoalAction, BuyOilAction, BuyTrashAction,
     * BuyUraniumAction, DiscardPowerPlantAction, PassAction,
     * or SelectPowerStateAction
     * and performs necessary steps for action
     *
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //playerId, 0 = human player, 1 = computer/network player
        int turn = powerState.getTurn();
        phase = powerState.getTurn();
        //BidAction
        if (action instanceof BidAction && turn == 0){
            powerState.setCurrentBid(((BidAction) action).getBid());
            //change player
            powerState.changeTurn();
            return true;
        }
        else if (action instanceof BidAction && turn == 1){
            powerState.setCurrentBid(((BidAction) action).getBid());
            //change player
            powerState.changeTurn();
            return true;
        }

        //BuyCityAction
        else if (action instanceof BuyCityAction && turn == 0) {
            boolean didithappen = powerState.getGameInventories().get(0).addMyCity(((BuyCityAction) action).getCity());
            if(!didithappen){} //vibrate screen
            powerState.setBoughtCity(((BuyCityAction) action).getCityIndex());
            return true;
        }
        else if (action instanceof BuyCityAction && turn == 1) {
            boolean didithappen = powerState.getGameInventories().get(1).addMyCity(((BuyCityAction) action).getCity());
            if(!didithappen){} //vibrate screen
            powerState.setBoughtCity(((BuyCityAction) action).getCityIndex());
            return true;
        }

        //BuyCoalAction
        else if(action instanceof BuyCoalAction && turn == 0) {
            //determine price of coal
            price = (((BuyCoalAction) action).getCoal()/3+1);
            if (powerState.getAvailableResources().coal[((BuyCoalAction) action).getCoal()] && powerState.getGameInventories().get(0).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().coal[((BuyCoalAction) action).getCoal()] = false;
                //take money from user
                powerState.getGameInventories().get(0).setMoney(powerState.getGameInventories().get(0).getMoney() - price);
                //add coal to inventory
                powerState.getGameInventories().get(0).setCoal(powerState.getGameInventories().get(0).getCoal() + 1);
            }
                return true;
        }
        else if(action instanceof BuyCoalAction && turn == 1) {
            price = (((BuyCoalAction) action).getCoal()/3+1);
            if (powerState.getAvailableResources().coal[((BuyCoalAction) action).getCoal()] && powerState.getGameInventories().get(1).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().coal[((BuyCoalAction) action).getCoal()] = false;
                //take money from user
                powerState.getGameInventories().get(1).setMoney(powerState.getGameInventories().get(1).getMoney() - price);
                //add coal to inventory
                powerState.getGameInventories().get(1).setCoal(powerState.getGameInventories().get(1).getCoal() + 1);
            }
                    return true;
        }

        //BuyOilAction
        else if(action instanceof BuyOilAction && turn == 0) {
            price = (((BuyOilAction) action).getOil()/3+1);
            if (powerState.getAvailableResources().oil[((BuyOilAction) action).getOil()] && powerState.getGameInventories().get(0).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().oil[((BuyOilAction) action).getOil()] = false;
                //take money from user
                powerState.getGameInventories().get(0).setMoney(powerState.getGameInventories().get(0).getMoney() - price);
                //add oil to inventory
                powerState.getGameInventories().get(0).setOil(powerState.getGameInventories().get(0).getOil() + 1);
            }
            return true;
        }
        else if(action instanceof BuyOilAction && turn == 1) {
            price = (((BuyOilAction) action).getOil()/3+1);
            if (powerState.getAvailableResources().oil[((BuyOilAction) action).getOil()] && powerState.getGameInventories().get(1).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().oil[((BuyOilAction) action).getOil()] = false;
                //take money from user
                powerState.getGameInventories().get(1).setMoney(powerState.getGameInventories().get(1).getMoney() - price);
                //add oil to inventory
                powerState.getGameInventories().get(1).setOil(powerState.getGameInventories().get(1).getOil() + 1);
            }
            return true;
        }

        //BuyTrashAction
        else if(action instanceof BuyTrashAction && turn == 0) {
            price = (((BuyTrashAction) action).getTrash()/3+1);
            if (powerState.getAvailableResources().trash[((BuyTrashAction) action).getTrash()] && powerState.getGameInventories().get(0).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().trash[((BuyTrashAction) action).getTrash()] = false;
                //take money from user
                powerState.getGameInventories().get(0).setMoney(powerState.getGameInventories().get(0).getMoney() - price);
                //add oil to inventory
                powerState.getGameInventories().get(0).setTrash(powerState.getGameInventories().get(0).getTrash() + 1);
            }
            return true;
        }
        else if(action instanceof BuyTrashAction && turn == 1) {
            price = (((BuyTrashAction) action).getTrash()/3+1);
            if (powerState.getAvailableResources().trash[((BuyTrashAction) action).getTrash()] && powerState.getGameInventories().get(1).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().trash[((BuyTrashAction) action).getTrash()] = false;
                //take money from user
                powerState.getGameInventories().get(1).setMoney(powerState.getGameInventories().get(1).getMoney() - price);
                //add oil to inventory
                powerState.getGameInventories().get(1).setTrash(powerState.getGameInventories().get(1).getTrash() + 1);
            }
            return true;
        }

        //BuyUraniumAction
        else if(action instanceof BuyUraniumAction && turn == 0) {
            price = (((BuyUraniumAction) action).getUranium()/3+1);
            if (powerState.getAvailableResources().uranium[((BuyUraniumAction) action).getUranium()] && powerState.getGameInventories().get(0).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().uranium[((BuyUraniumAction) action).getUranium()] = false;
                //take money from user
                powerState.getGameInventories().get(0).setMoney(powerState.getGameInventories().get(0).getMoney() - price);
                //add oil to inventory
                powerState.getGameInventories().get(0).setUranium(powerState.getGameInventories().get(0).getUranium() + 1);
            }
            return true;
        }
        else if(action instanceof BuyUraniumAction && turn == 1) {
            price = (((BuyUraniumAction) action).getUranium()/3+1);
            if (powerState.getAvailableResources().uranium[((BuyUraniumAction) action).getUranium()] && powerState.getGameInventories().get(1).getMoney() >= price) {
                //make that resource unavailable for further purchase
                powerState.getAvailableResources().uranium[((BuyUraniumAction) action).getUranium()] = false;
                //take money from user
                powerState.getGameInventories().get(1).setMoney(powerState.getGameInventories().get(1).getMoney() - price);
                //add oil to inventory
                powerState.getGameInventories().get(1).setUranium(powerState.getGameInventories().get(1).getUranium() + 1);
            }
            return true;
        }

       /* //DiscardPowerPlantAction
        else if(action instanceof DiscardPowerPlantAction && turn == 0) {
            //find index of powerplant to be discarded
            Powerplant discard = ((DiscardPowerPlantAction) action).getPowerplant();
            int ppIndex = powerState.getGameInventories().get(0).getMyPlants().indexOf(discard);
            //remove indicated powerplant
            powerState.getGameInventories().get(0).getMyPlants().remove(ppIndex);
            return true;
        }
        else if(action instanceof DiscardPowerPlantAction && turn == 1) {
            //find index of powerplant to be discarded
            Powerplant discard = ((DiscardPowerPlantAction) action).getPowerplant();
            int ppIndex = powerState.getGameInventories().get(1).getMyPlants().indexOf(discard);
            //remove indicated powerplant
            powerState.getGameInventories().get(1).getMyPlants().remove(ppIndex);
            return true;
        }*/

        /**PassAction
         * A user may pass when they decide not to buy a powerplant, resources, cities
         * tacked onto arraylist of actions when game phases end, Pass action changes phase of game
         **/
        else if(action instanceof PassAction && turn == 0) {
            
            if (phase == 0) {
                //First player has chosen to pass on buying a powerplant, change turn
                //"OK" or "Pass" updates phase.
                powerState.setGamePhase(2);
                powerState.changeTurn();
            }
            else if (phase == 1 ) {
                //Bidding on power plant(s). if a user passes on a bid, change phase, dont change turn
                //first player still has a chance to buy a power plant, dont change turn go back to phase 1
                //give second player their power plant
                powerState.getGameInventories().get(1).addMyPlants(powerState.getAvailPowerplant().get(powerState.getSelectedPlant()));
                powerState.setGamePhase(2);
            }
            else if (phase == 2 ) {
                //Second player has chosen to pass on buying a power plant, change turn
                powerState.getGameInventories().get(0).addMyPlants(powerState.getAvailPowerplant().get(powerState.getSelectedPlant()));
                powerState.setGamePhase(3);
                powerState.changeTurn();
               
            }
            else if (phase == 3 || phase == 4) {
                //player is done buying resources
                //change phase and turn
                powerState.setGamePhase(phase++);
                powerState.changeTurn();
            }
            else if (phase == 5){
                //first player is done buying cities
                //change phase and turn
                powerState.setGamePhase(6);
                powerState.changeTurn();
            }
            else if(phase == 6) {
                //Second player is done buying cities
                //pay users based on how many plants they can power
                //change turn return to first phase
                powerState.changeTurn();
                powerState.setGamePhase(0);

            }

            return true;
        }

        else if(action instanceof PassAction && turn == 1) {
            //if user1 passes on a bid, user0 gets the selected power plant
            powerState.getGameInventories().get(1).addMyPlants(powerState.getAvailPowerplant().get(powerState.getSelectedPlant()));
            powerState.removePlant(powerState.getSelectedPlant());
            //change player
            powerState.changeTurn();
            return true;
        }

        //SelectPowerPlantAction
        else if(action instanceof SelectPowerPlantAction && turn == 0) {
            //user0 selects a powerplant and presses confirm which will start the bidding process with user1
            //highlight on GUI for humanplayer
            //change player and phase

            if (((SelectPowerPlantAction) action).getNum() == -1) {
                powerState.setGamePhase(3);
                powerState.changeTurn();
            }
            else{
                powerState.setGamePhase(1);
                powerState.setSelectedPlant(((SelectPowerPlantAction) action).getNum());
                powerState.changeTurn();
            }
            return true;
        }
        else if(action instanceof SelectPowerPlantAction && turn == 1) {
            //user1 selects a powerplant and presses confirm which will start the bidding process with user0
            //change player and phase
            powerState.setGamePhase(1);
            powerState.setSelectedPlant(((SelectPowerPlantAction) action).getNum());
            powerState.changeTurn();
            return true;
        }
        else {
            return false;
        }
    }//makeMove
}
