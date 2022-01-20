package additions;

import model.Othello;
import model.OthelloBoard;
import util.Observable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class OthelloTimerEventHandler implements EventHandler<ActionEvent> {

	public char player;
	public Timeline timer;
	private OthelloTimer othelloTimer;
	private Othello othello;
	private GameStatus gs;

	public OthelloTimerEventHandler(Othello othello, OthelloTimer othelloTimer, GameStatus gs) {
		this.player = othelloTimer.player;
		this.othello = othello;
		this.othelloTimer = othelloTimer;
		this.timer = new Timeline(new KeyFrame(Duration.millis(1000), this));
		this.timer.setCycleCount(5000);
		this.gs = gs;
	}

	@Override
	public void handle(ActionEvent event) {
		this.othelloTimer.currentTime--;
		this.othelloTimer.updateTimer();
		if (this.othelloTimer.currentTime == 0) {
			String player = "";
			if (this.othelloTimer.player == 'X') {
				player = "P2";
			}
			else if (this.othelloTimer.player == 'O') {
				player = "P1";
			}
			this.gs.setText("The winner of" + System.lineSeparator() + "the game is "+player+"!");
			this.timer.stop();
		}
	}

	public void updateTimer() {
		if (othello.getWhosTurn() == player) {
			this.timer.play();
		} else {
			this.timer.stop();
		}
	}

}
