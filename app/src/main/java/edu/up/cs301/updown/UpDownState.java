package edu.up.cs301.updown;

//import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
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
	// unique identifier for the game state
	private int id;
	// array to keep track of the cards that have been flipped by the dealer
	private Card[] flippedCard;

	// list to store player IDs who are currently participating in game
	private ArrayList<Integer> players = new ArrayList<>();

	/*
	includes both up and down the river
	currentRound <= 4 = up river
	currentRound > 4 && currentRound < 9 = down river
	currentRound == 9 = count off for final round
	 */
	private int currentRound;

	// count for final round going from 1-13
	private int dealerCount;
	// keep track of each player's score
	private int playerScore;


	public UpDownState() {
		currentRound = 0;
		dealerCount = 0;
		id = 0;
		flippedCard = new Card[21]; // initializing the array to track flipped cards
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
			stringBuilder.setLength(stringBuilder.length() - 2); // Remove the last comma and space
		} else {
			stringBuilder.append("No cards flipped yet");
		}
		stringBuilder.append("\n");

		return stringBuilder.toString();

		/*
		ALTERNATE SIMPLE TOSTRING METHOD
		return "Up the River Down the River Game State:" +
        "\nID: " + id +
        "\nCurrent Round: " + currentRound +
        "\nDealer Count-Off: " + dealerCount +
        "\nPlayer Score: " + playerScore +
        "\nFlipped Cards: ";
		 */
	}

	/**
	 * copy constructor; makes a copy of the original object
	 *
	 * @param orig the object from which the copy should be made
	 */
	public UpDownState(UpDownState orig) {
		// Copy game state variables from the original object
		this.players = orig.players;
		this.currentRound = orig.currentRound;
		this.dealerCount = orig.dealerCount;
		this.id = orig.id;
	}

	// Getter methods to access game state variables
	public ArrayList<Integer> getPlayers() {
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

	// Setter methods to modify game state variables
	public void setPlayers(ArrayList<Integer> players) {
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

	// Inner class to represent a card that has a suit and rank
	public class Card {
		private String suit;
		private int rank;
	}
	// Inner class to represent a player that has a card and how many drinks they have taken
	public class Player {
		private ArrayList<Card> hand;
		private int drinksTaken;

	}
	// Method to increment the dealer count when the dealer counts from 1-13
	public void incrementDealerCount() {
		dealerCount++;
	}

}

