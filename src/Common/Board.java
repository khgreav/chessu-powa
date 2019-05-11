package Common;

/**
 * Represents a chessboard with 8x8 tiles.
 */
public class Board {
    /**
     * An array of tiles on the board.
     */
    public Tile[][] tiles;

    /**
     * Creates a new Board object, creates the tile array.
     */
    public Board () {
        tiles = new Tile[8][8];
        setTiles();
    }

    /**
     * Initializes tiles and assigns them to the array tile.
     */
    private void setTiles() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                tiles[i][j] = new BoardTile(i, j);
            }
        }
    }

    /**
     * Returns the tile specified by row and column indices.
     * @param row integer value representing row of the requested tile
     * @param col integer value representing column of the requested tile
     * @return tile at specified row and column
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}
