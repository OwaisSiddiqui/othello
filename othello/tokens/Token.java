package ca.utoronto.utm.othello.tokens;

/**
 * Tokens: to be used with Factory design pattern.
 * Four subclasses (p1, p2, empty, both) store information relevant to each token type.
 * Currently, all tokens have a char (reference to OthelloBoard) and styleString (could be used for GUI)
 * @author Christopher Indris (indrisch)
 */
public class Token {
	private char token;
	private String styleString = "";
	
	public Token(char token) {
		this.token = token;
	}
	
	// Getters
	public char getChar() {return this.token;}
	public String getStyleString() {return this.styleString;}
}