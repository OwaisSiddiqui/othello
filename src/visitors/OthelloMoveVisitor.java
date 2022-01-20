package visitors;

import model.*;

/**
 * MoveVisitor (Visitor, Iterator): mimics Othello.move() (manipulates OthelloBoard after piece placements)
 * @author Christopher Indris (indrisch)
 */
public class OthelloMoveVisitor extends Visitor{
	int row;
	int col;
	boolean moved;
	
	public OthelloMoveVisitor(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * @return boolean: if move was successful
	 */
	public boolean getMoved() {
		return this.moved;
	}
	
	/**
	 * Sends a MoveVisitor to manipulate OthelloBoard (mv.moved returns if move was successful)
	 * If move successful: end turn and set next mover (HasMoveAllVisitor ensures next move possible)
	 * -- +1 to total number of moves, and notify observers of changes
	 * otherwise: moved=false
	 */
	public void visit(Othello o) {
		MoveVisitor mv = new MoveVisitor(this.row, this.col, o.getWhosTurn());
		o.acceptToBoard(mv);
		if(mv.moved) {
			o.setWhosTurn(OthelloBoard.otherPlayer(o.getWhosTurn()));
			
			HasMoveAllVisitor av = new HasMoveAllVisitor();
			o.acceptToBoard(av);
			
			char allowedMove = av.hasMove();
			if(allowedMove != OthelloBoard.BOTH)o.setWhosTurn(allowedMove);
			o.incNumMoves();
			o.notifyObservers();
			this.moved = true;
		} else {
			this.moved = false;
		}
	}

}
