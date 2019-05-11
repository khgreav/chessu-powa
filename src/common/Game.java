package common;

import pieces.PieceColor;

import java.util.Stack;

public interface Game {
    boolean move(Tile from, Tile to, PieceColor playerTurn);
    boolean undo();
    boolean redo();

    Stack<BoardMove> getUndo();
}
