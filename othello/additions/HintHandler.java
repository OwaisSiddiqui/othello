package ca.utoronto.utm.othello.additions;

import ca.utoronto.utm.othello.model.*;

/**
 * This class is responsible for updating the board with the hints that follow
 * the certain strategy. This keeps track of the tokens on the grid which 
 * allows it to determine where the hints should be placed.
 * 
 */

public class HintHandler {
    public static String hintType = "none";

    public static Othello othello;
    public static OthelloButton[][] grid;
    public static String color = "";
    public static int x;
    public static int y;
    public static int oldX = 0;
    public static int oldY = 0;

    public HintHandler(Othello othello, OthelloButton[][] grid) {
        this.othello = othello;
        this.grid = grid;
    }

    public static void showHint() {
        grid[oldX][oldY].update(othello);

        if (othello.isGameOver()) {
            return;
        }

        else if (hintType == "Greedy Hint") {
            PlayerGreedy player = new PlayerGreedy(othello, othello.getWhosTurn());
            x = player.getMove().getCol();
            y = player.getMove().getRow();
            color = "-fx-background-color: #00ff00;";
        }
        else if (hintType == "Random Hint") {
            PlayerRandom player = new PlayerRandom(othello, othello.getWhosTurn());
            Move move = player.getMove();
            x = move.getCol();
            y = move.getRow();
            color = "-fx-background-color: #00ccff;";
        }
        else if (hintType == "Advanced Hint") {
            PlayerAdvanced player = new PlayerAdvanced(othello, othello.getWhosTurn());
            Move move = player.getMove();
            x = move.getCol();
            y = move.getRow();
            color = "-fx-background-color: #ff00ff;";
        }
        else if (hintType == "No Hint") {
            return;
        }

        grid[x][y].setStyle(
                color +
                "-fx-min-width: 25px; " +
                "-fx-min-height: 25px; " +
                "-fx-max-width: 25px; " +
                "-fx-max-height: 25px;");

        oldX = x;
        oldY = y;
    }
}
