package additions;

import javafx.scene.control.Label;

public class OthelloFeedbackLabel extends Label {

	public void updateText(String message) {
		this.setStyle("-fx-text-fill: red;");
		this.setText(message);
	}
	
}
