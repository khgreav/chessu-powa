package common;

public class Board {
    public Tile[][] tiles;

    public Board () {
        tiles = new Tile[8][8];
        setTiles();
    }

    private void setTiles() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                tiles[i][j] = new BoardTile(i, j);
            }
        }
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}
