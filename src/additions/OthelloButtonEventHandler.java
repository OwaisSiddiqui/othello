package additions;

import model.Move;
import model.Othello;
import model.OthelloBoard;
import undoredo.CommandInvoker;
import undoredo.MoveTimeCommand;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class OthelloButtonEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private CommandInvoker invoker;
	private OthelloChooseTime octp1;
	private OthelloChooseTime octp2;
	private OthelloTimerEventHandler otehp1;
	private OthelloTimerEventHandler otehp2;
	private OthelloFeedbackLabel ofl;
	private OthelloTimer otp1;
	private OthelloTimer otp2;

	public OthelloButtonEventHandler(Othello othello, CommandInvoker invoker, OthelloChooseTime octp1,
			OthelloChooseTime octp2, OthelloTimerEventHandler otehp1, OthelloTimerEventHandler otehp2,
			OthelloFeedbackLabel ofl, OthelloTimer otp1, OthelloTimer otp2) {
		this.othello = othello;
		this.invoker = invoker;
		this.octp1 = octp1;
		this.octp2 = octp2;
		this.otehp1 = otehp1;
		this.otehp2 = otehp2;
		this.ofl = ofl;
		this.otp1 = otp1;
		this.otp2 = otp2;
	}

	@Override
	public void handle(ActionEvent event) {

		OthelloButton source = (OthelloButton) event.getSource();
		
		ScaleTransition st1 = new ScaleTransition(Duration.millis(200), source);
		st1.setFromX(1);
		st1.setByX(0);
		
		ScaleTransition st2 = new ScaleTransition(Duration.millis(200), source);
		st2.setFromX(0);
		st2.setByX(1);

		if (this.octp1.hasChoosen && this.octp2.hasChoosen) {
			ofl.updateText("");
			if (!othello.move(source.getRow(), source.getCol())) {
				ofl.updateText("Invalid move");
			}
			else {
				st1.play();
				st2.play();
				if (!othello.player1.getClass().getSimpleName().equals("PlayerHuman")
						&& othello.getWhosTurn() == OthelloBoard.P1) {
					Move player1Move = othello.player1.getMove();
					othello.move(player1Move.getRow(), player1Move.getCol());
				} else if (!othello.player2.getClass().getSimpleName().equals("PlayerHuman")
						&& othello.getWhosTurn() == OthelloBoard.P2) {
					Move player2Move = othello.player2.getMove();
					othello.move(player2Move.getRow(), player2Move.getCol());

				}
			}
			this.otehp1.updateTimer();
			this.otehp2.updateTimer();
			// could have a check for move validity
			invoker.add(new MoveTimeCommand(this.othello, otp1, otp2, new Move(source.getRow(), source.getCol()), othello.getWhosTurn()));
		} else {
			ofl.updateText("Please put in times for both players \nbetween 5:00 and 30:00 minutes.");
		}
		HintHandler.showHint();
	}

}
