package additions;

import model.Othello;
import model.OthelloBoard;
import util.Observable;
import util.Observer;
import javafx.scene.control.TextArea;

/**
 * This class forms the textbox which displays who's turn it is, 
 * keeps count of the number of tokens each player has on the board
 * and displays who won when the game is over. 
 *
 */

public class GameStatus extends TextArea implements Observer {
	
	private OthelloTimer ot;
	private OthelloTimer ot2;

	public GameStatus(Othello o, OthelloTimer ot, OthelloTimer ot2) {
		this.ot = ot;
		this.ot2 = ot2;
		if (o.isGameOver()) {
			if (o.getWinner() != OthelloBoard.EMPTY) {
				this.setText("The winner of the game is " + o.getWinner());
			} else {
				this.setText("The game has ended in a draw!");
			}
		} else {
			String score1 = "Black | P1" + ": " + ((Integer) o.getCount(OthelloBoard.P1)).toString();
			String score2 = "White | P2" + ": " + ((Integer) o.getCount(OthelloBoard.P2)).toString();
			String turn = "It is P1's turn.";
			String message = score1 + System.lineSeparator() + score2 + System.lineSeparator() + turn;
			this.setText(message);
		}
	}

	@Override
	public void update(Observable o) {
		Othello game = (Othello) o;
		if (game.isGameOver()) {
			if (game.getWinner() == OthelloBoard.P1) {
				this.setText("The winner of" + System.lineSeparator() + "the game is P1!");
			} else if (game.getWinner() == OthelloBoard.P2) {
				this.setText("The winner of" + System.lineSeparator() + "the game is P2!");
			} else {
				this.setText("The game has ended in a draw!");
			}
		} else {
			String score1 = "Black | P1" + ": " + ((Integer) game.getCount(OthelloBoard.P1)).toString();
			String score2 = "White | P2" + ": " + ((Integer) game.getCount(OthelloBoard.P2)).toString();
			String turn;
			if (game.getWhosTurn() == 'X') {
				turn = "It is P1's turn.";
			} else {
				turn = "It is P2's turn.";
			}
			String message = score1 + System.lineSeparator() + score2 + System.lineSeparator() + turn;
			this.setText(message);
		}
	}

}
