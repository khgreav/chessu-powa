package Common;

import Pieces.Piece;
import Pieces.PieceColor;
import Pieces.PieceType;

import java.util.Stack;

/**
 * Represents an instance of chess game.
 */
public class ChessGame implements Game {
    private Board board;
    private Stack<BoardMove> undo;
    private Stack<BoardMove> redo;
    private boolean check;

    /**
     * Creates a new ChessGame object using a new board.
     * @param board board object to initialize the game on
     */
    public ChessGame(Board board) {
        this.board = board;
        this.undo = new Stack<>();
        this.redo = new Stack<>();
        this.check = false;
    }

    /**
     * Analyzes the board state after a player takes their turn to find out whether or not the turn imposed a check on the opponent.
     * @param tiles an array of tiles
     * @param playerTurn color of the player that took this turn
     * @return true if the turn imposed a check on the opponent, otherwise false
     */
    public boolean check(Tile[][] tiles, PieceColor playerTurn) {
        Tile kingTile = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!tiles[j][i].isEmpty())
                    if ((tiles[j][i].getPiece().getType() == PieceType.KI) && (tiles[j][i].getPiece().getColor() != playerTurn)) {
                        kingTile = tiles[j][i];
                    }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!tiles[j][i].isEmpty() && tiles[j][i].getPiece().getColor() == playerTurn) {
                    if (tiles[j][i].getPiece().isValidMovement(tiles[j][i], kingTile, tiles)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the undo stack of the game. The stack contains all the moves that happened.
     * @return undo stack
     */
    @Override
    public Stack<BoardMove> getUndo() {
        return undo;
    }

    /**
     * Performs a move on the board if the move is possible.
     * @param from original tile, one that a figure is moving from
     * @param to destination tile, one that a figure is moving to
     * @param playerTurn a color of the player that is making the move
     * @return true if the moving figure can move to the destination tile, otherwise false
     */
    @Override
    public boolean move(Tile from, Tile to, PieceColor playerTurn) {
        if (!to.isEmpty() && to.getPiece().getType() == PieceType.KI) {
            return false;
        }
        if (from.getPiece().isValidMovement(from, to, board.tiles)) {
            Piece movingPiece = from.getPiece();
            Piece removedPiece = null;
            from.removePiece();
            if (!to.isEmpty()) {
                removedPiece = to.getPiece();
                to.removePiece();
            }
            to.putPiece(movingPiece);
            undo.push(new BoardMove(from, to, movingPiece, removedPiece, check(board.tiles, playerTurn)));
            return true;
        }
        return false;
    }

    /**
     * Performs the undo action and reverts the most recent move made.
     * @return true if the undo action was performed, otherwise false
     */
    @Override
    public boolean undo() {
        if (undo.empty()) {
            return false;
        } else {
            BoardMove undoMove = undo.pop();
            Tile original = board.getTile(undoMove.getFrom().getRow() - 1, undoMove.getFrom().getCol() - 1);
            Tile destination = board.getTile(undoMove.getTo().getRow() - 1, undoMove.getTo().getCol() - 1);
            destination.removePiece();
            if (undoMove.getRemovedPiece() != null) {
                destination.putPiece(undoMove.getRemovedPiece());
            }
            original.putPiece(undoMove.getMovingPiece());
            redo.push(undoMove);
            return true;
        }
    }

    /**
     * Performs the redo action and reverts the most recent undo action.
     * @return true if the redo action was performed, otherwise false
     */
    @Override
    public boolean redo() {
        if (redo.empty()) {
            return false;
        } else {
            BoardMove redoMove = redo.pop();
            Tile original = board.getTile(redoMove.getFrom().getRow() - 1, redoMove.getFrom().getCol() - 1);
            Tile destination = board.getTile(redoMove.getTo().getRow() - 1, redoMove.getTo().getCol() - 1);
            original.removePiece();
            if (redoMove.getRemovedPiece() != null) {
                destination.removePiece();
            }
            destination.putPiece(redoMove.getMovingPiece());
            undo.push(redoMove);
            return true;
        }
    }
}
