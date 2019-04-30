package pieces;

public class Pawn implements Piece {
    private PieceColor color;
    private PieceType type;

    public Pawn(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public PieceColor getColor() {
        return color;
    }

    @Override
    public PieceType getType() {
        return type;
    }

    @Override
    public boolean isValidMovement() {
        return false;
    }
}
