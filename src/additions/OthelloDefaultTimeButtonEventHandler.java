package additions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OthelloDefaultTimeButtonEventHandler implements EventHandler<ActionEvent> {

	private OthelloDefaultTimeButton odtb;
	private OthelloChooseTime oct;
	private OthelloChooseTime oct2;
	private OthelloTimerEventHandler oteh;
	private OthelloTimerEventHandler oteh2;
	private OthelloFeedbackLabel ofl;

	public OthelloDefaultTimeButtonEventHandler(OthelloDefaultTimeButton odtb, OthelloChooseTime oct,
			OthelloChooseTime oct2, OthelloTimerEventHandler oteh, OthelloTimerEventHandler oteh2,
			OthelloFeedbackLabel ofl) {
		this.odtb = odtb;
		this.oct = oct;
		this.oct2 = oct2;
		this.oteh = oteh;
		this.oteh2 = oteh2;
		this.ofl = ofl;
	}

	@Override
	public void handle(ActionEvent event) {
		this.oct.setText("5:00");
		this.oct2.setText("5:00");
		this.oct.hasChoosen = true;
		this.oct2.hasChoosen = true;
		this.oct.setDisable(true);
		this.oct2.setDisable(true);
		oteh.updateTimer();
		oteh2.updateTimer();
		this.odtb.setDisable(true);
		this.ofl.updateText("");
	}

}
