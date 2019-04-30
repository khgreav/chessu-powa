package pieces;

public interface Piece {
    boolean isValidMovement();
    PieceColor getColor();
    PieceType getType();
}
