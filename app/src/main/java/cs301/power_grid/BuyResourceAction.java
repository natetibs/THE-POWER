package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * BuyResourceAction
 *
 * stores type and index of resource bought in phase 3 or 4
 *
 * Created by Computerz on 4/18/2017.
 */

public class BuyResourceAction  extends GameAction {

    int index;
    String type;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BuyResourceAction(GamePlayer player, int i,String resourceType) {
        super(player);
        index = i;
        type = resourceType;
    }

    public int getIndex() {return index;}
    public String getType() {return type;}
}

