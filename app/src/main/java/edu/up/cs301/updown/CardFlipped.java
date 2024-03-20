// @author Chris, Daniel, Divya, Ruth
package edu.up.cs301.updown;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class CardFlipped extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public CardFlipped(GamePlayer player) {
        super(player);
    }
}
