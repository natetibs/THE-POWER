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
    private int price, phase, turn;
    private boolean has10Cities;
    private boolean[] moveMade = {false,false,false};
    private int i = 0;
    private int j = -1;



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
            return "the game is over?";
        }
        else{return null;}
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

        turn = powerState.getTurn();
        phase = powerState.getGamePhase();

        for (i = 0; i < 2; i++){
            //access other player
            if (i == 0){j = 1;}
            else if (i == 1){j = 0;}

            //BidAction
            if (action instanceof BidAction && turn == i && !moveMade[j]){
                powerState.setCurrentBid(((BidAction) action).getBid());
                //change player
                powerState.changeTurn();
                moveMade[i] = true;
            }

            //BuyCityAction
            else if (action instanceof BuyCityAction && turn == i && !moveMade[j]) {
                boolean didithappen = powerState.getGameInventories().get(i).addMyCity(((BuyCityAction) action).getCity());
                if(!didithappen){} //vibrate or flash screen
                powerState.setBoughtCity(((BuyCityAction) action).getCityIndex());
                moveMade[i] = true;
            }

            //BuyResourceAction
            else if(action instanceof BuyResourceAction && turn == i && !moveMade[j]) {
                String type = ((BuyResourceAction) action).getType();
                if (type.equals("coal")) {
                    //determine price of coal
                    price = (((BuyResourceAction) action).getIndex() / 3 + 1);
                    if (powerState.getAvailableResources().coal[((BuyResourceAction) action).getIndex()] && powerState.getGameInventories().get(i).getMoney() >= price) {
                        //make that resource unavailable for further purchase
                        powerState.getAvailableResources().coal[((BuyResourceAction) action).getIndex()] = false;
                        //take money from user
                        powerState.getGameInventories().get(i).setMoney(powerState.getGameInventories().get(i).getMoney() - price);
                        //add coal to inventory
                        powerState.getGameInventories().get(i).setCoal(powerState.getGameInventories().get(i).getCoal() + 1);
                    }
                }
                else if (type.equals("oil")) {
                    price = (((BuyResourceAction) action).getIndex() / 3 + 1);
                    if (powerState.getAvailableResources().oil[((BuyResourceAction) action).getIndex()] && powerState.getGameInventories().get(i).getMoney() >= price) {
                        //make that resource unavailable for further purchase
                        powerState.getAvailableResources().oil[((BuyResourceAction) action).getIndex()] = false;
                        //take money from user
                        powerState.getGameInventories().get(i).setMoney(powerState.getGameInventories().get(i).getMoney() - price);
                        //add oil to inventory
                        powerState.getGameInventories().get(i).setOil(powerState.getGameInventories().get(i).getOil() + 1);
                    }
                }
                else if (type.equals("trash")) {
                    price = (((BuyResourceAction) action).getIndex() / 3 + 1);
                    if (powerState.getAvailableResources().trash[((BuyResourceAction) action).getIndex()] && powerState.getGameInventories().get(i).getMoney() >= price) {
                        //make that resource unavailable for further purchase
                        powerState.getAvailableResources().trash[((BuyResourceAction) action).getIndex()] = false;
                        //take money from user
                        powerState.getGameInventories().get(i).setMoney(powerState.getGameInventories().get(i).getMoney() - price);
                        //add trash to inventory
                        powerState.getGameInventories().get(i).setTrash(powerState.getGameInventories().get(i).getTrash() + 1);
                    }
                }
                else if (type.equals("uranium")){
                    price = (((BuyResourceAction) action).getIndex()/3+1);
                    if (powerState.getAvailableResources().uranium[((BuyResourceAction) action).getIndex()] && powerState.getGameInventories().get(i).getMoney() >= price) {
                        //make that resource unavailable for further purchase
                        powerState.getAvailableResources().uranium[((BuyResourceAction) action).getIndex()] = false;
                        //take money from user
                        powerState.getGameInventories().get(i).setMoney(powerState.getGameInventories().get(i).getMoney() - price);
                        //add uranium to inventory
                        powerState.getGameInventories().get(i).setUranium(powerState.getGameInventories().get(i).getUranium() + 1);
                    }
                }
                moveMade[i] = true;
            }

              //DiscardPowerPlantAction
            else if(action instanceof DiscardPowerPlantAction && turn == i && !moveMade[j]) {
                //discard their last powerplant and cycle old ones through
                //make sure in send action (okbuttonlistener) we are sending a discard action if they have 4 power plants already
                powerState.getGameInventories().get(i).getMyPlants().remove(3);
                moveMade[i] = true;
             }

            /**PassAction
             * A user may pass when they decide not to buy a powerplant, resources, cities
             * tacked onto arraylist of actions when game phases end, Pass action changes phase of game
             **/
            else if(action instanceof PassAction && turn == i && !moveMade[j]) {

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
                    powerState.getGameInventories().get(j).addMyPlants(powerState.getAvailPowerplant().get(powerState.getSelectedPlant()));
                    powerState.removePlant(powerState.getSelectedPlant());
                    powerState.setGamePhase(2);
                }
                else if (phase == 2 ) {
                    //Second player has chosen to pass on buying a power plant, change turn
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
                    //for now, give both players 10 dollars make other resources unavailable
                    powerState.getGameInventories().get(i).setMoney(powerState.getGameInventories().get(i).getMoney()+10);
                    //change turn return to first phase
                    powerState.changeTurn();
                    powerState.setGamePhase(0);
                }

                moveMade[i] = true;
            }

            //SelectPowerPlantAction
            else if(action instanceof SelectPowerPlantAction && turn == i && !moveMade[j]) {
                //user selects a powerplant and presses confirm which will start the bidding process with other player
                //highlight on GUI for humanplayer
                //change player and phase

                if (((SelectPowerPlantAction) action).getNum() == -1) {
                    powerState.setGamePhase(3);
                    powerState.changeTurn();
                }
                else{
                    powerState.setGamePhase(1);
                    powerState.getGameInventories().get(0).setMoney(3000);
                    powerState.setSelectedPlant(((SelectPowerPlantAction) action).getNum());
                    powerState.changeTurn();
                }
                moveMade[i] =  true;
            }

            else{moveMade[i] = false;}
        }//end for loop

        if(moveMade[0] || moveMade[1]){return true;}
        else{return false;}

    }//makeMove
}
