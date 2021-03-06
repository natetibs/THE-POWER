package cs301.power_grid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Inventory
 *
 * all of a player's possessions in the game
 * includes money, coal, oil, uranium, trash, powerPlants, & cities
 *
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 * Created by Computerz (lol nathan's computer) on 2/24/2017.
 */

public class Inventory implements Serializable{
    // to satisfy Serializable interface
    private static final long serialVersionUID = 448179182969164L;

    //instance variables
    private int money;
    private int coal;
    private int oil;
    private int uranium;
    private int trash;
    private ArrayList<Powerplant> myPlants = new ArrayList<Powerplant>();
    private ArrayList<City> myCities = new ArrayList<City>();

    //constructor
    public Inventory() {
        //initial game setup
        money = 50;
        coal = 0;
        oil = 0;
        uranium = 0;
        trash = 0;
    }
    //getters and setters
    public int getMoney() {return money;}
    public int getCoal() {return coal;}
    public int getOil() {return oil;}
    public int getUranium() {return uranium;}
    public int getTrash() {return trash;}
    public ArrayList<Powerplant> getMyPlants() {return myPlants;}
    public ArrayList<City> getMyCities() {return myCities;}

    public void setMoney(int restock) {money = restock;}
    public void addMoney(int bonus) {money += bonus;}
    public void setCoal(int restock) {coal = restock;}
    public void setOil(int restock) {oil = restock;}
    public void setUranium(int restock) {uranium = restock;}
    public void setTrash(int restock) {trash = restock;}

    //adds a power plant to a users inventory
    public boolean addMyPlants(Powerplant plantation) {
        boolean notAdded = true;
        if(myPlants.size() >= 4) {
          myPlants.remove(lowestCost());
          myPlants.add(plantation);
          return false; //false, a power plant was sacrificed
        }

        for(int i = 0; i < myPlants.size(); i++) {
            if (plantation.getCost() > myPlants.get(i).getCost()) {
                myPlants.add(i, plantation);
                notAdded = false;
                break;
            }
        }
        if(notAdded){
            myPlants.add(plantation);
        }

        return true; //success
    }

    //adds a city to a users inventory
    public boolean addMyCity(City mcCity) {
        int k;

        if(myCities.size() == 0) {
            myCities.add(mcCity);
            return true;
        }

        for(k = 0; k < myCities.size(); k++) {
            if(mcCity.getName().equals(myCities.get(k).getName())) {
                return false; //mission failure because you already own that city
            }

        }
        myCities.add(mcCity);
        return true; //mission fails because the city you wish to add is not in the neighborhood
    }

    //finds lowest costing power plant in an inventory
    public int lowestCost() {
        double minCost = 100000;
        int index = 0;

        for (int i = 0; i < myPlants.size(); i++) {
            if (myPlants.get(i).getCost() < minCost) {
                minCost = myPlants.get(i).getCost();
                index = i;
            }
        }
        return index;
    }
}