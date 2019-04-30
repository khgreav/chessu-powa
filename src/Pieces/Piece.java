package Pieces;

public interface Piece {
    boolean isValidMovement();
    PieceColor getColor();
    PieceType getType();
}
