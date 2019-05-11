package common;

import pieces.PieceColor;

public interface Game {
    boolean move(Tile from, Tile to, PieceColor playerTurn);
    boolean undo();
    boolean redo();
}
