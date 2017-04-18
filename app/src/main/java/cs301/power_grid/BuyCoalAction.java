package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class BuyCoalAction extends GameAction {

    int coal;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BuyCoalAction(GamePlayer player, int index) {
        super(player);
        coal = index;
    }

    public int getCoal() {return coal;}
}
