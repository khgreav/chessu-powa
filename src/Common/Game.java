package Common;

import Pieces.Piece;
import Pieces.PieceColor;

import java.util.Stack;

/**
 * Interface for a game class.
 */
public interface Game {
    /**
     * Performs a move on the board if the move is possible.
     * @param from original tile, one that a figure is moving from
     * @param to destination tile, one that a figure is moving to
     * @param playerTurn a color of the player that is making the move
     * @return true if the moving figure can move to the destination tile, otherwise false
     */
    boolean move(Tile from, Tile to, PieceColor playerTurn);

    boolean moveCheck(Tile from, Tile to, PieceColor playerTurn);
    /**
     * Performs the undo action and reverts the most recent move made.
     * @return true if the undo action was performed, otherwise false
     */
    boolean undo();

    /**
     * Performs the redo action and reverts the most recent undo action.
     * @return true if the redo action was performed, otherwise false
     */
    boolean redo();

    /**
     * Returns the undo stack of the game. The stack contains all the moves that happened.
     * @return undo stack
     */
    Stack<BoardMove> getUndo();
}
