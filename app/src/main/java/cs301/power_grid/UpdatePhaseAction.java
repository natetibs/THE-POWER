package cs301.power_grid;

import game.GamePlayer;
import game.actionMsg.GameAction;

/**
 * Created by douvillionaire on 4/16/17.
 */

public class UpdatePhaseAction extends GameAction {
    private int phase;
    public UpdatePhaseAction(GamePlayer player, int tempPhase) {
        super(player);
        phase = tempPhase;
    }
}
