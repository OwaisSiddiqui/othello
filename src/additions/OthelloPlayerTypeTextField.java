package additions;

import model.Othello;
import util.Observable;
import util.Observer;
import javafx.scene.control.TextArea;

public class OthelloPlayerTypeTextField extends TextArea implements Observer {

	public OthelloPlayerTypeTextField() {
		String message = "P1: Human" + System.lineSeparator() + "P2: Human";
		this.setText(message);
	}

	public void updatePlayerType(Othello othello) {
		String player1 = "Human";
		String player2 = "Human";
		if (othello.player1.getClass().getSimpleName().equals("PlayerGreedy"))
			player1 = "Greedy";
		if (othello.player1.getClass().getSimpleName().equals("PlayerRandom"))
			player1 = "Random";
		if (othello.player1.getClass().getSimpleName().equals("PlayerAdvanced"))
			player1 = "Advanced";
		if (othello.player2.getClass().getSimpleName().equals("PlayerGreedy"))
			player2 = "Greedy";
		if (othello.player2.getClass().getSimpleName().equals("PlayerRandom"))
			player2 = "Random";
		if (othello.player2.getClass().getSimpleName().equals("PlayerAdvanced"))
			player2 = "Advanced";
		String message = "P1: " + player1 + System.lineSeparator() + "P2: " + player2;
		this.setText(message);
	}

	@Override
	public void update(Observable o) {
		this.updatePlayerType((Othello) o);
	}

}
