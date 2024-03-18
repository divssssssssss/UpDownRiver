package edu.up.cs301.updown;

import edu.up.cs301.GameFramework.Game;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.actionMessage.GameAction;

/**
 * A CounterMoveAction is an action that is a "move" the game: either increasing
 * or decreasing the counter value.
 *
 * @author Christopher Vo, Daniel Le, Divya Pakalapati, Ruth Shepard
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2012, Spring 2024
 */
public class UpDownMoveAction extends GameAction {
	
	// to satisfy the serializable interface
	private static final long serialVersionUID = 28062013L;

	//whether this move is a plus (true) or minus (false)
	private boolean isPlus;
	
	/**
	 * Constructor for the CounterMoveAction class.
	 * 
	 * @param player
	 *            the player making the move
	 * @param isPlus
	 *            value to initialize this.isPlus
	 */
	public UpDownMoveAction(GamePlayer player, boolean isPlus) {
		super(player);
		this.isPlus = isPlus;
	}
	
	/**
	 * getter method, to tell whether the move is a "plus"
	 * 
	 * @return
	 * 		a boolean that tells whether this move is a "plus"
	 */
	public boolean isPlus() {
		return isPlus;

	}

	public static class AddPoint extends GameAction {

		/**
		 * constructor for GameAction
		 *
		 * @param player the player who created the action
		 */
		public AddPoint(GamePlayer player) {
			super(player);
		}
	}

	public static class SubtractPoint extends GameAction {

		/**
		 * constructor for GameAction
		 *
		 * @param player the player who created the action
		 */
		public SubtractPoint(GamePlayer player) {
			super(player);
		}
	}

	public static class SubmitPoints extends GameAction {

		/**
		 * constructor for GameAction
		 *
		 * @param player the player who created the action
		 */
		public SubmitPoints(GamePlayer player) {
			super(player);
		}
	}
}//class CounterMoveAction
