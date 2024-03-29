/**
 * Contains implementation of a pawn chess piece.
 * Author(s): Michal Bucher (xbuche01), Karel Hanák (xhanak34)
 */

package Pieces;

import Common.Tile;

/**
 * Represents a Pawn chess piece.
 */
public class Pawn implements Piece {
    private PieceColor color;
    private PieceType type;

    /**
     * Creates a new Pawn piece object.
     * @param color piece color
     * @param type piece type
     */
    public Pawn(PieceColor color, PieceType type) {
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

        if (diffX == 0 && diffY == 1) { //move by one tile forward
            Piece movingPiece = from.getPiece();
            if (movingPiece.getColor() == PieceColor.W) { //white pawn
                if (fromY - 1 == toY) { //moving up
                    return to.isEmpty();
                } else {
                    return false;
                }
            } else { //black pawn
                if (fromY + 1 == toY) { //moving down
                    return to.isEmpty();
                } else {
                    return false;
                }
            }
        } else if (diffX == 0 && diffY == 2) { //move by two tiles forward
            Piece movingPiece = from.getPiece();
            if (movingPiece.getColor() == PieceColor.W) { //white pawn
                if (fromY == 6 && fromY - 2 == toY) { //pawn is in the starting position
                    return tiles[toY+1][fromX].isEmpty() && to.isEmpty(); //both tiles are empty
                } else {
                    return false;
                }
            } else { //black pawn
                if (fromY == 1 && fromY + 2 == toY) { //pawn is in the starting position
                    return tiles[toY-1][fromX].isEmpty() && to.isEmpty(); //both tiles are empty
                } else {
                    return false;
                }
            }
        } else if (diffX == 1 && diffY == 1) { //attack
            Piece movingPiece = from.getPiece();
            if (movingPiece.getColor() == PieceColor.W) { //white pawn
                if (fromY - 1 == toY) { //moving up
                    if (to.isEmpty()) {
                        return false;
                    } else {
                        return (to.getPiece().getColor() == PieceColor.B);
                    }
                } else { // moving down
                    return false;
                }
            } else { //black pawn
                if (fromY + 1 == toY) { //moving down
                    if(to.isEmpty()) {
                        return false;
                    } else {
                        return (to.getPiece().getColor() == PieceColor.W);
                    }
                } else {
                    return false;
                }
            }
        } else { //invalid movement
            return false;
        }
    }
}
