package cs301.power_grid;
import game.Game;
import game.GamePlayer;
import game.actionMsg.GameAction;
/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class BuyUraniumAction extends GameAction {

    private int uranium;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BuyUraniumAction(GamePlayer player, int initUranium) {
        super(player);
        uranium = initUranium;
    }
    public int getUranium() {return uranium;}
}
