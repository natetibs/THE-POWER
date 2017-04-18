package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;

/**
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