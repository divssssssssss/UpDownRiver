// @author Chris, Daniel, Divya, Ruth
package edu.up.cs301.updown;

//import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Random;

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
	// arraylist to keep track of the cards that have been flipped by the dealer
	private ArrayList<Card> flippedCard;

	// list to store player IDs who are currently participating in game
	private ArrayList<Player> players = new ArrayList<Player>();

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
	private int player1Score;
	private int player2Score;


	public UpDownState() {
		currentRound = 0;
		dealerCount = 0;
		id = 0;
		flippedCard = new ArrayList<>(); // initializing the array to track flipped cards
		players.add(new Player(1));
		players.add(new Player(2));

		// add 52 cards
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		for(String suit: suits) {
			for(int rank = 1; rank <= 13; rank++) {
				Card card = new Card(suit, rank);
				flippedCard.add(card);
			}
		}
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
		stringBuilder.append("Player Score: ").append(player1Score);
		stringBuilder.append("\n");
		stringBuilder.append("Player Score: ").append(player2Score);
		stringBuilder.append("\n");
		stringBuilder.append("Flipped Cards: ");

		// if there are flipped cards (other than if null), iterates through cards
		// appends each + comma and space. removes final comma and space
		if (flippedCard != null && !flippedCard.isEmpty()) {
			for (Card card : flippedCard) { //for (int i = 0; i < flippedCard.length; i++) {
				stringBuilder.append(card).append(", ");
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
	// Copy constructor for UpDownState
	public UpDownState(UpDownState orig) {
		this.currentRound = orig.currentRound;
		this.dealerCount = orig.dealerCount;
		this.id = orig.id;
		this.player1Score = orig.player1Score;
		this.player2Score = orig.player2Score;

		// Copy players
		this.players = new ArrayList<>();
		for (Player originalPlayer : orig.players) {
			this.players.add(new Player(originalPlayer));
		}

		// Copy flipped cards
		this.flippedCard = new ArrayList<>();
		for (Card card : orig.flippedCard) {
			this.flippedCard.add(new Card(card));
		}
	}

	// Getter methods to access game state variables
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public ArrayList<Card> getFlippedCard() {
		return flippedCard;
	}

	public int getDealerCount() {
		return dealerCount;
	}

	public int getId() {
		return id;
	}

	public int getPlayer1Score() {
		return player1Score;
	}
	public int getPlayer2Score() {
		return player2Score;
	}

	// Setter methods to modify game state variables
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public void setFlippedCard(ArrayList<Card> flippedCard) {
		this.flippedCard = flippedCard;
	}

	public void setDealerCount(int dealerCount) {
		this.dealerCount = dealerCount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}
	public void setPlayer2Score(int player2Score) {
		this.player1Score = player2Score;
	}

	// Inner class to represent a card that has a suit and rank
	public class Card {
		private String suit;
		private int rank;

		// Copy constructor for the Card class
		public Card(Card orig) {
			this.suit = orig.suit;
			this.rank = orig.rank;
		}

		public Card(String suit, int rank) {
			this.suit = suit;
			this.rank = rank;
		}
 	}
	// Inner class to represent a player that has a card and how many drinks they have taken
	public class Player {
		private ArrayList<Card> hand;
		private int drinksTaken;

		private int id;
		public Player(int id) {
			hand = new ArrayList<>();
			drinksTaken = 0;
			this.id = id;
		}

		// Copy constructor for the Player class
		public Player(Player orig) {
			this.hand = new ArrayList<>();
			for (Card card : orig.hand) {
				this.hand.add(new Card(card));
			}
			this.drinksTaken = orig.drinksTaken;
			this.id = orig.id;
		}


		@Override
		public boolean equals(Object other) {
			//if (other is not a player) return false;
			if (this.id == ((Player)other).id) {
				return true;
			}
			return false;
		}

	}

	//Factory method to createa a Player
	public Player makePlayer(int id) {
		return new Player(id);
	}
	// Method to increment the dealer count when the dealer counts from 1-13
	public void incrementDealerCount() {
		dealerCount++;
	}


	public boolean ShuffleCardAction(UpDownHumanPlayer action) {
		if (this.getCurrentRound() >= 8) {
			this.setDealerCount(this.getDealerCount() + 1);
		}

		ArrayList<UpDownState.Card> CurrentCard = this.getFlippedCard();

		// want to be able to shuffle the deck of cards and set it to it (not correct yet)
		Random random = new Random();
		int randomIndex = random.nextInt(CurrentCard.size());

		// Swap the first card with the randomly selected card
		UpDownState.Card temp = CurrentCard.get(0);
		CurrentCard.set(randomIndex, temp);
		CurrentCard.set(0, CurrentCard.get(randomIndex)); // set the first element to a random card in the deck

		// Set the shuffled first card back to the game state by creating a new deck containing one card to be shown
		ArrayList<UpDownState.Card> shuffledCard = new ArrayList<>(1);

		shuffledCard.add( CurrentCard.get(0));

		this.setFlippedCard(shuffledCard);

		// add 1 to the current round of the game
		this.setCurrentRound(this.getCurrentRound() + 1);

		return true;
	//end of shufflecardaction
	}

	public boolean ReShuffleCardAction(UpDownHumanPlayer action) {
		ArrayList<UpDownState.Card> deck = this.getFlippedCard();

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
			this.setFlippedCard(deck);
		}
		return true;
	}

	public boolean giveDrinkAction(GiveDrink drink) {

		// Determine which player is giving the drink
		Player giver = drink.getGiver();
		Player receiver = drink.getReceiver();

		// Increment the receiver's score
		if (giver.equals(players.get(0))) {
			// Player 1 is giving the drink
			player1Score++;
		} else if (giver == players.get(1)) {
			// Player 2 is giving the drink
			player2Score++;
		}

		// Decrement the giver's score
		if (receiver.equals(players.get(0))) {
			// Player 1 is receiving the drink
			player1Score--;
		} else if (receiver == players.get(1)) {
			// Player 2 is receiving the drink
			player2Score--;
		}

		return true;
	}

//	public boolean addPoint() {
//		this.setPlayerScore(this.getPlayerScore() + 1);
//		return true;
//	}
//	public boolean subtractPoint() {
//		this.setPlayerScore(this.getPlayerScore() - 1);
//		return true;
//	}
	public boolean submitPoint() {
		this.setCurrentRound(this.getCurrentRound() - 1);
		return true;
	}



}

