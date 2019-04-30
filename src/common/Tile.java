package common;

import pieces.Piece;

public interface Tile {
    Piece getPiece();
    boolean putPiece(Piece piece);
    boolean removePiece();
    boolean isEmpty();
    int getCol();
    int getRow();
}
