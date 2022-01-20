package ca.utoronto.utm.othello.visitors;

import ca.utoronto.utm.othello.model.*;

public class HasMoveSquareVisitor extends Visitor{
	int row;
	int col;
	int drow;
	int dcol;
	char hasMove;
	
	public char hasMove() {
		return this.hasMove;
	}
	
	public HasMoveSquareVisitor(int row, int col, int drow, int dcol) {
		this.row = row;
		this.col = col;
		this.drow = drow;
		this.dcol = dcol;
	}
	
	public void visit(OthelloBoard ob) {
		if(!this.validCoordinate(ob.getDimension(), this.row, this.col) || ob.getBoard()[row][col] != ob.EMPTY) {
			this.hasMove = ob.EMPTY; return;
		}
		this.hasMove = alternation(ob, this.row + this.drow, this.col + this.dcol, this.drow, this.dcol);
	}
	
	public static boolean validCoordinate(int dim, int row, int col) {
		return 0 <= row && row < dim && 0 <= col && col < dim;
	}
	
	public static char alternation(OthelloBoard ob, int row, int col, int drow, int dcol) {
		if (drow == 0 && dcol == 0) return ob.EMPTY;
		char firstToken = ob.get(row,col);
		while (true) {
			char nextToken = ob.get(row,col);
			if(nextToken != ob.P1 && nextToken != ob.P2) return ob.EMPTY;
			if(nextToken == ob.otherPlayer(firstToken)) return nextToken;
			row += drow; col += dcol;
		}
	}
	
}
