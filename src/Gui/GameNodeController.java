package Gui;

import common.Board;
import common.Game;
import common.GameFactory;
import common.Tile;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import pieces.Piece;
import pieces.PieceColor;

import static pieces.PieceColor.W;

public class GameNodeController {

    private int turn = 0;
    private boolean clicked = false;

    private String lastClicked = "";

    private Tile lastClickedTile;
    private Tile currentClickedTile;

    private Board gameBoard;
    private Game game;

    @FXML
    GridPane board = new GridPane();

    @FXML
    ScrollPane gameHistory = new ScrollPane();

    @FXML
    VBox gameHistoryContent = new VBox();

    public void initialize() {

        gameBoard = new Board();
        game = GameFactory.createChessGame(gameBoard);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                TilePane tile = new TilePane();

                final int r = i;
                final int c = j;

                if ((i + j) % 2 == 0) {
                    tile.setStyle("-fx-background-color: #bf9a56;");
                } else {
                    tile.setStyle("-fx-background-color: #a86321;");
                }

                tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        MovePiece(r, c);
                    }
                });

                board.add(tile, j, i);
            }
        }

        refreshTilePieceGraphic();
    }

    private void MovePiece(int r, int c) {
        Tile clickedTile = gameBoard.getTile(r, c);
        Piece piece = clickedTile.getPiece();

        refreshTileColors();

        if (piece != null && piece.getColor() == PieceColor.values()[turn % 2]) {
            lastClickedTile = clickedTile;

            board.getChildren().get(r * 8 + c).setStyle("-fx-background-color: #12bf24;");
        } else {
            if (lastClickedTile != null) {
                if (game.move(lastClickedTile, clickedTile)) {
                    refreshTilePieceGraphic();
                }
            }

            lastClickedTile = null;
        }
    }

    private Image getPieceImage(Piece piece) {

        if (piece.getColor() == W) {
            switch (piece.getType()) {
                case P:
                    return new Image(getClass().getResourceAsStream("PiecePictures/pawn_white.png"));
                case R:
                    return new Image(getClass().getResourceAsStream("PiecePictures/rook_white.png"));
                case B:
                    return new Image(getClass().getResourceAsStream("PiecePictures/bishop_white.png"));
                case KN:
                    return new Image(getClass().getResourceAsStream("PiecePictures/knight_white.png"));
                case Q:
                    return new Image(getClass().getResourceAsStream("PiecePictures/queen_white.png"));
                case KI:
                    return new Image(getClass().getResourceAsStream("PiecePictures/king_white.png"));

                default:
                    return null;

            }
        } else {
            switch (piece.getType()) {
                case P:
                    return new Image(getClass().getResourceAsStream("PiecePictures/pawn_black.png"));
                case R:
                    return new Image(getClass().getResourceAsStream("PiecePictures/rook_black.png"));
                case B:
                    return new Image(getClass().getResourceAsStream("PiecePictures/bishop_black.png"));
                case KN:
                    return new Image(getClass().getResourceAsStream("PiecePictures/knight_black.png"));
                case Q:
                    return new Image(getClass().getResourceAsStream("PiecePictures/queen_black.png"));
                case KI:
                    return new Image(getClass().getResourceAsStream("PiecePictures/king_black.png"));

                default:
                    return null;

            }

        }
    }

    private void refreshTilePieceGraphic() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Node n = board.getChildren().get(i * 8 + j);
                if (n instanceof TilePane) {
                    Piece p = gameBoard.getTile(i, j).getPiece();

                    ((TilePane) n).getChildren().remove(0, ((TilePane) n).getChildren().size());
                    if (p == null)
                        continue;

                    Image img = getPieceImage(p);
                    ImageView imageView = new ImageView(img);

                    double w = 0;
                    double h = 0;

                    double ratioX = imageView.getFitWidth() / img.getWidth();
                    double ratioY = imageView.getFitHeight() / img.getHeight();

                    double reducCoeff = 0;
                    if (ratioX >= ratioY) {
                        reducCoeff = ratioY;
                    } else {
                        reducCoeff = ratioX;
                    }

                    w = img.getWidth() * reducCoeff;
                    h = img.getHeight() * reducCoeff;

                    imageView.setX((imageView.getFitWidth() - w) / 2);
                    imageView.setY((imageView.getFitHeight() - h) / 2);


                    ((TilePane) n).getChildren().add(imageView);
                }
            }
        }
    }

    private void refreshTileColors() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Node n = board.getChildren().get(i * 8 + j);
                if (n instanceof TilePane) {
                    if ((i + j) % 2 == 0) {
                        n.setStyle("-fx-background-color: #bf9a56;");
                    } else {
                        n.setStyle("-fx-background-color: #a86321;");
                    }
                }
            }
        }
    }

    public void loadFile() {

    }

    public void saveFile() {

    }

    public void autoPlay() {

    }

    public void resetGame() {

    }

    public void pauseAutoPlay() {

    }

    public void prevMove() {

    }

    public void nextMove() {

    }
}
