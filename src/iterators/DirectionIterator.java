package iterators;

import java.util.Iterator;

/**
 * DirectionIterator: iterates to find all defined squares around a given (row, col) on a char[][] board.
 * @author Christopher Indris (indrisch)
 */
public class DirectionIterator implements Iterator<Character>{
	private Character character;
	protected char[][] charArray;
	private int startRow;
	private int startCol;
	private int dim;
	private int drow;
	private int dcol;
	private int breaker = 0; // could be used to stop loops early
	
	public DirectionIterator(char[][] charArray, int dim, int row, int col) {
		// the square (may not exist) up and to the left.
		this.charArray = charArray;
		this.startRow = row;
		this.startCol = col;
		this.dim = dim;
		this.drow = -1;
		this.dcol = -2;
	}
	
	// For breaking, if necessary
	public int getBreaker() {return this.breaker;}
	public void incBreaker() {this.breaker++;}
	
	// Getters. getChar() can potentially be null (used in iterations: see main())
	public int getDRow() {return this.drow;}
	public int getDCol() {return this.dcol;}
	public Character getChar() {return this.character != null ? this.character : null;}
	public int getCurrRow() {return this.startRow + this.drow;}
	public int getCurrCol() {return this.startCol + this.dcol;}
	
	/**
	 * hasNext(): stops at the last adjacency
	 * (mimics earlier for-loops that send drow and dcol from -1 to 1)
	 */
	public boolean hasNext() {
		return !(this.drow == 1 && this.dcol == 1);
	}
	
	/**
	 * next(): returns the character (or null) of the 3x3 square surrounding and including
	 * the starting square.
	 */
	public Character next() {
		if(this.dcol < 1) {
			this.dcol += 1;
		} else {
			this.drow += 1;
			this.dcol = -1;
		}
		
		if(valid(startRow + drow, startCol + dcol)) {
			this.character = this.charArray[startRow + drow][startCol + dcol];
		} else {
			this.character = null;
		}
		return this.character;
	}
	
	/**
	 * (row, col) exists on board and is not equal to the starting square => move is valid
	 * This method tests if a square is adjacent and defined.
	 * @param row coord of adjacent square
	 * @param col coord of adjacent square
	 * @return boolean: if move is valid
	 */
	private boolean valid(int row, int col) {
		return 0 <= row && row < this.dim 
				&& 0 <= col && col < this.dim 
				&& !(this.dcol == 0 && this.drow == 0);
	}
	
	/**
	 * Main method for sample useage
	 */
	public static void main(String[] args) {
		
		char[][] board = new char[8][8];
		DirectionIterator di = new DirectionIterator(board, 8, 0, 7);
		while(di.hasNext()) {
			di.next();
			if(di.getChar() != null) {
				System.out.println(di.getCurrRow() + " " + di.getCurrCol());
			}
		}
	}

}
