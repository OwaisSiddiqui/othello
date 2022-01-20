package tokens;

/**
 * Storage object for OthelloBoard.EMPTY
 * @author Christopher Indris (indrisch)
 */
public class EmptyToken extends Token {
	private String styleString = "-fx-faint-focus-color: transparent;" + "-fx-focus-color: transparent;" + "-fx-min-width: 25px; "
			+ "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px;";

	public EmptyToken() {
		super(' ');
	}

}
