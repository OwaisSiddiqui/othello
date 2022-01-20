package tokens;

/**
 * Storage object for OthelloBoard.P2
 * @author Christopher Indris (indrisch)
 */
public class P2Token extends Token {
	private String styleString = "-fx-border-color: black;" + "-fx-border: 12px;" + "-fx-faint-focus-color: black;"
			+ "-fx-focus-color: black;" + "-fx-background-color: #ffffff;" + "-fx-border-radius: 25em; "
			+ "-fx-min-width: 25px; " + "-fx-min-height: 25px; " + "-fx-max-width: 25px; "
			+ "-fx-max-height: 25px;";

	public P2Token() {
		super('O');
	}

}
