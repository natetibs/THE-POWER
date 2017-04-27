package cs301.power_grid;

import java.io.Serializable;
import java.util.ArrayList;


/**@author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 * Created by Computerz on 2/24/2017.
 */

public class City implements Serializable{
    // to satisfy Serializable interface
    private static final long serialVersionUID = 986136194786931L;
    private int index;
    private String name;
    private ArrayList<Integer> neighborhood = new ArrayList<Integer>(); //contains a list of all the neighbors of cities this city has
    private ArrayList<Integer> costs = new ArrayList<Integer>();


    public City(String myName){
        name = myName;
    }

    public City(String myName, int myIndex) {
        name = myName;
        index = myIndex;
    }

    public City(String myName, ArrayList<Integer> community, ArrayList<Integer> prices){

        name = myName;

        for(int i = 0; i < community.size(); i++){
            neighborhood.add(i, community.get(i));
        }
        for(int i = 0; i < prices.size(); i++){
            costs.add(i, prices.get(i));
        }
    }

    public City(){
        name = "Nathan";
    }

    public ArrayList<Integer> getNeighborhood(){return neighborhood;}
    public String getName(){return name;}
    public ArrayList<Integer> getCosts(){return costs;}

    public void addNeighbor(int n){neighborhood.add(n);} //add neighbors to the list
    public void setName(String n){name = n;} //cities are identified by name
    public void setIndex(int n){index = n;}
    public void addCost(int n){costs.add(n);}

    public boolean isNeighbor(int possibleNeighbor){
        for(int i = 0; i < neighborhood.size(); i++){
            if(neighborhood.get(i) == possibleNeighbor) return true;
        }
        return false;
    }

    public boolean containsNeighbor(ArrayList<Integer> possibleNeighbors) {
        for (int i = 0; i < possibleNeighbors.size(); i++) {
            if (isNeighbor(possibleNeighbors.get(i))) return true;
        }
        return false;
    }

    public ArrayList<City> returnNeighbors(ArrayList<City> neighbors)
    {
        ArrayList<City> utopia = new ArrayList<City>();
        for(int i = 0; i < neighbors.size(); i++){
            if(isNeighbor(neighbors.get(i).getIndex())){
                utopia.add(neighbors.get(i));
            }

         }
        return utopia;
    }

    public int returnNeighborByIndex(int index){
        for(int i = 0; i < neighborhood.size(); i++){
            if(index == neighborhood.get(i)) return i;
        }
        return -1;
    }

    public int getIndex() {
        return index;
    }

}

