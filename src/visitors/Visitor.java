package visitors;

import model.*;

/**
 * Abstract Visitor class. Defines OthelloBoard and Othello as visit targets.
 * @author Christopher Indris (indrisch)
 */
public abstract class Visitor {
	public void visit(OthelloBoard ob) {}
	public void visit(Othello o) {}
}
