package common;

public class Board {
    private Tile[][] tiles;

    public Board () {
        tiles = new Tile[8][8];
        setTiles();
    }

    private void setTiles() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                tiles[i][j] = new BoardTile(8-i, i+1);
            }
        }
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}