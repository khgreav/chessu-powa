package pieces;

public class King implements Piece {
    private PieceColor color;
    private PieceType type;

    public King(PieceColor color, PieceType type) {
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
