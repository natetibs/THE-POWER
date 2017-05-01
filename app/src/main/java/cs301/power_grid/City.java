package cs301.power_grid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * City
 *
 * used in array list of available cities in power state
 * users may buy cities in phase 5 or 6 of the game
 *
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 * Created by Computerz on 2/24/2017.
 */

public class City implements Serializable{
    // to satisfy Serializable interface
    private static final long serialVersionUID = 986136194786931L;
    //instance variables
    private int index;
    private String name;
    private ArrayList<Integer> neighborhood = new ArrayList<Integer>(); //contains a list of all the neighbors of cities this city has
    private ArrayList<Integer> costs = new ArrayList<Integer>();

    //constructor
    public City(String myName, int myIndex) {
        name = myName;
        index = myIndex;
    }

    //constructor
    public City(){
        name = "Nathan";
    }

    //getters and setters
    public ArrayList<Integer> getNeighborhood(){return neighborhood;}
    public String getName(){return name;}
    public ArrayList<Integer> getCosts(){return costs;}
    public int getIndex() {
        return index;
    }

    public void addNeighbor(int n){neighborhood.add(n);} //add neighbors to the list
    public void setName(String n){name = n;} //cities are identified by name
    public void setIndex(int n){index = n;}
    public void addCost(int n){costs.add(n);}

    //used to check if a city has a certain neighbor
    public boolean isNeighbor(int possibleNeighbor){
        for(int i = 0; i < neighborhood.size(); i++){
            if(neighborhood.get(i) == possibleNeighbor) return true;
        }
        return false;
    }
    //used to see if a city has a certain neighborhood
    public boolean containsNeighbor(ArrayList<Integer> possibleNeighbors) {
        for (int i = 0; i < possibleNeighbors.size(); i++) {
            if (isNeighbor(possibleNeighbors.get(i))) return true;
        }
        return false;
    }
    //returns a cities neighbors
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
    //returns indexes of cities neighbors
    public int returnNeighborByIndex(int index){
        for(int i = 0; i < neighborhood.size(); i++){
            if(index == neighborhood.get(i)) return i;
        }
        return -1;
    }
}

