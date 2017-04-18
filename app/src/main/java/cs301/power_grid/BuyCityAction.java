package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;
/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class BuyCityAction extends GameAction {

    private City city;
    private int cityIndex;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BuyCityAction(GamePlayer player,City initCity, int index) {
        super(player);
        city = initCity;
        cityIndex = index;
    }
    public City getCity(){return city;}
    public int getCityIndex(){return cityIndex;}
}
