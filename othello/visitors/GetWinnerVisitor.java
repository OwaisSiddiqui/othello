package ca.utoronto.utm.othello.visitors;

import ca.utoronto.utm.othello.model.*;

/**
 * GetWinnerVisitor: mimics getWinner (returns the token of the winning player, or empty if game unfinished/tied)
 * DesignPattern: Visitor
 * @author Christopher Indris (indrisch)
 */
public class GetWinnerVisitor extends Visitor{
	char winner;
	
	public GetWinnerVisitor() {}
	
	/**
	 * EMPTY if the game is unfinished, or the game ends in a tie.
	 * Otherwise, player with the higher count.
	 */
	public void visit(Othello o) {
		
		if(!o.isGameOver()) {this.winner = OthelloBoard.EMPTY; return;}
		
		GetCountVisitor p1 = new GetCountVisitor(OthelloBoard.P1); o.accept(p1);
		GetCountVisitor p2 = new GetCountVisitor(OthelloBoard.P2); o.accept(p2);
		
		if(p1.getCount() > p2.getCount()) {this.winner = OthelloBoard.P1; return;}
		if(p1.getCount() < p2.getCount()) {this.winner = OthelloBoard.P2; return;}
		this.winner = OthelloBoard.EMPTY; return;
		
	}
	
	/**
	 * Getter: returns game's winner
	 * @return char (player)
	 */
	public char getWinner() {
		return this.winner;
	}

}
