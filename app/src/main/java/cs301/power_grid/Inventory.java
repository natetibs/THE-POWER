package cs301.power_grid;

import java.io.Serializable;
import java.util.ArrayList;

/**@author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 * Created by Computerz on 2/24/2017.
 */

public class Inventory implements Serializable{
    // to satisfy Serializable interface
    private static final long serialVersionUID = 448179182969164L;

    private int money;
    private int coal;
    private int oil;
    private int uranium;
    private int trash;
    private ArrayList<Powerplant> myPlants = new ArrayList<Powerplant>();
    private ArrayList<City> myCities = new ArrayList<City>();

    public Inventory() {
        //initial game setup
        money = 50;
        coal = 0;
        oil = 0;
        uranium = 0;
        trash = 0;
    }

    public int getMoney() {return money;}
    public int getCoal() {return coal;}
    public int getOil() {return oil;}
    public int getUranium() {return uranium;}
    public int getTrash() {return trash;}
    public ArrayList<Powerplant> getMyPlants() {return myPlants;}
    public ArrayList<City> getMyCities() {return myCities;}

    public void setMoney(int restock) {money = restock;}
    public void setCoal(int restock) {coal = restock;}
    public void setOil(int restock) {oil = restock;}
    public void setUranium(int restock) {uranium = restock;}
    public void setTrash(int restock) {trash = restock;}

    public boolean addMyPlants(Powerplant plantation) {
        if(myPlants.size() >= 4) {
            return false; //failure, player may only have 4 plants
        }

        myPlants.add(plantation);
        return true; //success
    }

    public boolean addMyCity(City mcCity) {
        int i, j, k;

        if(myCities.size() == 0) {
            myCities.add(mcCity);
            return true;
        }

        for(k = 0; k < myCities.size(); k++) {
            if(mcCity.getName().equals(myCities.get(k).getName())) {
                return false; //mission failure because you already own that city
            }

        }
        //has to check all the neighboring cities to make sure it is a success
        //goes through every neighbor in every city that you have
        for(j = 0; j < myCities.size(); j++) {
            for (i = 0; i < myCities.get(j).getNeighborhood().size(); i++) {
                if (myCities.get(j).getNeighborhood().get(i).getName().equals(mcCity.getName())) {
                    myCities.add(mcCity);
                    return true;
                }
            }
        }
        return false; //mission fails because the city you wish to add is not in the neighborhood
    }
}