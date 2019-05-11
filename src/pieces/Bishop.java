package pieces;

import common.Tile;

public class Bishop implements Piece {
    private PieceColor color;
    private PieceType type;

    public Bishop(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public PieceColor getColor() {
        return color;
    }

    @Override
    public PieceType getType() {
        return type;
    }

    @Override
    public boolean isValidMovement(Tile from, Tile to, Tile[][] tiles) {
        return false;
    }
}