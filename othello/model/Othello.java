package ca.utoronto.utm.othello.model;
import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.visitors.*;

import java.util.Random;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over.
 * 
 * See the following for a short, simple introduction.
 * https://www.youtube.com/watch?v=Ol3Id7xYsY4
 * 
 * @author arnold
 *
 */
public class Othello extends Observable {
	public static final int DIMENSION=8; // This is an 8x8 game

	private OthelloBoard board=new OthelloBoard(Othello.DIMENSION);
	private char whosTurn = OthelloBoard.P1;
	private int numMoves = 0;
	public Player player1 = new PlayerHuman(this, OthelloBoard.P1);
	public Player player2 = new PlayerHuman(this, OthelloBoard.P2);
	
	public Othello() {
		this.board = new OthelloBoard(Othello.DIMENSION);
		this.whosTurn = OthelloBoard.P1;
		this.numMoves = 0;
		this.player1 = new PlayerHuman(this, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this, OthelloBoard.P2);
		this.notifyObservers();
	}
	
	/**
	 * Used to reset the OthelloBoard.
	 */
	public void reset() {
		this.board = new OthelloBoard(Othello.DIMENSION);
		this.whosTurn = OthelloBoard.P1;
		this.numMoves = 0;
		this.player1 = new PlayerHuman(this, OthelloBoard.P1);
		this.player2 = new PlayerHuman(this, OthelloBoard.P2);
		this.notifyObservers();
	}
	
	// Accept Methods (for visitors) to Othello and OthelloBoard
	public void accept(Visitor v) {v.visit(this);}
	public void acceptToBoard(Visitor v) {this.board.accept(v);}
	
	
	/** increments the number of moves
	 */
	public void incNumMoves() {this.numMoves++;}

	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() { // Getter
		return this.whosTurn;
	}
	public void setWhosTurn(char player) { // Setter (visitors)
		this.whosTurn = player;
	}
	
	/**
	 * 
	 * @param row 
	 * @param col
	 * @return the token at position row, col.
	 */
	public char getToken(int row, int col) {
		return this.board.get(row, col);
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.
	 * 
	 * @param row
	 * @param col
	 * @return whether the move was successfully made.
	 */
	public boolean move(int row, int col) {
		OthelloMoveVisitor mv = new OthelloMoveVisitor(row, col);
		this.accept(mv);
		boolean moved =  mv.getMoved();
		return moved;
	}
	
	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {
		GetCountVisitor gcv = new GetCountVisitor(player);
		this.acceptToBoard(gcv);
		return gcv.getCount();
	}


	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	public char getWinner() {
		GetWinnerVisitor gw = new GetWinnerVisitor();
		this.accept(gw);
		return gw.getWinner();
	}


	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		return this.whosTurn==OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return a copy of this. The copy can be manipulated without impacting this.
	 */
	public Othello copy() {
		Othello o= new Othello();
		o.board=this.board.copy();
		o.numMoves = this.numMoves;
		o.whosTurn = this.whosTurn;
		return o;
	}


	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString() {
		return board.toString()+"\n";
	}


	/**
	 * run this to test the current class. We play a completely random game. DO NOT
	 * MODIFY THIS!! See the assignment page for sample outputs from this.
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
		Random rand = new Random();


		Othello o = new Othello();
		System.out.println(o.getBoardString());
		while(!o.isGameOver()) {
			int row = rand.nextInt(8);
			int col = rand.nextInt(8);

			if(o.move(row, col)) {
				System.out.println("makes move ("+row+","+col+")");
				System.out.println(o.getBoardString()+ o.getWhosTurn()+" moves next");
			}
		}

	}
}


