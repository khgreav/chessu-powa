import Pieces.*;

public class Board {

    public Piece[][] tiles = new Piece[8][8];

    public Board() {
        setDefaultBoardState();
    }

    public void setDefaultBoardState() {

        char c = 'a';

        tiles[0][0] = new Rook(PieceColor.BLACK, 8, 'a');
        tiles[0][7] = new Rook(PieceColor.BLACK, 8, 'h');
        tiles[0][1] = new Knight(PieceColor.BLACK, 8, 'b');
        tiles[0][6] = new Knight(PieceColor.BLACK, 8, 'g');
        tiles[0][2] = new Bishop(PieceColor.BLACK, 8, 'c');
        tiles[0][5] = new Bishop(PieceColor.BLACK, 8, 'f');
        tiles[0][3] = new Queen(PieceColor.BLACK, 8, 'd');
        tiles[0][4] = new King(PieceColor.BLACK, 8, 'e');

        tiles[7][0] = new Rook(PieceColor.WHITE, 1, 'a');
        tiles[7][7] = new Rook(PieceColor.WHITE, 1, 'h');
        tiles[7][1] = new Knight(PieceColor.WHITE, 1, 'b');
        tiles[7][6] = new Knight(PieceColor.WHITE, 1, 'g');
        tiles[7][2] = new Bishop(PieceColor.WHITE, 1, 'c');
        tiles[7][5] = new Bishop(PieceColor.WHITE, 1, 'f');
        tiles[7][3] = new Queen(PieceColor.WHITE, 1, 'd');
        tiles[7][4] = new King(PieceColor.WHITE, 1, 'e');


        for (int i = 0; i < 8; i++) {
            tiles[1][i] = new Pawn(PieceColor.BLACK, 7, c);
            tiles[6][i] = new Pawn(PieceColor.WHITE, 2, c++);
        }
    }
}
