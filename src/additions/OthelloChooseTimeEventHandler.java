package additions;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class OthelloChooseTimeEventHandler implements EventHandler<KeyEvent> {

	private OthelloChooseTime oct;
	private OthelloTimer ot;
	private OthelloChooseTime oct2;
	private OthelloTimerEventHandler oteh;
	private OthelloTimerEventHandler oteh2;
	private OthelloFeedbackLabel ofl;
	private OthelloDefaultTimeButton odtb;

	public OthelloChooseTimeEventHandler(OthelloChooseTime oct, OthelloChooseTime oct2, OthelloTimer ot,
			OthelloTimerEventHandler oteh, OthelloTimerEventHandler oteh2, OthelloFeedbackLabel ofl, OthelloDefaultTimeButton odtb) {
		this.oct = oct;
		this.ot = ot;
		this.oct2 = oct2;
		this.oteh = oteh;
		this.oteh2 = oteh2;
		this.ofl = ofl;
		this.odtb = odtb;
	}

	@Override
	public void handle(@SuppressWarnings("exports") KeyEvent event) {
		OthelloChooseTime source = (OthelloChooseTime) event.getSource();
		String input = source.getText();
		if (event.getCode().equals(KeyCode.ENTER) && this.isValidInput(input)) {
			oct.setDisable(true);
			source.hasChoosen = true;
			ot.totalTime = this.stringTimeToInt(input);
			ot.currentTime = ot.totalTime;
			ot.updateTimer();
			if (this.oct.hasChoosen == true && this.oct2.hasChoosen == true) {
				ofl.updateText("");
				oteh.updateTimer();
				oteh2.updateTimer();
				odtb.setDisable(true);
			}
		}
	}

	private boolean isValidInput(String input) {
		if (input.length() <= 5 && input.contains(":") && input.length() - input.replace(":", "").length() == 1 && ((input.length() == 4 && input.indexOf(":") == 1) || (input.length() == 5 && input.indexOf(":") == 2))) {
			try {
				int colonIndex = input.indexOf(":");
				int minutes = Integer.parseInt(String.valueOf(input.substring(0, colonIndex)));
				int seconds = Integer.parseInt(String.valueOf(input.substring(colonIndex + 1, input.length())));
				if (minutes > 30) {
					if (seconds >= 60) {
						ofl.updateText("Seconds has to be between 0 and 59.\nMinutes has to be between 0 and 30.");
					}
					else {
						ofl.updateText("Minutes has to be between 0 and 30.");
					}
					return false;
				}
				if (seconds >= 60) {
					if (minutes > 30) {
						ofl.updateText("Seconds has to be between 0 and 59.\nMinutes has to be between 0 and 30.");
					}
					else {
						ofl.updateText("Seconds has to be between 0 and 59.");
					}
					return false;
				}
				else {
					return true;
				}
			} catch (NumberFormatException nfe) {
				ofl.updateText("Please put in times for both players \nbetween 5:00 and 30:00 minutes.");
				return false;
			}
		}
		else {
			ofl.updateText("Please put in times for both players \nbetween 5:00 and 30:00 minutes.");
			return false;
		}
	}

	private int stringTimeToInt(String time) {
		int colonIndex = time.indexOf(":");
		int minutes = Integer.parseInt(String.valueOf(time.substring(0, colonIndex)));
		int seconds = Integer.parseInt(String.valueOf(time.substring(colonIndex + 1, time.length())));
		return 60 * minutes + seconds;
	}

}
