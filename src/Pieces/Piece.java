package Pieces;

public abstract class  Piece {

    public PieceColor color;
    public int row;
    public char column;

    public Piece(PieceColor color, int row, char column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    public abstract String getPiece();

    public abstract String getCoordinates();

}
