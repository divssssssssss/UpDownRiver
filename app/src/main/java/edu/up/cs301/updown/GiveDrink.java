package edu.up.cs301.updown;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

public class GiveDrink extends GameAction {
    private UpDownState.Player giver;
    private UpDownState.Player receiver;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public GiveDrink(GamePlayer player, UpDownState.Player giver, UpDownState.Player receiver) {
        super(player);
        this.giver = giver;
        this.receiver = receiver;
    }
    /**
     * Get the giver player of the drink action
     *
     * @return the giver player
     */
    public UpDownState.Player getGiver() {
        return giver;
    }

    /**
     * Get the receiver player of the drink action
     *
     * @return the receiver player
     */
    public UpDownState.Player getReceiver() {
        return receiver;
    }


}


