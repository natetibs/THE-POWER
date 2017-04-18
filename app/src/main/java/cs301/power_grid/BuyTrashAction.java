package cs301.power_grid;
import game.GamePlayer;
import game.actionMsg.GameAction;
/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class BuyTrashAction extends GameAction {

    private int trash;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BuyTrashAction(GamePlayer player, int initTrash) {
        super(player);
        trash = initTrash;
    }
    public int getTrash() {return trash;}
}
