package additions;

import model.Othello;
import util.Observable;
import util.Observer;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Othello button is the button on the grid. This class
 * observers the model, Othello, and is updated with the token each
 * time a move is made.
 */
public class OthelloButton extends Button implements Observer {
	private int col;
	private int row;
	public char previousToken;

	public OthelloButton(String name, int row, int col) {
		super(name);
		this.row = row;
		this.col = col;
		this.previousToken = name.charAt(0);
		this.setStyle("-fx-faint-focus-color: transparent;" + "-fx-focus-color: transparent;" + "-fx-min-width: 25px; "
				+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
	}
	
	/**
	 * Set the previous token of this button at row, col.
	 */
	public void setPreviousToken(char name) {
		this.previousToken = name;
	}

	/**
	 * Get column number of the button.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Get row number of the button.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * String representation of the button with row, col.
	 */
	@Override
	public String toString() {
		return "( " + row + ", " + col + " )";
	}

	/**
	 * Update the game with the correct token and flip it when the previous token
	 * is changed with the current one.
	 */
	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		
		ScaleTransition st1 = new ScaleTransition(Duration.millis(200), this);
		st1.setFromX(1);
		st1.setByX(0);

		ScaleTransition st2 = new ScaleTransition(Duration.millis(200), this);
		st2.setFromX(0);
		st2.setByX(1);
		
		char move = othello.getWhosTurn();
		Othello othelloCopy = othello.copy();

		if (othello.getToken(row, col) == 'X') {
			this.setStyle("-fx-background-color: #000000;" + "-fx-background-radius: 25em; " + "-fx-min-width: 25px; "
					+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
			if (this.previousToken != othello.getToken(row, col)) {
				st1.play();
				st2.play();
				this.previousToken = othello.getToken(row, col);
			}

		} else if (othello.getToken(row, col) == 'O') {
			this.setStyle("-fx-border-color: black;" + "-fx-border: 12px;" + "-fx-faint-focus-color: black;"
					+ "-fx-focus-color: black;" + "-fx-background-color: #ffffff;" + "-fx-border-radius: 25em; "
					+ "-fx-min-width: 25px; " + "-fx-min-height: 25px; " + "-fx-max-width: 25px; "
					+ "-fx-max-height: 25px;");
			if (this.previousToken != othello.getToken(row, col)) {
				st1.play();
				st2.play();
				this.previousToken = othello.getToken(row, col);
			}

		} else if (othelloCopy.move(this.row, this.col)) {
			this.setStyle("-fx-border-color: #808080;" + "-fx-background-color: #ffcccb;" + "-fx-min-width: 25px; "
					+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
			othelloCopy = othello.copy();
		}

		else {
			this.setStyle(
					"-fx-faint-focus-color: transparent;" + "-fx-focus-color: transparent;" + "-fx-min-width: 25px; "
							+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;");
		}
		
	}
}
