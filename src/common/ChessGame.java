package common;

import java.util.Stack;

public class ChessGame implements Game {
    private Board board;
    private Stack<BoardMove> undo;
    private Stack<BoardMove> redo;

    public ChessGame(Board board) {
        this.board = board;
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    @Override
    public boolean move(Tile from, Tile to) {
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
        if(redo.empty()) {
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
