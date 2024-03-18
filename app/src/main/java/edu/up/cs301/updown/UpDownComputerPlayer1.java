package edu.up.cs301.updown;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;

/**
 * A computer-version of a counter-player.  Since this is such a simple game,
 * it just sends "+" and "-" commands with equal probability, at an average
 * rate of one per second. 
 *
 * @author Christopher Vo, Daniel Le, Divya Pakalapati, Ruth Shepard
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2013, Spring 2024
 *
 * This will be the Dumb Computer player
 */
public class UpDownComputerPlayer1 extends GameComputerPlayer implements Tickable {
	
    /**
     * Constructor for objects of class CounterComputerPlayer1
     * 
     * @param name
     * 		the player's name
     */
    public UpDownComputerPlayer1(String name) {
        // invoke superclass constructor
        super(name);
        
        // start the timer, ticking 20 times per second
        getTimer().setInterval(50);
        getTimer().start();
    }
    
    /**
     * callback method--game's state has changed
     * 
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {
		// Do nothing, as we ignore all state in deciding our next move. It
		// depends totally on the timer and random numbers.


//		// not sure about this code
//		if (!(info instanceof UpDownState)) {
//			return;
//		}
//
//
//		UpDownState gameState = (UpDownState) info;
//
//		ArrayList<UpDownHumanPlayer> Amountplayers = new ArrayList<UpDownHumanPlayer>(gameState.getPlayers());
//
//		int lowestScore = 10000; // set to high value to change
//
//		for (UpDownHumanPlayer player : Amountplayers) {
//			int playerScore = gameState.getPlayerScore();
//
//			if (playerScore < lowestScore) {
//				lowestScore = playerScore;
//			}
//		}
//
//		// get the round of the game
//		int CurrentRound = gameState.getCurrentRound();
//
//		// add all the point to the person
//		for (int i = 0; i < CurrentRound; i++){
//			UpDownMoveAction.AddPoint Add = new UpDownMoveAction.AddPoint(this);
//			game.sendAction(Add);
//		}
//		UpDownMoveAction.SubmitPoints submit = new UpDownMoveAction.SubmitPoints(this);
//		game.sendAction(submit);

	}
	
	/**
	 * callback method: the timer ticked
	 */
	protected void timerTicked() {
		// 5% of the time, increment or decrement the counter
		if (Math.random() >= 0.05) return; // do nothing 95% of the time

		// "flip a coin" to determine whether to increment or decrement
		boolean move = Math.random() >= 0.5;
		
		// send the move-action to the game
		game.sendAction(new UpDownMoveAction(this, move));
	}
}
