package common;

import pieces.Piece;

public class BoardMove {
    private Tile from;
    private Tile to;
    private Piece movingPiece;
    private Piece removedPiece;

    public BoardMove(Tile from, Tile to, Piece movingPiece, Piece removedPiece) {
        this.from = from;
        this.to = to;
        this.movingPiece = movingPiece;
        this.removedPiece = removedPiece;
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
}
