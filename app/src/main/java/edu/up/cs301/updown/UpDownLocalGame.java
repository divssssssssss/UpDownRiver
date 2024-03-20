package edu.up.cs301.updown;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;

import android.text.Layout;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that represents the state of a game. In our counter game, the only
 * relevant piece of information is the value of the game's counter. The
 * CounterState object is therefore very simple.
 *
 * @author Christopher Vo, Daniel Le, Divya Pakalapati, Ruth Shepard
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013, Spring 2024
 */
public class UpDownLocalGame extends LocalGame {

	// When a counter game is played, any number of players. The first player
	// is trying to get the counter value to TARGET_MAGNITUDE; the second player,
	// if present, is trying to get the counter to -TARGET_MAGNITUDE. The
	// remaining players are neither winners nor losers, but can interfere by
	// modifying the counter.
	public static final int TARGET_MAGNITUDE = 10;

	// the game's state
	private UpDownState gameState;

	/**
	 * can this player move
	 *
	 * @return
	 * 		true, because all player are always allowed to move at all times,
	 * 		as this is a fully asynchronous game
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		return true;
	}

	/**
	 * This ctor should be called when a new counter game is started
	 */
	public UpDownLocalGame(GameState state) {
		// initialize the game state, with the counter value starting at 0
		if (! (state instanceof UpDownState)) {
			state = new UpDownState();
		}
		this.gameState = (UpDownState)state;
		super.state = state;
	}

	/**
	 * The only type of GameAction that should be sent is CounterMoveAction
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		Log.i("action", action.getClass().toString());

		// this was here before I got here
//		if (action instanceof UpDownMoveAction) {
//
//			// cast so that we Java knows it's a CounterMoveAction
//			UpDownMoveAction cma = (UpDownMoveAction)action;
//
//			// Update the counter values based upon the action
//			//int result = gameState.getCounter() + (cma.isPlus() ? 1 : -1);
//			//gameState.setCounter(result);
//
//			// denote that this was a legal/successful move
//			return true;
//		}

		// get a new card to be displayed by the deck
		if (action instanceof CardFlipped) {
			 // counts the amount dealer has played for the final reshuffle
			 if (gameState.getCurrentRound() >= 8) {
				 gameState.setDealerCount(gameState.getDealerCount() + 1);
			 }

			 ArrayList<UpDownState.Card> CurrentCard = gameState.getFlippedCard();

			 // want to be able to shuffle the deck of cards and set it to it (not correct yet)
			 Random random = new Random();
			 int randomIndex = random.nextInt(CurrentCard.size());

			 // Swap the first card with the randomly selected card
			 UpDownState.Card temp = CurrentCard.get(0);
			 CurrentCard.set(randomIndex, temp);
			 CurrentCard.set(0, CurrentCard.get(randomIndex)); // set the first element to a random card in the deck

			 // Set the shuffled first card back to the game state by creating a new deck containing one card to be shown
			 ArrayList<UpDownState.Card> shuffledCard = new ArrayList<>(1);

			 shuffledCard.set(0, CurrentCard.get(0));

			 gameState.setFlippedCard(shuffledCard);

			// add 1 to the current round of the game
			gameState.setCurrentRound(gameState.getCurrentRound() + 1);

			return true;
		}
		 // this will get the user to go back to the main menu *(not sure if it works yet)*
		 else if (action instanceof ReturnHome) {
			/**
				gonna try to implement this method
			 */
			 return true;
		 }
		 // this will reshuffle the whole deck of cards
		 else if (action instanceof ShuffleCards) {
			 ArrayList<UpDownState.Card> deck = gameState.getFlippedCard();

			// make sure deck is bigger then 1 card for the need to shuffle
			 if (deck.size() > 1) {
				 Random random = new Random();

				 // Iterate through each card in the deck
				 for (int i = 0; i < deck.size(); i++) {
					 // Generate a random index within the range of the deck length
					 int randomIndex = random.nextInt(deck.size());

					 // Swap the current card with a randomly selected card in the deck
					 UpDownState.Card temp = deck.get(i);
					 deck.set(i, deck.get(randomIndex));
					 deck.set(randomIndex, temp);
				 }
				 // Update the game state with the shuffled deck
				 gameState.setFlippedCard(deck);
			 }
			 return true;
		 }
		 // add 1 point for down the river if button is clicked
		 else if (action instanceof UpDownMoveAction.AddPoint) {
			 gameState.setPlayerScore(gameState.getPlayerScore() + 1);
			 return true;
		 }
		 // subtract 1 point for down the river if button is clicked
		 else if (action instanceof UpDownMoveAction.SubtractPoint) {
			 gameState.setPlayerScore(gameState.getPlayerScore() - 1);
			 return true;
		 }
		 // submit the points to players for Down the River when button is clicked and also -1 to current round
		 else if (action instanceof UpDownMoveAction.SubmitPoints) {
			 gameState.setCurrentRound(gameState.getCurrentRound() - 1);
			 return true;
		}

		return false;
	}//makeMove

	/**
	 * send the updated state to a given player
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
		// this is a perfect-information game, so we'll make a
		// complete copy of the state to send to the player
		p.sendInfo(new UpDownState(this.gameState));

	}//sendUpdatedSate

	/**
	 * Check if the game is over. It is over, return a string that tells
	 * who the winner(s), if any, are. If the game is not over, return null;
	 *
	 * @return
	 * 		a message that tells who has won the game, or null if the
	 * 		game is not over
	 */
	@Override
	protected String checkIfGameOver() {

		// get the value of the counter
		//int counterVal = this.gameState.getCounter();

		//if (counterVal >= TARGET_MAGNITUDE) {
			// counter has reached target magnitude, so return message that
			// player 0 has won.
			// return playerNames[0]+" has won.";
		//}
		//else if (counterVal <= -TARGET_MAGNITUDE) {
			// counter has reached negative of target magnitude; if there
			// is a second player, return message that this player has won,
			// otherwise that the first player has lost
			//if (playerNames.length >= 2) {
				//return playerNames[1] + " has won.";
		return null;
			} //else {
				//return playerNames[0] + " has lost.";
				//}
			//}//else{
				// game is still between the two limit: return null, as the game
				// is not yet over
				//return null;

}// class CounterLocalGame


