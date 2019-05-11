package Pieces;

import Common.Tile;

/**
 * Represents a Rook chess piece.
 */
public class Rook implements Piece {
    private PieceColor color;
    private PieceType type;

    /**
     * Creates a new Rook piece object.
     * @param color piece color
     * @param type piece type
     */
    public Rook(PieceColor color, PieceType type) {
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
        int fromX = from.getCol();
        int fromY = from.getRow();
        int toX = to.getCol();
        int toY = to.getRow();
        int diffX = Math.abs(fromX - toX);
        int diffY = Math.abs(fromY - toY);

        if (diffX == 0 && diffY != 0) { //moving up or down
            if (fromY < toY) { //moving down
                for (int i = 1; i < diffY; i++) {
                    Tile current = tiles[fromY + i][fromX];
                    if (!current.isEmpty()) { //fields between from and to are not empty
                        return false;
                    }
                }
                if (to.isEmpty()) { //destination field is empty
                    return true;
                } else {
                    return (from.getPiece().getColor() != to.getPiece().getColor()); //check for color mismatch
                }
            } else { //moving up
                for (int i = 1; i < diffY; i++) {
                    Tile current = tiles[fromY - i][fromX];
                    if (!current.isEmpty()) {
                        return false;
                    }
                }
                if (to.isEmpty()) {
                    return true;
                } else {
                    return (from.getPiece().getColor() != to.getPiece().getColor());
                }
            }
        } else if (diffX != 0 && diffY == 0) { //moving left or right
            if (fromX < toX) { //moving right
                for (int i = 1; i < diffX; i++) {
                    Tile current = tiles[fromY][fromX+i];
                    if (!current.isEmpty()) {
                        return false;
                    }
                }
                if (to.isEmpty()) {
                    return true;
                } else {
                    return (from.getPiece().getColor() != to.getPiece().getColor());
                }
            } else { //moving left
                for (int i = 1; i < diffX; i++) {
                    Tile current = tiles[fromY][fromX-i];
                    if (!current.isEmpty()) {
                        return false;
                    }
                }
                if (to.isEmpty()) {
                    return true;
                } else {
                    return (from.getPiece().getColor() != to.getPiece().getColor());
                }
            }
        } else {
            return false;
        }
    }
}
