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
        int fromX = from.getCol();
        int fromY = from.getRow();
        int toX = to.getCol();
        int toY = to.getRow();
        int diffX = Math.abs(fromX - toX);
        int diffY = Math.abs(fromY - toY);

        if ((diffX == diffY) && diffX != 0) { //bishop moves to a valid tile
            if (fromX < toX && fromY < toY) { //moving down and right
                for (int i = 1; i < diffX; i++) {
                    Tile current = tiles[fromY+i][fromX+i];
                    if (!current.isEmpty()) {
                        return false;
                    }
                }
                if (to.isEmpty()) {
                    return true;
                } else {
                    return (from.getPiece().getColor() != to.getPiece().getColor());
                }
            } else if (fromX > toX && fromY < toY) { //moving down and left
                for (int i = 1; i < diffX; i++) {
                    Tile current = tiles[fromY+i][fromX-i];
                    if (!current.isEmpty()) {
                        return false;
                    }
                }
                if (to.isEmpty()) {
                    return true;
                } else {
                    return (from.getPiece().getColor() != to.getPiece().getColor());
                }
            } else if (fromX < toX && fromY > toY) { //moving up and right
                for (int i = 1; i < diffX; i++) {
                    Tile current = tiles[fromY-i][fromX+i];
                    if (!current.isEmpty()) {
                        return false;
                    }
                }
                if (to.isEmpty()) {
                    return true;
                } else {
                    return (from.getPiece().getColor() != to.getPiece().getColor());
                }
            } else if (fromX > toX && fromY > toY) { //moving up and left
                for (int i = 1; i < diffX; i++) {
                    Tile current = tiles[fromY-i][fromY-i];
                    if (!current.isEmpty()){
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
        return false;
    }
}
