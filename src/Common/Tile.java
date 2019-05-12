/**
 * Contains interface for a board tile class.
 * Author(s): Michal Bucher (xbuche01), Karel Hanák (xhanak34)
 */

package Common;

import Pieces.Piece;

/**
 * Interface for a board tile class.
 */
public interface Tile {

    /**
     * Returns the piece occupying this tile if there is one.
     * @return piece if there is a piece on the tile, or null if there isn't one
     */
    Piece getPiece();

    /**
     * Puts a piece on the board tile if there is no piece occupying it.
     * @param piece piece to be placed on the tile
     * @return true if the tile was empty and a piece could be placed, false if it is already occupied
     */
    boolean putPiece(Piece piece);

    /**
     * Removes a piece from the board tile if there is one occupying it.
     * @return true if the tile was not empty and a piece could be removed, otherwise false
     */
    boolean removePiece();

    /**
     * Checks if the tile is empty or being occupied by a piece.
     * @return true if the tile is empty, otherwise false
     */
    boolean isEmpty();

    /**
     * Returns an integer value representing the column of the tile.
     * @return column number
     */
    int getCol();

    /**
     * Returns and integer value representing the row of the tile.
     * @return row number
     */
    int getRow();

    /**
     * Converts the column and row values into a string and returns it.
     * @return string containing column and row of the tile
     */
    String toString();
}
