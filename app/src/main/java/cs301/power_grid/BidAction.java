package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;
/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */

public class BidAction extends GameAction {

    private int bid;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BidAction(GamePlayer player, int initBid) {
        super(player);
        bid = initBid;
    }

    public int getBid(){return bid;}
}
