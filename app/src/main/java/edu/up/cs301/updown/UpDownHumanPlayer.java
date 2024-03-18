package edu.up.cs301.updown;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.updown.R;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI of a counter-player. The GUI displays the current value of the counter,
 * and allows the human player to press the '+' and '-' buttons in order to
 * send moves to the game.
 * 
 * Just for fun, the GUI is implemented so that if the player presses either button
 * when the counter-value is zero, the screen flashes briefly, with the flash-color
 * being dependent on whether the player is player 0 or player 1.
 *
 * @author Christopher Vo, Daniel Le, Divya Pakalapati, Ruth Shepard
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013, Spring 2024
 */
public class UpDownHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */
	
	// The TextView the displays the current counter value
	private TextView counterValueTextView;
	
	// the most recent game state, as given to us by the CounterLocalGame
	private UpDownState state;
	
	// the android activity that we are running
	private GameMainActivity myActivity;
	
	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public UpDownHumanPlayer(String name) {
		super(name);
	}

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view heirarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.top_gui_layout);
	}
	
	/**
	 * sets the counter value in the text view
	 */
	protected void updateDisplay() {
		// set the text in the appropriate widget
		//counterValueTextView.setText("" + state.getCounter());
	}

	/**
	 * this method gets called when the user clicks the '+' or '-' button. It
	 * creates a new CounterMoveAction to return to the parent activity.
	 * 
	 * @param button
	 * 		the button that was clicked
	 */
	public void onClick(View button) {
		// if we are not yet connected to a game, ignore
		if (game == null) return;

		// Construct the action and send it to the game
		GameAction action = null;

		boolean HasShuffled = false; // use to check if deck has been shuffled after down the river

		// if it is the 8th round then the deck pile will turn into a shuffle card
		if (state.getCurrentRound() == 8 && !HasShuffled) {
			if (button.getId() == R.id.deck_pile) {
				action = new ShuffleCards(this);
				HasShuffled = true;
			}
		}
		// Check to see if flip card button is pressed and is below 8 cards dealt
		else if (button.getId() == R.id.deck_pile) {
			action = new CardFlipped(this);
		}

		// return home button
		if (button.getId() == R.id.homeButton) {
			// return to main menu
			 action = new ReturnHome(this);
		}

		if (button.getId() == R.id.plusButton) { // will need to change id for later
			// plus button: create "increment" action
			action = new UpDownMoveAction.AddPoint(this);
		}
		else if (button.getId() == R.id.minusButton) { // will need to change id for later
			// minus button: create "decrement" action
			action = new UpDownMoveAction.SubtractPoint(this);
		}

		// this will be for the submit points button *(need to change the ID when we get the popup to work)*
		if (button.getId() == R.id.p1points) {
			action = new UpDownMoveAction.SubmitPoints(this);
		}

		game.sendAction(action); // send action to the game
	}// onClick
	
	/**
	 * callback method when we get a message (e.g., from the game)
	 * 
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		// ignore the message if it's not a CounterState message
		if (!(info instanceof UpDownState)) return;
		
		// update our state; then update the display
		this.state = (UpDownState)info;
		updateDisplay();
	}
	
	/**
	 * callback method--our game has been chosen/rechosen to be the GUI,
	 * called from the GUI thread
	 * 
	 * @param activity
	 * 		the activity under which we are running
	 */
	public void setAsGui(GameMainActivity activity) {
		
		// remember the activity
		this.myActivity = activity;
		
	    // Load the layout resource for our GUI
		activity.setContentView(R.layout.counter_human_player);
		
		// make this object the listener for both the '+' and '-' 'buttons
		Button plusButton = (Button) activity.findViewById(R.id.plusButton);
		plusButton.setOnClickListener(this);
		Button minusButton = (Button) activity.findViewById(R.id.minusButton);
		minusButton.setOnClickListener(this);

		// remember the field that we update to display the counter's value
		this.counterValueTextView =
				(TextView) activity.findViewById(R.id.counterValueTextView);
		
		// if we have a game state, "simulate" that we have just received
		// the state from the game so that the GUI values are updated
		if (state != null) {
			receiveInfo(state);
		}
	}

}// class CounterHumanPlayer

