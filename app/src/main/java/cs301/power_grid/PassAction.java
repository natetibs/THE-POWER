package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * PassAction
 *
 * players can pass if they are finished with their turn or don't want to purchase anything
 *
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */

public class PassAction extends GameAction{
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PassAction(GamePlayer player) {
        super(player);
    }
}
