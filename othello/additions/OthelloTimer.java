package ca.utoronto.utm.othello.additions;

import ca.utoronto.utm.othello.model.OthelloBoard;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class OthelloTimer extends Label {

	public char player;
	public int totalTime;
	public int currentTime;

	public OthelloTimer(char player) {
		this.setFont(new Font("Arial", 24));
		this.player = player;
		this.totalTime = 300; // In seconds, 300 seconds = 5 minutes
		this.currentTime = this.totalTime;
		this.updateTimer();
	}

	public int secondsToMinutes(int seconds) {
		if (this.currentTime < 0) {
			return 0;
		} else {
			return seconds / 60;
		}
	}

	public int getRemainingSeconds(int seconds) {
		if (this.currentTime < 0) {
			return 0;
		} else {
			return seconds - (seconds / 60) * 60;
		}
	}

	public void updateTimer() {
		if (this.player == OthelloBoard.P1) {
			this.setText("Black: " + secondsToMinutes(this.currentTime) + ":"
					+ String.format("%02d", getRemainingSeconds(this.currentTime)));
		} else {
			this.setText("White: " + secondsToMinutes(this.currentTime) + ":"
					+ String.format("%02d", getRemainingSeconds(this.currentTime)));
		}
	}
	
	public void resetTimer() {
		this.currentTime = totalTime;
		this.updateTimer();
	}

}
