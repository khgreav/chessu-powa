package Common;

import Pieces.Piece;

/**
 * Represents a single tile on the board. The tile knows it's coordinates and a figure occupying it.
 */
public class BoardTile implements Tile {
    private int row;
    private int col;
    private Piece piece;

    /**
     * Creates a new BoardTile object, that will be on the board.
     * @param row integer value specifying the row of a tile
     * @param col integer value specifying the column of a tile
     */
    public BoardTile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the piece occupying this tile if there is one.
     * @return piece if there is a piece on the tile, or null if there isn't one
     */
    @Override
    public Piece getPiece() {
        return piece;
    }

    /**
     * Puts a piece on the board tile if there is no piece occupying it.
     * @param piece piece to be placed on the tile
     * @return true if the tile was empty and a piece could be placed, false if it is already occupied
     */
    @Override
    public boolean putPiece(Piece piece) {
        if (this.isEmpty()) {
            this.piece = piece;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a piece from the board tile if there is one occupying it.
     * @return true if the tile was not empty and a piece could be removed, otherwise false
     */
    @Override
    public boolean removePiece() {
        if (this.isEmpty()) {
            return false;
        } else {
            this.piece = null;
            return true;
        }
    }

    /**
     * Checks if the tile is empty or being occupied by a piece.
     * @return true if the tile is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return (this.piece == null);
    }

    /**
     * Returns an integer value representing the column of the tile.
     * @return column number
     */
    @Override
    public int getCol() {
        return col;
    }

    /**
     * Returns and integer value representing the row of the tile.
     * @return row number
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Converts the column and row values into a string and returns it.
     * @return string containing column and row of the tile
     */
    @Override
    public String toString() {
        return ((char)('a' + col)) + "" + (char)(('8' - row));
    }
}
