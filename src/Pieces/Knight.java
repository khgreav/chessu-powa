package Pieces;

public class Knight extends Piece {

    public PieceType type;

    public Knight(PieceColor color, int row, char column) {
        super(color, row, column);
        this.type = PieceType.KNIGHT;
    }

    @Override
    public String getPiece() {
        return(this.color.toString().substring(0,1)+this.type.toString().substring(0,1));
    }

    @Override
    public String getCoordinates() {
        return(this.column+Integer.toString(row));
    }
}
