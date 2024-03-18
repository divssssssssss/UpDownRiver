package edu.up.cs301.updown;

//import androidx.cardview.widget.CardView;

import java.util.List;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the counter.
 *
 * @author Christopher Vo, Daniel Le, Divya Pakalapati, Ruth Shepard
 * @author Steven R. Vegdahl
 * @version July 2013, Spring 2024
 */
public class UpDownState extends GameState {

	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;
	private int id;

	// Current value of card on playing deck pile
	private Card[] flippedCard;

	private List<UpDownHumanPlayer> players;

	/*
	Includes both up and down the river.
	currentRound <= 4 = up river
	currentRound > 4 && currentRound < 9 = down river
	currentRound == 9 = count off for final round
	 */
	private int currentRound;

	// Count for final round going from 1-13.
	private int dealerCount;
	private int playerScore;


	public UpDownState() {
		currentRound = 0;
		dealerCount = 0;
		id = 0;
		flippedCard = new Card[21];
	}


	/**
	 * copy constructor; makes a copy of the original object
	 *
	 * @param orig the object from which the copy should be made
	 */
	public UpDownState(UpDownState orig) {
		// set the counter to that of the original
		// this.counter = orig.counter;
		this.players = orig.players;
		this.currentRound = orig.currentRound;
		this.dealerCount = orig.dealerCount;
		this.id = orig.id;
	}

	/**
	 External Citation
	 Date: 13 March 2024
	 Problem: Reviewing how to write a toString method correctly and wanted a way to clearly see
	 each portion of my to string with labels
	 Resources:
	 https://www.javatpoint.com/understanding-toString()-method
	 https://levelup.gitconnected.com/working-of-tostring-and-comparing-concat-and-append
	 -f688a5f8433c
	 Solution: I used the default method name from the javapoint.com example and then referenced the
	 append article within the levelup website to write my toString method
	 */

	// toString method builds a string by appending to clearly see what is being printed
	// it should print the title followed by each variable in Game State on a new line
	// reference the flippedCards comment for more specifics
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Up the River Down the River Game State:");
		stringBuilder.append("\n");
		stringBuilder.append("ID: ").append(id);
		stringBuilder.append("\n");
		stringBuilder.append("Current Round: ").append(currentRound);
		stringBuilder.append("\n");
		stringBuilder.append("Dealer Count-Off: ").append(dealerCount);
		stringBuilder.append("\n");
		stringBuilder.append("Player Score: ").append(playerScore);
		stringBuilder.append("\n");
		stringBuilder.append("Flipped Cards: ");

		// if there are flipped cards (other than if null), iterates through cards
		// appends each + comma and space. removes final comma and space

		if (flippedCard != null) {
			for (int i = 0; i < flippedCard.length; i++) {
				stringBuilder.append(flippedCard[i]).append(", ");
			}
			stringBuilder.setLength(stringBuilder.length() - 2);
		} else {
			stringBuilder.append("No cards flipped yet");
		}
		stringBuilder.append("\n");

		return stringBuilder.toString();
	}


	public List<UpDownHumanPlayer> getPlayers() {
		return players;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public Card[] getFlippedCard() {
		return flippedCard;
	}

	public int getDealerCount() {
		return dealerCount;
	}

	public int getId() {
		return id;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayers(List<UpDownHumanPlayer> players) {
		this.players = players;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public void setFlippedCard(Card[] flippedCard) {
		this.flippedCard = flippedCard;
	}

	public void setDealerCount(int dealerCount) {
		this.dealerCount = dealerCount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public class Card {
		private String suit;
		private int rank;
	}

	public class Player {
		private List<Card> hand;
		private int drinksTaken;

	}

	public void incrementDealerCount() {
		dealerCount++;
	}

}

