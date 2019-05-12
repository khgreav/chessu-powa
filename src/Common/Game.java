/**
 * Contains interface for a game class.
 * Author(s): Michal Bucher (xbuche01), Karel Hanák (xhanak34)
 */

package Common;

import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

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
    boolean move(Tile from, Tile to, PieceColor playerTurn, PieceType piece);

    /**
     * Performs a move on the board if the move is possible, used for autoplay.
     * @param from original tile, one that a piece is moving from
     * @param to destination tile, one that a piece is moving to
     * @param playerTurn a color of the player that is making the move
     * @param piece chess piece type, this attribute is used for short notation
     * @return true if the moving piece can move to the destination tile, otherwise false
     */
    boolean autoMove(Tile from, Tile to, PieceColor playerTurn, PieceType piece);

    /**
     * Performs a fake move to determine whether or not it prevents a check.
     * @param from original tile, one a piece is moving from
     * @param to destination tile, one a piece is moving to
     * @param playerTurn player that takes their turn
     * @return true if a player is still in check after performing the move, otherwise false
     */
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
    Stack<BoardMove> getMoves();

    /**
     * Stores current stack of moves.
     */
    void SaveToUndoStack();

    /**
     * Removes moves after specified index from the move stack.
     * @param index index of a move
     */
    void TrimMoves(int index);
}
