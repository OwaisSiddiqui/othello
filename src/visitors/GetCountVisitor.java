package visitors;

import model.*;
import iterators.*;

/**
 * GetCountVisitor: mimics getCount (returns a player's score, given a player token)
 * DesignPattern: Visitor
 * @author Christopher Indris (indrisch)
 */
public class GetCountVisitor extends Visitor{
	int count;
	char player;
	
	public GetCountVisitor(char player) {
		this.player = player;
	}
	
	/** Accepts directly to OthelloBoard.
	 */
	public void visit(Othello o) {
		o.acceptToBoard(this);
	}
	/*
	public void visit(OthelloBoard ob) {
		int count = 0;
		for (int row = 0; row < ob.getDim(); row++) {
			for (int col = 0; col < ob.getDim(); col++) {
				if (ob.getBoard()[row][col] == player)
					count++;
			}
		}
		this.count = count; return;
	}
	*/
	
	/**
	 * Iterates through a char[][] board. Increments a counter when the char at
	 * a position matches the given player.
	 */
	public void visit(OthelloBoard ob) {
		char[][] board = ob.getBoard();
		int count = 0;
		BoardIterator bi = new BoardIterator(board, ob.getDimension());
		while(bi.hasNext()) {
			bi.next();
			if(board[bi.getRow()][bi.getCol()] == player) {count++;}
		}
		this.count = count; return;
	}
	
	/**
	 * @return player's score
	 */
	public int getCount() {
		return this.count;
	}
	
}
