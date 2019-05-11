package pieces;

import common.Tile;

public class King implements Piece {
    private PieceColor color;
    private PieceType type;

    public King(PieceColor color, PieceType type) {
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

        if ((diffX == 1 && diffY == 0) || (diffX == 0 && diffY == 1) || (diffX == 1 && diffY == 1)) { //valid movement
            if (to.isEmpty()) {
                return true;
            } else {
                return (from.getPiece().getColor() != to.getPiece().getColor());
            }
        } else {
            return false;
        }
    }
}
