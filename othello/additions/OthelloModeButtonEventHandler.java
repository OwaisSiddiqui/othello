package ca.utoronto.utm.othello.additions;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.PlayerAdvanced;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerHuman;
import ca.utoronto.utm.othello.model.PlayerRandom;
import javafx.scene.control.Button;
import javafx.event.*;

public class OthelloModeButtonEventHandler implements EventHandler<ActionEvent> {

	private Othello othello;
	private OthelloPlayerTypeTextField optt;
	
	public OthelloModeButtonEventHandler(Othello othello, OthelloPlayerTypeTextField optt) {
		this.othello = othello;
		this.optt = optt;
	}
	
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button)event.getSource();
		if(source.getText() == "Human") {
			othello.player1 = new PlayerHuman(othello, OthelloBoard.P1);
			othello.player2 = new PlayerHuman(othello, OthelloBoard.P2);
		} else if(source.getText() == "Greedy") {
			if (this.othello.getWhosTurn() == OthelloBoard.P1) {
				othello.player1 = new PlayerHuman(othello, OthelloBoard.P1);
				othello.player2 = new PlayerGreedy(othello, OthelloBoard.P2);
			} else {
				othello.player1 = new PlayerGreedy(othello, OthelloBoard.P1);
				othello.player2 = new PlayerHuman(othello, OthelloBoard.P2);
			}
		}
		else if(source.getText() == "Random") {
			if (this.othello.getWhosTurn() == OthelloBoard.P1) {
				othello.player1 = new PlayerHuman(othello, OthelloBoard.P1);
				othello.player2 = new PlayerRandom(othello, OthelloBoard.P2);
			} else {
				othello.player1 = new PlayerRandom(othello, OthelloBoard.P1);
				othello.player2 = new PlayerHuman(othello, OthelloBoard.P2);
			}
		}
		else if(source.getText() == "Advanced") {
			if (this.othello.getWhosTurn() == OthelloBoard.P1) {
				othello.player1 = new PlayerHuman(othello, OthelloBoard.P1);
				othello.player2 = new PlayerAdvanced(othello, OthelloBoard.P2);
			} else {
				othello.player1 = new PlayerAdvanced(othello, OthelloBoard.P1);
				othello.player2 = new PlayerHuman(othello, OthelloBoard.P2);
			}
		}
		this.optt.updatePlayerType(othello);
	}
	
	

}
