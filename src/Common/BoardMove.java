package Common;

import Pieces.Piece;

/**
 * Represents a single move made in the game by any player.
 * Whenever a player takes their turn, a BoardMove object is created.
 */
public class BoardMove {
    private Tile from;
    private Tile to;
    private Piece movingPiece;
    private Piece removedPiece;
    private boolean check;

    /**
     * Creates a new BoardMove object that stores information about a player's turn.
     * @param from a tile that a piece was moving from
     * @param to a tile that a piece is moving to
     * @param movingPiece a piece that moved
     * @param removedPiece a piece that was removed from the game as a result of this move, if any
     * @param check specifies if this turn imposed check on the opposing player
     */
    public BoardMove(Tile from, Tile to, Piece movingPiece, Piece removedPiece, boolean check) {
        this.from = from;
        this.to = to;
        this.movingPiece = movingPiece;
        this.removedPiece = removedPiece;
        this.check = check;
    }

    /**
     * Returns the original tile, one that a piece was moving from.
     * @return original tile
     */
    public Tile getFrom() {
        return from;
    }

    /**
     * Returns the destination tile, one that a piece was moving to.
     * @return destination tile
     */
    public Tile getTo() {
        return to;
    }

    /**
     * Returns the moving piece.
     * @return moving piece
     */
    public Piece getMovingPiece() {
        return movingPiece;
    }

    /**
     * Returns the removed piece, if there was one at the destination tile.
     * @return removed piece or null if there was no piece at the destination tile
     */
    public Piece getRemovedPiece() {
        return removedPiece;
    }

    /**
     * Returns a boolean value specifying if a player's turn imposes check on their opponent.
     * @return true if check is imposed on the opponent, otherwise false
     */
    public boolean isCheck() {
        return check;
    }
}
