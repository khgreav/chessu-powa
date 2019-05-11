package common;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;

import java.util.Stack;

public class ChessGame implements Game {
    private Board board;
    private Stack<BoardMove> undo;

    private Stack<BoardMove> redo;
    private boolean check;

    public ChessGame(Board board) {
        this.board = board;
        this.undo = new Stack<>();
        this.redo = new Stack<>();
        this.check = false;
    }

    private boolean check(Tile[][] tiles, PieceColor playerTurn) {
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

    @Override
    public Stack<BoardMove> getUndo() {
        return undo;
    }

    @Override
    public boolean move(Tile from, Tile to, PieceColor playerTurn) {
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

    @Override
    public boolean undo() {
        if (undo.empty()) {
            return false;
        } else {
            BoardMove undoMove = undo.pop();
            Tile original = board.getTile(undoMove.getFrom().getRow() - 1, undoMove.getFrom().getCol() - 1);
            Tile destination = board.getTile(undoMove.getTo().getRow() - 1, undoMove.getTo().getCol() - 1);
            destination.removePiece();
            if (undoMove.getRemovedFigure() != null) {
                destination.putPiece(undoMove.getRemovedFigure());
            }
            original.putPiece(undoMove.getMovingFigure());
            redo.push(undoMove);
            return true;
        }
    }

    @Override
    public boolean redo() {
        if (redo.empty()) {
            return false;
        } else {
            BoardMove redoMove = redo.pop();
            Tile original = board.getTile(redoMove.getFrom().getRow() - 1, redoMove.getFrom().getCol() - 1);
            Tile destination = board.getTile(redoMove.getTo().getRow() - 1, redoMove.getTo().getCol() - 1);
            original.removePiece();
            if (redoMove.getRemovedFigure() != null) {
                destination.removePiece();
            }
            destination.putPiece(redoMove.getMovingFigure());
            undo.push(redoMove);
            return true;
        }
    }
}
