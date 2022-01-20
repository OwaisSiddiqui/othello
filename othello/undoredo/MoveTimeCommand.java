package ca.utoronto.utm.othello.undoredo;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.othello.additions.*;

public class MoveTimeCommand implements Command {
	private Othello othello;
	private Move move;
	private char player;
	private OthelloTimer otp1;
	private OthelloTimer otp2;
	private int p1time;
	private int p2time;
	
	/**
	 * Setup undo; pop last from history to future.
	 */
	public MoveTimeCommand(Othello othello, OthelloTimer otp1, OthelloTimer otp2, Move move, char player) {
		this.othello = othello;
		this.move = move;
		this.player = player;
		this.otp1 = otp1;
		this.otp2 = otp2;
		this.p1time = otp1.currentTime;
		this.p2time = otp2.currentTime;
	}

	/**
	 * Execute move and time commands.
	 */
	public void execute() {
		this.otp1.currentTime = p1time;
		this.otp2.currentTime = p2time;
		this.otp1.updateTimer();
		this.otp2.updateTimer();
		othello.move(move.getRow(), move.getCol());
	}
	
	/**
	 * String representation with player and move of the command.
	 */
	public String toString() {
		return Character.toString(player) + this.move;
	}

}
