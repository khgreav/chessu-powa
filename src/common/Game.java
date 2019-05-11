package common;

import java.util.Stack;

public interface Game {
    boolean move(Tile from, Tile to);
    boolean undo();
    boolean redo();

    Stack<BoardMove> getUndo();
}
