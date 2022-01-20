package additions;

import javafx.scene.control.Button;

/**
 * Othello mode buttons. The different modes are 
 * HumanVsHuman, HumanVsGreedy, HumanVsRandom, and 
 * HumanVsAdvanced.
 */
public class OthelloModeButton extends Button {

	public String mode;
	
	public OthelloModeButton(String mode) {
		this.mode = mode;
		this.setText(mode);
	}
	
}
