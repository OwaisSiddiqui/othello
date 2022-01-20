package visitors;

import model.*;
import iterators.*;

/**
 * HasMoveAllVisitor: mimics Othello.hasMove(x, y, p) (Check if a player has a move at a given square)
 * DesignPattern: Visitor, Iterator
 * @author Christopher Indris (indrisch)
 */
public class HasMovePlayerVisitor extends Visitor{
	int row;
	int col;
	char player;
	boolean hasMove;
	
	public HasMovePlayerVisitor(int x, int y, char p) {
		this.row = x; 
		this.col = y; 
		this.player = p;
	}
	
	/**
	 * @return boolean (if player can move here)
	 */
	public boolean hasMove() {
		return this.hasMove;
	}
	
	/*
	public void visit(Othello o) {
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				
				HasMoveSquareVisitor sv = new HasMoveSquareVisitor(row, col, drow, dcol);
				o.acceptToBoard(sv);
				
				char whosTurn = sv.hasMove();
				
				if (whosTurn == player || whosTurn == OthelloBoard.BOTH) {
					this.hasMove = true; return;
				}
			}
		}
		this.hasMove = false; return;
	}
	*/
	
	/**
	 * DirectionIterator: iterates around the given (row, col) square
	 * -- for each, HasMoveSquareVisitor: checks if the player can move
	 * -- player has a move in at least one direction => there is a move 
	 */
	public void visit(Othello o) {
		
		// dummy array: used only for DirectionIterator to base iterations on.
		char[][] frame = new char[o.DIMENSION][o.DIMENSION];
		
		DirectionIterator di = new DirectionIterator(frame, o.DIMENSION, row, col);
		while(di.hasNext()) {
			di.next();
			HasMoveSquareVisitor sv = new HasMoveSquareVisitor(row, col, di.getDRow(), di.getDCol());
			o.acceptToBoard(sv);
			
			char whosTurn = sv.hasMove();
			
			if (whosTurn == player || whosTurn == OthelloBoard.BOTH) {
				this.hasMove = true; return;
			}	
		}
		this.hasMove = false; return;
	}

}
