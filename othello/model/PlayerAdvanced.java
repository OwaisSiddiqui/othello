package ca.utoronto.utm.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * PlayerAdvanced makes a move in accordance to a certain algorithm.
 * First the player checks to see if it put a token on one of the 
 * corners (i.e. positions (0,0), (0,7), (7,0) or (7,7)). Then, the player
 * checks to see if it can move on any of the sides of the board. If the 
 * advanced player cannot make a move on the corners or the sides, then
 * it will make a move so that it maximizes the number of tokens flipped
 * in the middle 4x4 region in the middle of Othello board. If the player 
 * cannot increase the number of tokens it has in this region, then it will
 * move anywhere on the board which will maximize the number of tokens it has
 * on the board (similar to PlayerGreedy's strategy).
 *
 */

public class PlayerAdvanced extends Player {
	
	public PlayerAdvanced(Othello othello, char player) {
		super(othello, player);
	}

	@Override
	public Move getMove() {
		
		// Choose a corner if available
		List<Object> cornerMove = (List<Object>) isCornerAvailable();
		if ((boolean) cornerMove.get(1)) {
			return (Move) cornerMove.get(0);
		}
		
		
		// Chooses a side if available. Looks for side tile which maximizes tokens on board.
		List<Object> sideMove = (List<Object>) areSidesAvailable();
		if ((boolean) sideMove.get(1)) {
			return (Move) sideMove.get(0);
		}
		
		// Chooses a move that maximizes number of tokens in middle 4x4 area of the board.
		List<Object> maxmizeMiddleMove = (List<Object>) canMaximizeMiddleTokens();
		if ((boolean) maxmizeMiddleMove.get(1)) {
			return (Move) maxmizeMiddleMove.get(0);
		}
		
		// If the player cannot move using the three previous methods, the player moves to the spot which maximizes their tokens.
		Othello othelloCopy = othello.copy();
		Move bestMove = new Move(0,0);
		int tokens = othelloCopy.getCount(this.player);	
		for(int row=0;row<Othello.DIMENSION;row++) {
			for(int col=0;col<Othello.DIMENSION;col++) {
				othelloCopy = othello.copy();
				if(othelloCopy.move(row, col) && othelloCopy.getCount(this.player)>tokens) {
					tokens = othelloCopy.getCount(this.player);
					bestMove = new Move(row,col);
				}
			}
		}
		return bestMove;
	}
	
	private List<Object> isCornerAvailable() {
		Othello othelloCopy = othello.copy();
		Move bestMove=new Move(0,0);
		boolean hasMoved = false;
		
		int tokens = othelloCopy.getCount(this.player);
		if (othelloCopy.move(0, 0)) {
			tokens = othelloCopy.getCount(this.player);
			othelloCopy = othello.copy();
			hasMoved = true;
		} if (othelloCopy.move(0, 7)) {
			int tempTokens = othelloCopy.getCount(this.player);
			if (tempTokens > tokens) {
				tokens = tempTokens;
				bestMove = new Move(0, 7);
				othelloCopy = othello.copy();
			}
			hasMoved = true;
		} if (othelloCopy.move(7, 0)) {
			int tempTokens = othelloCopy.getCount(this.player);
			if (tempTokens > tokens) {
				tokens = tempTokens;
				bestMove = new Move(7, 0);
				othelloCopy = othello.copy();
			}
			hasMoved = true;
		} if (othelloCopy.move(7, 7)) {
			int tempTokens = othelloCopy.getCount(this.player);
			if (tempTokens > tokens) {
				tokens = tempTokens;
				bestMove = new Move(7, 7);
				othelloCopy = othello.copy();
			}
			hasMoved = true;
		}
		
		ArrayList<Object> l = new ArrayList<Object>();
		l.add(bestMove);
		l.add(hasMoved);	
		
		return l;
	}
	
	
	private List<Object> areSidesAvailable() {
		Othello othelloCopy = othello.copy();
		Move bestMove=new Move(0,0);
		boolean hasMoved = false;
		
		int tokens = othelloCopy.getCount(this.player);
		// Checks top and bottom border
		for (int col = 0; col <= 7; col++) {
			if (othelloCopy.move(0, col)) {
				int tempTokens = othelloCopy.getCount(this.player);
				if (tempTokens > tokens) {
					tokens = tempTokens;
					bestMove = new Move(0, col);
					othelloCopy = othello.copy();
				}
			}
			
			if (othelloCopy.move(7, col)) {
				int tempTokens = othelloCopy.getCount(this.player);
				if (tempTokens > tokens) {
					tokens = tempTokens;
					bestMove = new Move(7, col);
					othelloCopy = othello.copy();
				}
			}
			
		}
		for (int row = 0; row <= 7; row++) {
			if (othelloCopy.move(row, 0)) {
				int tempTokens = othelloCopy.getCount(this.player);
				if (tempTokens > tokens) {
					tokens = tempTokens;
					bestMove = new Move(row, 0);
				}
			}
			
			if (othelloCopy.move(row, 7)) {
				int tempTokens = othelloCopy.getCount(this.player);
				if (tempTokens > tokens) {
					tokens = tempTokens;
					bestMove = new Move(row, 7);
					othelloCopy = othello.copy();
				}
			}
		}
		
		ArrayList<Object> l = new ArrayList<Object>();
		l.add(bestMove);
		l.add(hasMoved);	
		
		return l;
	}
	
	private List<Object> canMaximizeMiddleTokens() {
		Othello othelloCopy = othello.copy();
		Move bestMove=new Move(0,0);
		boolean hasMoved = false;
		
		// If this method is called, that means that there is no move that
		// can be made on the corners or the sides so we only need to check 6/8 rows/columns.
		
		int tokens = othelloCopy.getCount(this.player);
		for (int row=1; row < 7; row++) {
			for (int col=1; col < 7; col++) {
				if (othelloCopy.move(row, col)) {
					int count = getTokensInMiddle(othelloCopy);			
					if (count > tokens) {
						tokens = count;
						hasMoved = true;
						bestMove = new Move(row, col);
					}
					othelloCopy = othello.copy();
				}
			}
		}
		
		ArrayList<Object> l = new ArrayList<Object>();
		l.add(bestMove);
		l.add(hasMoved);
		
		return l;
	}

	
	private int getTokensInMiddle(Othello o) {
		int count = 0;
		for (int row=2; row < 5; row++) {
			for (int col=2; col < 5; col++) {
				if (o.getToken(row, col) == this.player) {
					count++;
				}
			}
		}
		return count;
	}
	
}
