package cs301.power_grid;

import java.io.Serializable;
import java.util.ArrayList;

import game.GamePlayer;
import game.infoMsg.GameState;

/** @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 * Created by Computerz on 2/24/2017.
 *
 * NOTE: Game logic is contained in the PowerGridLocalGame Class under the makeMove method
 */


public class PowerState extends GameState implements Serializable {
    // to satisfy Serializable interface
    private static final long serialVersionUID = 736494716938474L;

    private int phase;
    private int turn;
    private int currentBid;
    private int playerId;
    private int selectedPlant = -1;
    private ArrayList<City> cities = new ArrayList<City>();
    private boolean[] boughtCities = new boolean[20];
    private ArrayList<Powerplant> salePlants = new ArrayList<Powerplant>();
    private ArrayList<Inventory> gameInventories = new ArrayList<Inventory>(); //making an array list because we want to leave the option open for more than 2 players for later code
    private ResourceStore availableResources;

    //Constructor
    public PowerState() {
        phase = 0;
        playerId = 0;
        initiateCityScape();
        initiatePowerPlants();
        scramblePlants(); //at any given moment, Powerplants 0, 1, 2, 3 are for sale
        gameInventories.add(0, new Inventory());
        gameInventories.add(1, new Inventory());
    }

    //Copy Constructor
    public PowerState(PowerState original) {

        int i, j, k, m, n;

        //get the cities up and running
        for(i = 0; i < original.cities.size(); i++) {
            //copies the city over and goes to the trouble of saving each city name
            cities.add(original.cities.get(i));
            cities.get(i).setName(original.cities.get(i).getName());

            //one by one copies all of the neighbors over to the new copy city
//            for(j = 0; j < original.cities.get(i).getNeighborhood().size(); j++) {
//                cities.get(i).addNeighbor(original.cities.get(i).getNeighborhood().get(j));
//            }
//            for(j = 0; j < original.cities.get(i).getCosts().size(); j++) {
//                cities.get(i).addCost(original.cities.get(i).getCosts().get(j));
//            }

        }

        //get the powerplants up and running
        for(k = 0; k< original.salePlants.size(); k++) {
            salePlants.add(original.salePlants.get(k));
            salePlants.get(k).setCost(original.salePlants.get(k).getCost());
            salePlants.get(k).setPtP(original.salePlants.get(k).getPtP());
            salePlants.get(k).setHp(original.salePlants.get(k).getHp());
            salePlants.get(k).setKind(original.salePlants.get(k).getKind());
        }

        //lets save all the player data
        for(m = 0; m < original.gameInventories.size(); m++) {
            gameInventories.add(original.gameInventories.get(m));
            gameInventories.get(m).setCoal(original.gameInventories.get(m).getCoal());
            gameInventories.get(m).setMoney(original.gameInventories.get(m).getMoney());
            gameInventories.get(m).setUranium(original.gameInventories.get(m).getUranium());
            gameInventories.get(m).setOil(original.gameInventories.get(m).getOil());
            gameInventories.get(m).setTrash(original.gameInventories.get(m).getTrash());

            for(n = 0; n < original.gameInventories.get(m).getMyPlants().size(); n++) {
                gameInventories.get(m).addMyPlants(original.gameInventories.get(m).getMyPlants().get(n));
                gameInventories.get(m).addMyCity(original.gameInventories.get(m).getMyCities().get(n));
            }
        }
    }//copyConstructor

    //getters
    public int getTurn() {return turn;}
    public int getSelectedPlant() {return selectedPlant;}
    public boolean[] getBoughtCities() {return boughtCities;}
    public int getPlayerId() {return playerId;}
    public int getGamePhase() {return phase;}
    public ArrayList<City> getAvailCities() {return cities;}
    public ArrayList<Powerplant> getAvailPowerplant() {return salePlants;}
    public ArrayList<Inventory> getGameInventories() {return gameInventories;}
    public ResourceStore getAvailableResources() {return availableResources;}
    public void removePlant(int index){ salePlants.remove(index);}



    //setters

    public void setBoughtCities(boolean[] purchased) {boughtCities = purchased;}
    public void setSelectedPlant(int index){selectedPlant = index;}
    public void setBoughtCity(int index){boughtCities[index] = true;}
    public void setPlayerId(int newPlayerId) {playerId = newPlayerId;}
    public void setGamePhase(int refresh) {phase = refresh;}
    public void setAvailCities(ArrayList<City> newCit) {cities = newCit;}
    public void setSalePlants(ArrayList<Powerplant> newPlant) {salePlants = newPlant;}
    public void setGameInventories(ArrayList<Inventory> newInventory) {gameInventories = newInventory;}
    public void setAvailableResources(ResourceStore newResources) {availableResources = newResources;}
    public void setCurrentBid(int newCurrentBid) {currentBid = newCurrentBid;}
    public void changeTurn(){
        turn = (turn + 1) % 2;
    }

    /**
     * initiateCityScape
     *
     * method that initializes every city on the map
     * and their cost, then adds their neighbors
     */
    public void initiateCityScape() {

        //initialize all the cities with their name
        //note that their neighbors are not involved
        cities.add(new City("Seattle")); //0
        cities.add(new City("San Francisco")); //1
        cities.add(new City("Los Angeles")); //2
        cities.add(new City("Boise")); //3
        cities.add(new City("Missoula")); //4
        cities.add(new City("Phoenix")); //5
        cities.add(new City("Denver")); //6
        cities.add(new City("Omaha")); //7
        cities.add(new City("Oklahoma City")); //8
        cities.add(new City("Dallas")); //9
        cities.add(new City("Houston")); //10
        cities.add(new City("New Orleans")); //11
        cities.add(new City("Memphis")); //12
        cities.add(new City("Saint Louis")); //13
        cities.add(new City("Chicago")); //14
        cities.add(new City("Detroit")); //15
        cities.add(new City("Atlanta")); //16
        cities.add(new City("Miami")); //17
        cities.add(new City("New York City")); //18
        cities.add(new City("Boston")); //19

        //add the appropriate neighbors for each city and the appropriate costs

        //Seattle's neighbors
        cities.get(0).addNeighbor(cities.get(1));
        cities.get(0).addCost(22);
        cities.get(0).addNeighbor(cities.get(3));
        cities.get(0).addCost(8);
        cities.get(0).addNeighbor(cities.get(4));
        cities.get(0).addCost(12);

        //San Francisco's neighbors
        cities.get(1).addNeighbor(cities.get(0));
        cities.get(1).addCost(22);
        cities.get(1).addNeighbor(cities.get(2));
        cities.get(1).addCost(11);
        cities.get(1).addNeighbor(cities.get(3));
        cities.get(1).addCost(15);
        cities.get(1).addNeighbor(cities.get(5));
        cities.get(1).addCost(17);

        //Los Angeles's neighbors
        cities.get(2).addNeighbor(cities.get(1));
        cities.get(2).addCost(11);
        cities.get(2).addNeighbor(cities.get(3));
        cities.get(2).addCost(19);
        cities.get(2).addNeighbor(cities.get(5));
        cities.get(2).addCost(10);

        //Boise's neighbors
        cities.get(3).addNeighbor(cities.get(0));
        cities.get(3).addCost(8);
        cities.get(3).addNeighbor(cities.get(1));
        cities.get(3).addCost(15);
        cities.get(3).addNeighbor(cities.get(2));
        cities.get(3).addCost(19);
        cities.get(3).addNeighbor(cities.get(4));
        cities.get(3).addCost(6);
        cities.get(3).addNeighbor(cities.get(5));
        cities.get(3).addCost(20);
        cities.get(3).addNeighbor(cities.get(6));
        cities.get(3).addCost(16);

        //Missoula's neighbors
        cities.get(4).addNeighbor(cities.get(0));
        cities.get(4).addCost(12);
        cities.get(4).addNeighbor(cities.get(3));
        cities.get(4).addCost(6);
        cities.get(4).addNeighbor(cities.get(7));
        cities.get(4).addCost(17);

        //Phoenix's neighbors
        cities.get(5).addNeighbor(cities.get(1));
        cities.get(5).addCost(17);
        cities.get(5).addNeighbor(cities.get(2));
        cities.get(5).addCost(10);
        cities.get(5).addNeighbor(cities.get(3));
        cities.get(5).addCost(20);
        cities.get(5).addNeighbor(cities.get(6));
        cities.get(5).addCost(14);
        cities.get(5).addNeighbor(cities.get(9));
        cities.get(5).addCost(28);

        //Denver's neighbors
        cities.get(6).addNeighbor(cities.get(3));
        cities.get(6).addCost(16);
        cities.get(6).addNeighbor(cities.get(5));
        cities.get(6).addCost(14);
        cities.get(6).addNeighbor(cities.get(7));
        cities.get(6).addCost(2);
        cities.get(6).addNeighbor(cities.get(8));
        cities.get(6).addCost(11);

        //Omaha's neighbors
        cities.get(7).addNeighbor(cities.get(4));
        cities.get(7).addCost(17);
        cities.get(7).addNeighbor(cities.get(6));
        cities.get(7).addCost(2);
        cities.get(7).addNeighbor(cities.get(8));
        cities.get(7).addCost(13);
        cities.get(7).addNeighbor(cities.get(13));
        cities.get(7).addCost(7);
        cities.get(7).addNeighbor(cities.get(14));
        cities.get(7).addCost(8);

        //Oklahoma City's neighbors
        cities.get(8).addNeighbor(cities.get(6));
        cities.get(8).addCost(11);
        cities.get(8).addNeighbor(cities.get(7));
        cities.get(8).addCost(13);
        cities.get(8).addNeighbor(cities.get(9));
        cities.get(8).addCost(8);
        cities.get(8).addNeighbor(cities.get(12));
        cities.get(8).addCost(5);
        cities.get(8).addNeighbor(cities.get(13));
        cities.get(8).addCost(5);

        //Dallas's neighbors
        cities.get(9).addNeighbor(cities.get(5));
        cities.get(9).addCost(28);
        cities.get(9).addNeighbor(cities.get(8));
        cities.get(9).addCost(8);
        cities.get(9).addNeighbor(cities.get(10));
        cities.get(9).addCost(7);
        cities.get(9).addNeighbor(cities.get(11));
        cities.get(9).addCost(3);
        cities.get(9).addNeighbor(cities.get(12));
        cities.get(9).addCost(7);
        cities.get(9).addNeighbor(cities.get(16));
        cities.get(9).addCost(15);

        //Houston's neighbors
        cities.get(10).addNeighbor(cities.get(9));
        cities.get(10).addCost(7);
        cities.get(10).addNeighbor(cities.get(11));
        cities.get(10).addCost(4);

        //New Orleans' neighbors
        cities.get(11).addNeighbor(cities.get(9));
        cities.get(11).addCost(3);
        cities.get(11).addNeighbor(cities.get(10));
        cities.get(11).addCost(4);
        cities.get(11).addNeighbor(cities.get(16));
        cities.get(11).addCost(10);
        cities.get(11).addNeighbor(cities.get(17));
        cities.get(11).addCost(13);

        //Memphis' neighbors
        cities.get(12).addNeighbor(cities.get(8));
        cities.get(12).addCost(5);
        cities.get(12).addNeighbor(cities.get(9));
        cities.get(12).addCost(7);
        cities.get(12).addNeighbor(cities.get(13));
        cities.get(12).addCost(3);
        cities.get(12).addNeighbor(cities.get(16));
        cities.get(12).addCost(10);

        //St. Louis' neighbors
        cities.get(13).addNeighbor(cities.get(7));
        cities.get(13).addCost(7);
        cities.get(13).addNeighbor(cities.get(8));
        cities.get(13).addCost(5);
        cities.get(13).addNeighbor(cities.get(12));
        cities.get(13).addCost(3);
        cities.get(13).addNeighbor(cities.get(14));
        cities.get(13).addCost(12);
        cities.get(13).addNeighbor(cities.get(16));
        cities.get(13).addCost(12);
        cities.get(13).addNeighbor(cities.get(18));
        cities.get(13).addCost(14);

        //Chicago's neighbors
        cities.get(14).addNeighbor(cities.get(7));
        cities.get(14).addCost(8);
        cities.get(14).addNeighbor(cities.get(13));
        cities.get(14).addCost(12);
        cities.get(14).addNeighbor(cities.get(15));
        cities.get(14).addCost(5);

        //Detroit's neighbors
        cities.get(15).addNeighbor(cities.get(14));
        cities.get(15).addCost(5);
        cities.get(15).addNeighbor(cities.get(18));
        cities.get(15).addCost(5);
        cities.get(15).addNeighbor(cities.get(19));
        cities.get(15).addCost(6);

        //Atlanta's neighbors
        cities.get(16).addNeighbor(cities.get(9));
        cities.get(16).addCost(15);
        cities.get(16).addNeighbor(cities.get(11));
        cities.get(16).addCost(10);
        cities.get(16).addNeighbor(cities.get(12));
        cities.get(16).addCost(10);
        cities.get(16).addNeighbor(cities.get(13));
        cities.get(16).addCost(12);
        cities.get(16).addNeighbor(cities.get(17));
        cities.get(16).addCost(9);
        cities.get(16).addNeighbor(cities.get(18));
        cities.get(16).addCost(20);

        //Miami's neighbors
        cities.get(17).addNeighbor(cities.get(11));
        cities.get(17).addCost(13);
        cities.get(17).addNeighbor(cities.get(16));
        cities.get(17).addCost(9);
        cities.get(17).addNeighbor(cities.get(18));
        cities.get(17).addCost(23);

        //New York City's neighbors
        cities.get(18).addNeighbor(cities.get(13));
        cities.get(18).addCost(14);
        cities.get(18).addNeighbor(cities.get(15));
        cities.get(18).addCost(5);
        cities.get(18).addNeighbor(cities.get(16));
        cities.get(18).addCost(20);
        cities.get(18).addNeighbor(cities.get(17));
        cities.get(18).addCost(23);
        cities.get(18).addNeighbor(cities.get(19));
        cities.get(18).addCost(3);

        //Boston's neighbors
        cities.get(19).addNeighbor(cities.get(15));
        cities.get(19).addCost(6);
        cities.get(19).addNeighbor(cities.get(18));
        cities.get(19).addCost(3);
    }//initiateCityScape

    /**
     * initiatePowerPlants
     *
     * initializes all possible powerPlants in the
     * game and adds them to the master list
     */
    public void initiatePowerPlants() {

        salePlants.add(new Powerplant(3, 2, 1, "Oil")); //0
        salePlants.add(new Powerplant(4, 2, 1, "Coal")); //1
        salePlants.add(new Powerplant(5, 2, 1, "Coal")); //2
        salePlants.add(new Powerplant(6, 1, 1, "Trash"));//3
        salePlants.add(new Powerplant(7, 3, 2, "Oil"));//4
        salePlants.add(new Powerplant(8, 3, 2, "Coal"));//5
        salePlants.add(new Powerplant(9, 1, 1, "Oil"));//6
        salePlants.add(new Powerplant(10, 2, 2, "Coal"));//7
        salePlants.add(new Powerplant(11, 1, 2, "Uranium"));//8
        salePlants.add(new Powerplant(12, 2, 2, "Oil"));//9
        salePlants.add(new Powerplant(13, 0, 1, "Wind"));//10
        salePlants.add(new Powerplant(14, 2, 2, "Trash"));//11
        salePlants.add(new Powerplant(15, 2, 3, "Coal"));//12
        salePlants.add(new Powerplant(16, 2, 3, "Oil"));//13
        salePlants.add(new Powerplant(17, 1, 2, "Uranium"));//14
        salePlants.add(new Powerplant(18, 0, 2, "Wind"));//15
        salePlants.add(new Powerplant(19, 2, 3, "Trash"));//16
        salePlants.add(new Powerplant(20, 3, 5, "Coal"));//17
        salePlants.add(new Powerplant(21, 2, 4, "Coal"));//18
        salePlants.add(new Powerplant(22, 0, 2, "Wind"));//19
        salePlants.add(new Powerplant(23, 1, 3, "Uranium"));//20
        salePlants.add(new Powerplant(24, 2, 4, "Trash"));//21
        salePlants.add(new Powerplant(25, 2, 5, "Coal"));//22
        salePlants.add(new Powerplant(26, 2, 5, "Oil"));//23
        salePlants.add(new Powerplant(27, 0, 3, "Wind"));//24
        salePlants.add(new Powerplant(28, 1, 4, "Uranium"));//25
        salePlants.add(new Powerplant(29, 1, 4, "Oil"));//26
        salePlants.add(new Powerplant(30, 3, 6, "Trash"));//27
        salePlants.add(new Powerplant(31, 3, 6, "Coal"));//28
        salePlants.add(new Powerplant(32, 3, 6, "Oil"));//29
        salePlants.add(new Powerplant(33, 0, 4, "Wind"));//30
        salePlants.add(new Powerplant(34, 1, 5, "Uranium"));//31
        salePlants.add(new Powerplant(35, 1, 5, "Oil"));//32
        salePlants.add(new Powerplant(36, 3, 7, "Coal"));//33
        salePlants.add(new Powerplant(37, 0, 4, "Wind"));//34
        salePlants.add(new Powerplant(38, 3, 7, "Trash"));//35
        salePlants.add(new Powerplant(39, 1, 6, "Uranium"));//36
        salePlants.add(new Powerplant(40, 2, 6, "Oil"));//37
        salePlants.add(new Powerplant(42, 2, 6, "Coal"));//38
        salePlants.add(new Powerplant(44, 0, 5, "Wind"));//39
        salePlants.add(new Powerplant(46, 3, 7, "Coal"));//40
        salePlants.add(new Powerplant(50, 0, 6, "Wind"));//41
    }//initiatePowerPlants

    /**
     * scramblePlants
     *
     * shuffles the powerPlants salePlants
     */
    public void scramblePlants() {

        Powerplant [] shuffledPlants = new Powerplant[42];
        Powerplant temp = new Powerplant();
        int rando;
        int i;

        //for game setup, powerplant 13 (index 10) needs to be in position 8
        temp = salePlants.get(8);
        salePlants.set(8, salePlants.get(10));
        salePlants.set(10, temp);

        //don't shuffle first 9 plants
        for(i = 0; i < 9; i++){
            shuffledPlants[i] = salePlants.get(i);
        }

        //shuffle the rest of the deck
        for(i = 9; i< salePlants.size(); i++) {
            //keep pulling random numbers until we can place the plant
            rando = (int) (Math.random() * (salePlants.size() - 9)) + 9;
            while(shuffledPlants[rando] != null) {
                rando = (int) (Math.random() * (salePlants.size() - 9)) + 9;
            }
            shuffledPlants[rando] = salePlants.get(i);
        }

        //make sure that the salePlants is in the same order as the shuffled deck
        for(i= 0; i < salePlants.size(); i++) {
            salePlants.set(i,shuffledPlants[i]);
        }
    }//scramble plants
}
