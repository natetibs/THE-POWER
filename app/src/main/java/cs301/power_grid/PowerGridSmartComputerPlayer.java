package cs301.power_grid;

import java.util.ArrayList;
import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * PowerGridSmartComputerPlayer
 *
 * Hedonistic AI
 * Finds Power Plant that powers the most cities, buys the resources necessary and as many cities as it can afford
 *
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */

public class PowerGridSmartComputerPlayer extends GameComputerPlayer{

    //instance variables
    PowerState powerState = new PowerState();
    private int lastIndex = 0;
    private int selectNum = -1;
    Inventory inv;
    private boolean firstPlant = true;
    private boolean firstCity = true;
    ArrayList<BuyCityAction> cityActionList = new ArrayList<BuyCityAction>();
    ArrayList<BuyResourceAction> actionArray = new ArrayList<BuyResourceAction>();

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
            int tempPowered = -1;
            int index = 0;
            //look at all powerplants and see which one can power the most
            for(int i = 0; i< 4; i++){
                if(powerState.getAvailPowerplant().get(i).getHp() > tempPowered){
                    tempPowered = powerState.getAvailPowerplant().get(i).getHp();
                    index = i;
                }
            }
            //attempt to buy the power plant that can power the most cities
            selectNum = index;

            //depending on the phase, send the appropriate action
            if (phase == 0) {
                //select the power plant that we wanted earlier
                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridSmartComputerPlayer.this, selectNum);
                game.sendAction(sppa);
            }
            else if(phase == 1) {
                //bid sometimes, always bid 1 more than previous bid
                if(Math.random() > .5) {
                    int prevBid = powerState.getCurrentBid();
                    BidAction ba = new BidAction(PowerGridSmartComputerPlayer.this, prevBid + 1);
                    game.sendAction(ba);
                }
                else {
                    game.sendAction(new PassAction(PowerGridSmartComputerPlayer.this));
                }
            }
            else if(phase == 2) {
                //select the power plant we wanted earlier, don't do a new calculation though
                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridSmartComputerPlayer.this, selectNum);
                game.sendAction(sppa);
            }
            else if(phase == 3) {
                //look through power plants and see how many of which resource are needed to power all plants
                if(firstPlant) {
                    for (int i = 0; i < inv.getMyPlants().size(); i++) {
                        if (inv.getMyPlants().get(i).getKind().equals("Oil")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "oil"));
                            }
                        }
                        if (inv.getMyPlants().get(i).getKind().equals("Coal")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "coal"));
                            }
                        }
                        if (inv.getMyPlants().get(i).getKind().equals("Uranium")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "uranium"));
                            }
                        }
                        if (inv.getMyPlants().get(i).getKind().equals("Trash")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "trash"));
                            }
                        }
                    }
                    firstPlant = false;
                }
                //send resource actions until arrayList is empty, then send pass to end turn
                if(actionArray.size() == 0){
                    firstPlant = true;
                    game.sendAction(new PassAction(PowerGridSmartComputerPlayer.this));
                }
                else{
                    game.sendAction(actionArray.remove(0));
                }
            } else if(phase == 4) {
                //look through power plants and see how many of which resource are needed to power all plants
                if(firstPlant) {
                    for (int i = 0; i < inv.getMyPlants().size(); i++) {
                        if (inv.getMyPlants().get(i).getKind().equals("Oil")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "oil"));
                            }
                        }
                        if (inv.getMyPlants().get(i).getKind().equals("Coal")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "coal"));
                            }
                        }
                        if (inv.getMyPlants().get(i).getKind().equals("Uranium")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "uranium"));
                            }
                        }
                        if (inv.getMyPlants().get(i).getKind().equals("Trash")) {
                            for (int j = 0; j < inv.getMyPlants().get(i).getPtP(); j++) {
                                actionArray.add(new BuyResourceAction(PowerGridSmartComputerPlayer.this, j, "trash"));
                            }
                        }
                    }
                    firstPlant = false;
                }
                //send resource actions until arrayList is empty, then send pass to end turn
                if(actionArray.size() == 0){
                    firstPlant = true;
                    game.sendAction(new PassAction(PowerGridSmartComputerPlayer.this));
                }
                else{

                    game.sendAction(actionArray.remove(0));
                }
            } else if(phase == 5) {
                //buy as many cities as possible
                int tempMoney = inv.getMoney();
                if(firstCity) {
                    for (int i = lastIndex; i < 20; i++) {
                        if (tempMoney > 10) {
                            cityActionList.add(new BuyCityAction(PowerGridSmartComputerPlayer.this, powerState.getAvailCities().get(i), i));
                            tempMoney -= 10;
                            lastIndex = i;
                        }
                    }
                    firstCity = false;
                }
                //send buy city actions until arrayList is empty, then send pass to end turn
                if(cityActionList.size() == 0){
                    firstCity = true;
                    game.sendAction(new PassAction((PowerGridSmartComputerPlayer.this)));
                }
                else{
                    game.sendAction(cityActionList.remove(0));
                }

            } else if(phase == 6) {
                //buy as many cities as possible
                int tempMoney = inv.getMoney();
                if(firstCity) {
                    for (int i = lastIndex; i < 20; i++) {
                        if (tempMoney > 10) {
                            cityActionList.add(new BuyCityAction(PowerGridSmartComputerPlayer.this, powerState.getAvailCities().get(i), i));
                            tempMoney -= 10;
                            lastIndex = i;
                        }
                    }
                    firstCity = false;
                }
                //buy as many cities as possible
                if(cityActionList.size() == 0){
                    firstCity = true;
                    game.sendAction(new PassAction((PowerGridSmartComputerPlayer.this)));
                }
                else{
                    game.sendAction(cityActionList.remove(0));
                }
            }
        }
    }
}
