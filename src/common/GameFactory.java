package common;

import pieces.*;

public abstract class GameFactory {
    public GameFactory() {

    }

    public static Game createChessGame(Board board) {
        for(int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                board.getTile(0, i).putPiece(new Rook(PieceColor.B, PieceType.R));
                board.getTile(7, i).putPiece(new Rook(PieceColor.W, PieceType.R));
            } else if (i == 1 || i == 6) {
                board.getTile(0, i).putPiece(new Knight(PieceColor.B, PieceType.KN));
                board.getTile(7, i).putPiece(new Knight(PieceColor.W, PieceType.KN));
            } else if (i == 2 || i == 5) {
                board.getTile(0, i).putPiece(new Bishop(PieceColor.B, PieceType.B));
                board.getTile(7, i).putPiece(new Bishop(PieceColor.W, PieceType.B));
            } else if (i == 3) {
                board.getTile(0, i).putPiece(new Queen(PieceColor.B, PieceType.Q));
                board.getTile(7, i).putPiece(new Queen(PieceColor.W, PieceType.Q));
            } else {
                board.getTile(0, i).putPiece(new King(PieceColor.B, PieceType.KI));
                board.getTile(7, i).putPiece(new King(PieceColor.W, PieceType.KI));
            }
            board.getTile(1, i).putPiece(new Pawn(PieceColor.B, PieceType.P));
            board.getTile(6, i).putPiece(new Pawn(PieceColor.W, PieceType.P));
        }
        return new ChessGame(board);
    }
}
