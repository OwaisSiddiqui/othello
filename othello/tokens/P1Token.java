package ca.utoronto.utm.othello.tokens;

/**
 * Storage object for OthelloBoard.P1
 * @author Christopher Indris (indrisch)
 */
public class P1Token extends Token{
	private String styleString = "-fx-background-color: #000000;" + "-fx-background-radius: 25em; " + "-fx-min-width: 25px; "
			+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;";

	public P1Token() {
		super('X');
	}

}
