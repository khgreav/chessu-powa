package common;

import pieces.Piece;

public class BoardTile implements Tile {
    private int row;
    private int col;
    private Piece piece;

    public BoardTile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean putPiece(Piece piece) {
        if (this.isEmpty()) {
            this.piece = piece;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removePiece() {
        if (this.isEmpty()) {
            return false;
        } else {
            this.piece = null;
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.piece == null);
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRow() {
        return row;
    }
}
