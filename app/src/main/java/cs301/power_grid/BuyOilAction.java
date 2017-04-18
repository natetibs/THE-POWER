package cs301.power_grid;
import game.GamePlayer;
import game.actionMsg.GameAction;
/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class BuyOilAction extends GameAction {

    private int oil;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BuyOilAction(GamePlayer player, int initOil) {
        super(player);
        oil = initOil;
    }
    public int getOil() {return oil;}
}
