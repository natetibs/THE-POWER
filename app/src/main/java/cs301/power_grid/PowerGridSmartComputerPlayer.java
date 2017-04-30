package cs301.power_grid;

import java.lang.reflect.Array;
import java.util.ArrayList;

import game.GameComputerPlayer;
import game.infoMsg.GameInfo;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class PowerGridSmartComputerPlayer extends GameComputerPlayer{
    PowerState powerState = new PowerState();
    private int lastIndex = 0;
    private int selectNum = -1;
    Inventory inv;
    private int money;
    private boolean first = true;
    private boolean bananas = true;
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
            for(int i = 0; i< 4; i++){
                if(powerState.getAvailPowerplant().get(i).getHp() > tempPowered){
                    tempPowered = powerState.getAvailPowerplant().get(i).getHp();
                    index = i;
                }
            }

            selectNum = index;


            if (phase == 0) {

                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridSmartComputerPlayer.this, selectNum);
                game.sendAction(sppa);

            } else if(phase == 1) {

                if(Math.random() > .5) {
                    int prevBid = powerState.getCurrentBid();
                    BidAction ba = new BidAction(PowerGridSmartComputerPlayer.this, prevBid + 1);
                    game.sendAction(ba);
                }
                else {

                    game.sendAction(new PassAction(PowerGridSmartComputerPlayer.this));
                }

            } else if(phase == 2) {

                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridSmartComputerPlayer.this, selectNum);
                game.sendAction(sppa);

            } else if(phase == 3) {


                if(first) {
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
                    first = false;
                }

                if(actionArray.size() == 0){
                    first = true;
                    game.sendAction(new PassAction(PowerGridSmartComputerPlayer.this));
                }
                else{

                    game.sendAction(actionArray.remove(0));
                }

            } else if(phase == 4) {


                if(first) {
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
                    first = false;
                }

                if(actionArray.size() == 0){
                    first = true;
                    game.sendAction(new PassAction(PowerGridSmartComputerPlayer.this));
                }
                else{

                    game.sendAction(actionArray.remove(0));
                }

            } else if(phase == 5) {


                int tempMoney = inv.getMoney();
                if(bananas) {
                    for (int i = lastIndex; i < 20; i++) {
                        if (tempMoney > 10) {
                            cityActionList.add(new BuyCityAction(PowerGridSmartComputerPlayer.this, powerState.getAvailCities().get(i), i));
                            tempMoney -= 10;
                            lastIndex = i;
                        }
                    }
                    bananas = false;
                }

                if(cityActionList.size() == 0){
                    bananas = true;
                    game.sendAction(new PassAction((PowerGridSmartComputerPlayer.this)));
                }
                else{
                    game.sendAction(cityActionList.remove(0));
                }


            /*Pass*/
            } else if(phase == 6) {


                int tempMoney = inv.getMoney();
                if(bananas) {
                    for (int i = lastIndex; i < 20; i++) {
                        if (tempMoney > 10) {
                            cityActionList.add(new BuyCityAction(PowerGridSmartComputerPlayer.this, powerState.getAvailCities().get(i), i));
                            tempMoney -= 10;
                            lastIndex = i;
                        }
                    }
                    bananas = false;
                }

                if(cityActionList.size() == 0){
                    bananas = true;
                    game.sendAction(new PassAction((PowerGridSmartComputerPlayer.this)));
                }
                else{
                    game.sendAction(cityActionList.remove(0));
                }
            }
        }

    }
}
