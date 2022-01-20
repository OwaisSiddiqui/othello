package visitors;

import model.*;
import iterators.*;

/**
 * HasMoveAllVisitor: mimics OthelloBoard.hasMove() (Return token of the player who can move)
 * DesignPattern: Visitor, Iterator
 * @author Christopher Indris (indrisch)
 */
public class HasMoveAllVisitor extends Visitor{
	char hasMove;
	
	/**
	 * @return char representation of player(s) that can move.
	 */
	public char hasMove() {
		return this.hasMove;
	}
	
	/*
	public void visit(OthelloBoard ob) {
		char retVal = ob.EMPTY;
		for (int row = 0; row < ob.getDim(); row++) {
			for (int col = 0; col < ob.getDim(); col++) {
				for (int drow = -1; drow <= 1; drow++) {
					for (int dcol = -1; dcol <= 1; dcol++) {
						if (drow == 0 && dcol == 0)
							continue;
						
						HasMoveSquareVisitor sv = new HasMoveSquareVisitor(row, col, drow, dcol);
						ob.accept(sv);
						char p = sv.hasMove();
						
						if (p == ob.P1 && retVal == ob.P2) {
							hasMove = ob.BOTH; return;}
						if (p == ob.P2 && retVal == ob.P1) {
							hasMove = ob.BOTH; return;}
						if (retVal == ob.EMPTY)
							retVal = p;
					}
				}
			}
		}
		hasMove = retVal; return;
	}
	*/
	
	/**
	 * BoardIterator: iterates through every square.
	 * DirectionIterator: for each square, iterate through the directions to valid, adjacent squares
	 * HasMoveSquareVisitor: checks which player has a valid move (for a square and direction)
	 * 
	 * retVal stores the char of whichever move is found first.
	 * If BOTH have a move: return (break from loop)
	 * -- this would occur if a move had been found earlier, and a move for the opponent has also been found
	 */
	public void visit(OthelloBoard ob) {
		char retVal = ob.EMPTY;
		
		BoardIterator bi = new BoardIterator(ob.getBoard(), ob.getDimension());
		while(bi.hasNext()) {
			bi.next();
			
			DirectionIterator di = new DirectionIterator(ob.getBoard(), ob.getDimension(), bi.getRow(), bi.getCol());
			while(di.hasNext()) {
				di.next();
				if(di.getChar() != null) {
				HasMoveSquareVisitor sv = new HasMoveSquareVisitor(bi.getRow(), bi.getCol(), di.getDRow(), di.getDCol());
				ob.accept(sv);
				char p = sv.hasMove();
				
				if (p == ob.P1 && retVal == ob.P2) {
					hasMove = ob.BOTH; return;}
				if (p == ob.P2 && retVal == ob.P1) {
					hasMove = ob.BOTH; return;}
				if (retVal == ob.EMPTY)
					retVal = p;
				}
			}
		}
		hasMove = retVal; return;
	}
	
}
