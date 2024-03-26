package edu.up.cs301.updown;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.updown.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
//FIX THIS
public class UpDownHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */
	
	// The TextView the displays the current counter value
	private TextView testResultsTextView;

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
		//testResultsTextView.setText("" + state.getCounter());
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

		/**
		 * Project E
		 */

		// clear the text
		testResultsTextView.setText(null);

		// new instance of the game state
		UpDownState firstInstance = new UpDownState();

		UpDownState secondInstance = new UpDownState();

		UpDownState firstCopy = new UpDownState(firstInstance);

		UpDownState secondCopy = new UpDownState(secondInstance);


		/**
		 *
		 */

		// Construct the action and send it to the game
		GameAction action = null;

		boolean HasShuffled = false; // use to check if deck has been shuffled after down the river

		// if it is the 8th round then the deck pile will turn into a shuffle card button
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

		// add button for down the river *(need to change the ID when we get the popup to work)*
		if (button.getId() == R.id.plusButton) {
			action = new UpDownMoveAction.AddPoint(this);
		}
		// minus button to subtract points for down the river *(need to change the ID when we get the popup to work)*
		else if (button.getId() == R.id.minusButton) {
			action = new UpDownMoveAction.SubtractPoint(this);
		}

		// this will be for the submit points button *(need to change the ID when we get the popup to work)*
		if (button.getId() == R.id.p1points) {
			action = new UpDownMoveAction.SubmitPoints(this);
		}

		// send action to the game
		game.sendAction(action);

		//call actions of new gameState
		//firstInstance.
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
		activity.setContentView(R.layout.runtest);

		// update to show counter value
		this.testResultsTextView = (EditText) activity.findViewById(R.id.edit_text_input);

		// listener for run test button
		Button runTestButton = (Button) activity.findViewById(R.id.button_run_test);
		runTestButton.setOnClickListener(this);

		// if state is not empty update the GUI
		if (state != null) {
			receiveInfo(state);
		}
	}

}// class CounterHumanPlayer

