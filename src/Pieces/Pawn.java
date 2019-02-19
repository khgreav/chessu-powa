package Pieces;

public class Pawn extends Piece {

    public PieceType type;

    public Pawn(PieceColor color, int row, char column) {
        super(color, row, column);
        this.type = PieceType.PAWN;
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
