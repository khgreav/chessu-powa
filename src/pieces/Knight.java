package pieces;

import common.Tile;

public class Knight implements Piece {
    private PieceColor color;
    private PieceType type;

    public Knight(PieceColor color, PieceType type) {
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

        if ((diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1)) {
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
