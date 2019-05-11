package common;

import pieces.Piece;

public class BoardMove {
    private Tile from;
    private Tile to;
    private Piece movingPiece;
    private Piece removedPiece;
    private boolean check;

    public BoardMove(Tile from, Tile to, Piece movingPiece, Piece removedPiece, boolean check) {
        this.from = from;
        this.to = to;
        this.movingPiece = movingPiece;
        this.removedPiece = removedPiece;
        this.check = check;
    }

    public Tile getFrom() {
        return from;
    }

    public Tile getTo() {
        return to;
    }

    public Piece getMovingFigure() {
        return movingPiece;
    }

    public Piece getRemovedFigure() {
        return removedPiece;
    }

    public boolean isCheck() {
        return check;
    }
}
