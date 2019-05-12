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
    private Stack<BoardMove> moves;
    private Stack<Stack<BoardMove>> redo;
    private Stack<Stack<BoardMove>> undo;

    /**
     * Creates a new ChessGame object using a new board.
     * @param board board object to initialize the game on
     */
    public ChessGame(Board board) {
        this.board = board;
        this.moves = new Stack<>();
        this.redo = new Stack<>();
        this.undo = new Stack<>();

        this.redo.push((Stack<BoardMove>) moves.clone());
        this.undo.push((Stack<BoardMove>) moves.clone());
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
     * Returns the moves stack of the game. The stack contains all the moves that happened.
     * @return moves stack
     */
    @Override
    public Stack<BoardMove> getMoves() {
        return moves;
    }

    public void SaveToUndoStack() {
        undo.push((Stack<BoardMove>) moves.clone());
    }

    @Override
    public void TrimMoves(int index) {
        int max = moves.size()-2;
        while (index <= max) {
            moves.remove(index);
            max--;
        }
    }

    /**
     * Performs a move on the board if the move is possible.
     * @param from original tile, one that a figure is moving from
     * @param to destination tile, one that a figure is moving to
     * @param playerTurn a color of the player that is making the move
     * @return true if the moving figure can move to the destination tile, otherwise false
     */
    @Override
    public boolean move(Tile from, Tile to, PieceColor playerTurn, PieceType piece) {
        SaveToUndoStack();
        boolean ret =  autoMove(from, to, playerTurn, piece);
        return ret;
    }

    @Override
    public boolean autoMove(Tile from, Tile to, PieceColor playerTurn, PieceType piece) {
        if (from == null) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!board.getTile(i,j).isEmpty() && board.getTile(i,j).getPiece().getType() == piece && board.getTile(i,j).getPiece().getColor() == playerTurn) {
                        if (board.getTile(i,j).getPiece().isValidMovement(board.getTile(i,j), to, board.tiles)) {
                            from = board.getTile(i,j);
                        }
                    }
                }
            }
            if (from == null) {
                return false;
            }
        }

        if (!to.isEmpty() && to.getPiece().getType() == PieceType.KI) {
            return false;
        }
        PieceColor otherPlayer = (playerTurn == PieceColor.W) ? PieceColor.B : PieceColor.W;
        if (from.getPiece().isValidMovement(from, to, board.tiles)) {
            Piece movingPiece = from.getPiece();
            Piece removedPiece = null;
            from.removePiece();
            if (!to.isEmpty()) {
                removedPiece = to.getPiece();
                to.removePiece();
            }
            to.putPiece(movingPiece);
            if (check(board.tiles, otherPlayer)) {
                to.removePiece();
                to.putPiece(removedPiece);
                from.putPiece(movingPiece);
                return false;
            }
            moves.push(new BoardMove(from, to, movingPiece, removedPiece, check(board.tiles, playerTurn)));

            return true;
        }
        return false;
    }

    /**
     * Performs a fake move to determine whether or not it prevents a check.
     * @param from original tile, one a piece is moving from
     * @param to destination tile, one a piece is moving to
     * @param playerTurn player that takes their turn
     * @return true if a player is still in check after performing the move, otherwise false
     */
    public boolean moveCheck(Tile from, Tile to, PieceColor playerTurn) {
        PieceColor otherPlayer = (playerTurn == PieceColor.W) ? PieceColor.B : PieceColor.W;
        Piece movingPiece = from.getPiece();
        Piece removedPiece = null;
        from.removePiece();
        if (!to.isEmpty()) {
            removedPiece = to.getPiece();
            to.removePiece();
        }
        to.putPiece(movingPiece);
        if (check(board.tiles, otherPlayer)) {
            to.removePiece();
            to.putPiece(removedPiece);
            from.putPiece(movingPiece);
            return true;
        }
        to.removePiece();
        to.putPiece(removedPiece);
        from.putPiece(movingPiece);
        return false;
    }

    /**
     * Performs the moves action and reverts the most recent move made.
     * @return true if the moves action was performed, otherwise false
     */
    @Override
    public boolean undo() {
        if (undo.empty()) {
            return false;
        } else {
            redo.push((Stack<BoardMove>) moves.clone());
            moves = undo.pop();
            return true;
        }
    }

    /**
     * Performs the redo action and reverts the most recent moves action.
     * @return true if the redo action was performed, otherwise false
     */
    @Override
    public boolean redo() {
        if (redo.empty()) {
            return false;
        } else {
            undo.push((Stack<BoardMove>) moves.clone());
            moves = redo.pop();
            return true;
        }
    }
}
