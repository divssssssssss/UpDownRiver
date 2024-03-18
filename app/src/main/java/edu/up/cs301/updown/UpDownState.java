package edu.up.cs301.updown;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the counter.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class UpDownState extends GameState {

	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;
	private int id;
	// variable used to keep track of the cards that have been flipped by the dealer
	private Card[] flippedCard;
	
	private ArrayList<Integer> players = new ArrayList<Integer>();
	private int currentRound;
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
		this.players = orig.players;
		this.currentRound = orig.currentRound;
		this.dealerCount = orig.dealerCount;
		this.id = orig.id;
	}

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

	public class Card {
		private String suit;
		private int rank;
	}

	public class Player {
		private ArrayList<Card> hand;
		private int drinksTaken;

	}

	public void incrementDealerCount() {
		dealerCount++;
	}

}

