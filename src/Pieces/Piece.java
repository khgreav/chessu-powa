package Pieces;

import Common.Tile;

/**
 * An interface for a chess piece object.
 */
public interface Piece {
    /**
     * Checks if a move from the original tile to a destination tile is possible for the respective chess piece.
     * @param from original tile, one that a piece is moving from
     * @param to destination tile, one that a piece is moving to
     * @param tiles array of tiles
     * @return true if the moving piece can perform this move by the rules, otherwise false
     */
    boolean isValidMovement(Tile from, Tile to, Tile[][] tiles);

    /**
     * Returns the color of the piece.
     * @return color of the piece
     */
    PieceColor getColor();

    /**
     * Returns the type of the piece.
     * @return type of the piece
     */
    PieceType getType();
}
