package visitors;

import model.*;
import iterators.*;

/**
 * MoveVisitor (Visitor, Iterator): mimics OthelloBoard.move() (manipulates OthelloBoard after piece placements)
 * @author Christopher Indris (indrisch)
 */
public class MoveVisitor extends Visitor {
	int row;
	int col;
	char player;
	boolean moved;
	
	/**
	 * Constructor: takes inputs from OthelloBoard.move()
	 * @param row (int) - row of intended placement
	 * @param col (int) - col of intended placement
	 * @param player (char) - player attempting to move
	 */
	public MoveVisitor(int row, int col, char player) {
		this.row = row;
		this.col = col;
		this.player = player;
	}
	
	/**
	 * @return boolean: if move attempt successful
	 */
	public boolean moved() {return this.moved;}
	
	public void visit(Othello o) {
	}
	
	/**
	 * (row, col): off of board || unempty || no move available => no move, moved=false
	 *  - DirectionIterator checks all directions for flips. numChanged tallies all flips, all directions.
	 * When flips are made, add the 'cornerstone' token and moved=true
	 */
	public void visit(OthelloBoard ob) {
		if(!validCoordinate(ob.getDimension(), this.row, this.col) || ob.getBoard()[this.row][this.col] != ob.EMPTY) {
			this.moved = false; return;
		}
		
		int numChangedTotal = 0;
		
		DirectionIterator di = new DirectionIterator(ob.getBoard(), ob.getDimension(), row, col);
		while(di.hasNext()) {
			di.next();
			if(di.getChar() != null) {
			int numChanged = flip(ob, row + di.getDRow(), col + di.getDCol(), di.getDRow(), di.getDCol(), player);
			if (numChanged >= 0)
				numChangedTotal += numChanged;	
			}
			}
		
		if (numChangedTotal > 0) {
			ob.setBoard(row, col, player);
			this.moved = true; return;
		}
		this.moved = false; return;
	}
	
	/**
	 * Ensures that (row, col) is a valid position on board with dimension dim
	 * @param dim (int)
	 * @param row (int)
	 * @param col (int)
	 * @return boolean: if (row, col) is within board bounds
	 */
	public static boolean validCoordinate(int dim, int row, int col) {
		return 0 <= row && row < dim && 0 <= col && col < dim;
	}
	
	/**
	 * Flips and counts flipped tokens in favour of (player) from (row, col) in direction (drow, dcol) [integers]
	 * @param ob (OthelloBoard)
	 * @param row 
	 * @param col
	 * @param drow
	 * @param dcol
	 * @param player (char) of current player
	 * @return int (count of tokens flipped)
	 */
	public static int flip(OthelloBoard ob, int row, int col, int drow, int dcol, char player) {
		if (!validCoordinate(ob.getDimension(), row, col))
			return -1;
		if (ob.getBoard()[row][col] == ob.EMPTY)
			return -1;
		if (ob.getBoard()[row][col] == player)
			return 0;
		if (ob.getBoard()[row][col] == ob.otherPlayer(player)) {
			int numChanged = MoveVisitor.flip(ob, row + drow, col + dcol, drow, dcol, player);
			if (numChanged >= 0) {
				ob.setBoard(row, col, player);
				return numChanged + 1;
			} else {
				return numChanged;
			}
		}
		return -1; // Should not get here!
	}
}
