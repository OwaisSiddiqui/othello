package ca.utoronto.utm.othello.factories;

import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.tokens.*;

/**
 * TokenFactory
 * DesignPattern: Factory
 * This class builds a Token based on instructions from char "pointers"
 * that live on the OthelloBoard. These Token objects can be used to hold
 * information (such as styling instructions) for a GUI.
 * 
 * @author Christopher Indris (indrisch)
 */

public class TokenFactory {
	public Token create(char player) {
		if(player == OthelloBoard.P1)return new P1Token();
		if(player == OthelloBoard.P2)return new P2Token();
		if(player == OthelloBoard.EMPTY)return new EmptyToken();
		if(player == OthelloBoard.BOTH)return new BothToken();
		return null;
	}
}
