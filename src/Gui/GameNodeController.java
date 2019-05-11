package Gui;

import common.Board;
import common.Game;
import common.GameFactory;
import common.Tile;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import pieces.Piece;

import java.awt.event.ActionEvent;

import static pieces.PieceColor.W;

public class GameNodeController {

    private int moven = 0;
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
                        printTileToHistory(r, c);
                    }
                });

                board.add(tile, i, j);
            }
        }

        refreshBoardGraphic();
    }

    private void printTileToHistory(int r, int c) {

        if (clicked) {
            moven++;
//
//            ImageView img = new ImageView(new Image(getClass().getResourceAsStream("Chess_BlackPawn.png")));
//            Node n = board.getChildren().get(r * 8 + c);
//
//            img.setFitWidth(((TilePane) n).getWidth());
//            img.setFitHeight(((TilePane) n).getHeight());
//
//            ((TilePane) n).getChildren().add(img);

            if (moven % 2 == 0) {
//                Button btn = new Button();
//                btn.setText(moven / 2 + " " + lastClicked + " " + r + ":" + c);
//                gameHistoryContent.getChildren().add(btn);
            } else {
//                lastClicked = r + ":" + c;
                lastClickedTile = gameBoard.getTile(r, c);
            }
            clicked = false;
        } else {
            clicked = true;
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

    private void refreshBoardGraphic() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Node n = board.getChildren().get(i * 8 + j);
                if (n instanceof TilePane) {
                    Piece p = gameBoard.getTile(j, i).getPiece();

                    if (p == null)
                        continue;

                    Image img = getPieceImage(p);
                    ImageView imageView = new ImageView(img);

                    double w = 0;
                    double h = 0;

                    double ratioX = imageView.getFitWidth() / img.getWidth();
                    double ratioY = imageView.getFitHeight() / img.getHeight();

                    double reducCoeff = 0;
                    if(ratioX >= ratioY) {
                        reducCoeff = ratioY;
                    } else {
                        reducCoeff = ratioX;
                    }

                    w = img.getWidth() * reducCoeff;
                    h = img.getHeight() * reducCoeff;

                    imageView.setX((imageView.getFitWidth() - w) / 2);
                    imageView.setY((imageView.getFitHeight() - h) / 2);

                    ((TilePane) n).getChildren().remove(0, ((TilePane) n).getChildren().size());
                    ((TilePane) n).getChildren().add(imageView);
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
