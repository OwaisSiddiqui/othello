package ca.utoronto.utm.othello.undoredo;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.additions.*;
import javafx.scene.control.Button;
import javafx.event.*;

public class UndoRedoEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private CommandInvoker invoker;
	private OthelloTimer otp1;
	private OthelloTimer otp2;
	private OthelloTimerEventHandler otehp1;
	private OthelloTimerEventHandler otehp2;
	
	public UndoRedoEventHandler(Othello othello, OthelloTimer otp1, OthelloTimer otp2, OthelloTimerEventHandler otehp1, OthelloTimerEventHandler otehp2, CommandInvoker invoker) {
		this.othello = othello;
		this.invoker = invoker;
		this.otp1 = otp1;
		this.otp2 = otp2;
		this.otehp1 = otehp1;
		this.otehp2 = otehp2;
	}
	
	/**
	 * Execute the commands, updating the history and future
	 * depending on if it is an undo or redo command. Also, update
	 * the time.
	 */
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button)event.getSource();
		this.othello.reset();
		this.otp1.resetTimer();
		this.otp2.resetTimer();
		this.otehp1.updateTimer();
		this.otehp2.updateTimer();
		if(source.getText() == "Undo") {
			invoker.back();
		} else if(source.getText() == "Redo") {
			invoker.forward();
		}
		invoker.executeAll();
	}

}
