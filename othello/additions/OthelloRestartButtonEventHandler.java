package ca.utoronto.utm.othello.additions;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.undoredo.CommandInvoker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OthelloRestartButtonEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private CommandInvoker invoker;
	private OthelloTimer ot;
	private OthelloTimer ot2;
	private OthelloTimerEventHandler otehp1;
	private OthelloTimerEventHandler otehp2;
	private OthelloChooseTime octp1;
	private OthelloChooseTime octp2;
	private OthelloDefaultTimeButton odt;
	
	public OthelloRestartButtonEventHandler(Othello othello, OthelloTimer ot, OthelloTimer ot2, OthelloTimerEventHandler otehp1, OthelloTimerEventHandler otehp2, CommandInvoker invoker, 
			OthelloChooseTime octp1, OthelloChooseTime octp2, OthelloDefaultTimeButton odt) {
		this.othello = othello;
		this.invoker = invoker;
		this.ot = ot;
		this.ot2 = ot2;
		this.otehp1 = otehp1;
		this.otehp2 = otehp2;
		this.octp1 = octp1;
		this.octp2 = octp2;
		this.otehp1 = otehp1;
		this.otehp2 = otehp2;
		this.odt = odt;
	}

	@Override
	public void handle(ActionEvent arg0) {
		othello.reset();
		invoker.clear();
		this.ot.resetTimer();
		this.ot2.resetTimer();
		this.octp1.setDisable(false);
		this.octp2.setDisable(false);
		this.odt.setDisable(false);
		this.octp1.setText("");
		this.octp2.setText("");
		this.otehp1.timer.stop();
		this.otehp2.timer.stop();
		this.octp1.hasChoosen = false;
		this.octp2.hasChoosen = false;
	}

}
