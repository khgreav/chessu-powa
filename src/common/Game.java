package common;

public interface Game {
    boolean move(Tile from, Tile to);
    boolean undo();
    boolean redo();
}
