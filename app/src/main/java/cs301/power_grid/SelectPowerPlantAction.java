package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;

/** SelectPowerPlantAction
 *
 *  player selects a power plant in phase 0 or 1
 *  stores index of power plant in available power plants of power state
 *
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */

public class SelectPowerPlantAction extends GameAction {
    private int num;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectPowerPlantAction(GamePlayer player, int initNum) {
        super(player);
        num = initNum;
    }

    public int getNum(){return num;}
}