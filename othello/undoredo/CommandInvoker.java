package ca.utoronto.utm.othello.undoredo;

import java.util.ArrayDeque;

/**
 * Command invoker for the command.
 */
public class CommandInvoker {

	private ArrayDeque<Command> history;
	private ArrayDeque<Command> future;

	// public static ArrayList<MoveCommand> movesPast = new
	// ArrayList<MoveCommand>(),
	// public static ArrayList<MoveCommand> movesFuture = new
	// ArrayList<MoveCommand>();

	/**
	 * Initializes CommandInvoker.
	 */
	public CommandInvoker() {
		history = new ArrayDeque<Command>();
		future = new ArrayDeque<Command>();
	}

	/**
	 * new game -> undone/redone state
	 * Execute all the command in the history array.
	 */
	public void executeAll() {
		for (Command move : history) {
			move.execute();
		}
	}

	/**
	 * Add a command to the history array. Clear the 
	 * future array if it is not empty. 
	 * 
	 * Add a new move to the history stack, ensure no future (paths diverged)
	 * 
	 * @param command
	 */
	public void add(Command command) {
		history.add(command);
		if (!future.isEmpty()) {
			future.clear();
		}
	}

	/**
	 * Setup redo; pop last from future to history.
	 */
	public void forward() { // redo
		if (!future.isEmpty()) {
			history.addLast(future.removeFirst());
		}
	}

	/**
	 * Setup undo; pop last from history to future.
	 */
	public void back() { // undo
		if (!history.isEmpty()) {
			future.addFirst(history.removeLast());
		}
	}

	/**
	 * Clear all the commands from the history and future.
	 */
	public void clear() {
		history.clear();
		future.clear();
	}
}
