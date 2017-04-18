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
    private int phase = powerState.getGamePhase();
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
        if (playerIdx == powerState.getPlayerId()){
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
        int playerID = powerState.getPlayerId();

        //BidAction
        if (action instanceof BidAction && playerID == 0){
            powerState.setCurrentBid(((BidAction) action).getBid());
            //change player
            powerState.setPlayerId(1);
            return true;
        }
        else if (action instanceof BidAction && playerID == 1){
            powerState.setCurrentBid(((BidAction) action).getBid());
            //change player
            powerState.setPlayerId(0);
            return true;
        }

        //BuyCityAction
        else if (action instanceof BuyCityAction && playerID == 0) {
            boolean didithappen = powerState.getGameInventories().get(0).addMyCity(((BuyCityAction) action).getCity());
            if(!didithappen){} //vibrate screen
            powerState.setBoughtCity(((BuyCityAction) action).getCityIndex());
            return true;
        }
        else if (action instanceof BuyCityAction && playerID == 1) {
            boolean didithappen = powerState.getGameInventories().get(1).addMyCity(((BuyCityAction) action).getCity());
            if(!didithappen){} //vibrate screen
            powerState.setBoughtCity(((BuyCityAction) action).getCityIndex());
            return true;
        }

        //BuyCoalAction
        else if(action instanceof BuyCoalAction && playerID == 0) {
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
        else if(action instanceof BuyCoalAction && playerID == 1) {
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
        else if(action instanceof BuyOilAction && playerID == 0) {
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
        else if(action instanceof BuyOilAction && playerID == 1) {
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
        else if(action instanceof BuyTrashAction && playerID == 0) {
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
        else if(action instanceof BuyTrashAction && playerID == 1) {
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
        else if(action instanceof BuyUraniumAction && playerID == 0) {
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
        else if(action instanceof BuyUraniumAction && playerID == 1) {
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
        else if(action instanceof DiscardPowerPlantAction && playerID == 0) {
            //find index of powerplant to be discarded
            Powerplant discard = ((DiscardPowerPlantAction) action).getPowerplant();
            int ppIndex = powerState.getGameInventories().get(0).getMyPlants().indexOf(discard);
            //remove indicated powerplant
            powerState.getGameInventories().get(0).getMyPlants().remove(ppIndex);
            return true;
        }
        else if(action instanceof DiscardPowerPlantAction && playerID == 1) {
            //find index of powerplant to be discarded
            Powerplant discard = ((DiscardPowerPlantAction) action).getPowerplant();
            int ppIndex = powerState.getGameInventories().get(1).getMyPlants().indexOf(discard);
            //remove indicated powerplant
            powerState.getGameInventories().get(1).getMyPlants().remove(ppIndex);
            return true;
        }*/

        //PassAction
        else if(action instanceof PassAction && playerID == 0) {
            //if user0 passes on a bid, user1 gets the selected power plant

            powerState.getGameInventories().get(1).addMyPlants(powerState.getAvailPowerplant().get(powerState.getSelectedPlant()));
            powerState.removePlant(powerState.getSelectedPlant());
            //change player
            powerState.setPlayerId(1);
            return true;
        }

        else if(action instanceof PassAction && playerID == 1) {
            //if user1 passes on a bid, user0 gets the selected power plant
            powerState.getGameInventories().get(1).addMyPlants(powerState.getAvailPowerplant().get(powerState.getSelectedPlant()));
            powerState.removePlant(powerState.getSelectedPlant());
            //change player
            powerState.setPlayerId(0);
            return true;
        }

        //SelectPowerPlantAction
        else if(action instanceof SelectPowerPlantAction && playerID == 0) {
            //user0 selects a powerplant and presses confirm which will start the bidding process with user1
            //highlight on GUI for humanplayer
            //change player
            powerState.setSelectedPlant(((SelectPowerPlantAction) action).getNum());
            powerState.setPlayerId(1);
            return true;
        }
        else if(action instanceof SelectPowerPlantAction && playerID == 1) {
            //user1 selects a powerplant and presses confirm which will start the bidding process with user0
            //change player
            powerState.setSelectedPlant(((SelectPowerPlantAction) action).getNum());
            powerState.setPlayerId(0);
            return true;
        }
        else {
            return false;
        }
    }//makeMove
}
