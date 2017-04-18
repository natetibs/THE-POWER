package cs301.power_grid;

import java.io.Serializable;

/**@author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 * Created by Computerz on 2/24/2017.
 */
//pretty simple class of powerplants each with 4 different attributes
    //there will be an array list of these in the game
public class Powerplant implements Serializable{
    // to satisfy Serializable interface
    private static final long serialVersionUID = 471138174917169L;

    private int cost;
    private int priceToPower;
    private int housesPowered;
    private String kind;

    public Powerplant(int c, int ptp, int hp, String t){

        cost = c;
        priceToPower = ptp;
        housesPowered = hp;
        kind = t;

    }

    public Powerplant(){
        cost = -1;
        priceToPower = -1;
        housesPowered = -1;
        kind = "";

    }
    public int getCost(){return cost;}
    public int getPtP(){return priceToPower;}
    public int getHp(){return housesPowered;}
    public String getKind(){return kind;}

    public void setCost(int c){cost = c;}
    public void setPtP(int p){priceToPower = p;}
    public void setHp(int h){housesPowered = h;}
    public void setKind(String k){kind = k;}
}