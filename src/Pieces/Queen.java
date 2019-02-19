package Pieces;

public class Queen extends Piece {

    public PieceType type;

    public Queen(PieceColor color, int row, char column) {
        super(color, row, column);
        this.type = PieceType.QUEEN;
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
