package ca.utoronto.utm.othello.iterators;

import java.util.Iterator;

/**
 * Iterates over characters in a char[][] array.
 * @author Christopher Indris (indrisch)
 */
public class BoardIterator implements Iterator<Character> {
	protected Character currentChar;
	protected char[][] charArray;
	protected int dim, row, col;
	
	public BoardIterator(char[][] charArray, int dim) {
		this.charArray = charArray;
		this.dim = dim;
		this.row = 0; this.col = -1;
	}
	
	/** HasNext(): char[dim-1][dim-1] is the "last element". 
	 */
	public boolean hasNext() {
		return !(this.row == this.dim - 1 && this.col == this.dim - 1);
	}
	
	/**
	 * next(): Moves to the next element in the array, and returns the char there.
	 */
	public Character next() {
		if(col < dim - 1) {
			col += 1;
		} else {
			row += 1;
			col = 0;
		}
		this.currentChar = charArray[row][col];
		return this.currentChar;
	}
	
	// Getters
	public int getRow() {return row;}
	public int getCol() {return col;}
	public int getChar() {return this.currentChar.charValue();}

	
	/**
	 * Main method for example useage
	 */
	public static void main(String[] args) {
		
		char[][] board = new char[8][8];
		BoardIterator bi = new BoardIterator(board, 8);
		while(bi.hasNext()) {
			bi.next();
			System.out.println(bi.row + " " + bi.col);
		}
	}
}