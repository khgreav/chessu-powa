package pieces;

import common.Tile;

public interface Piece {
    boolean isValidMovement(Tile from, Tile to, Tile[][] tiles);
    PieceColor getColor();
    PieceType getType();
}
