package Gui;

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

import java.awt.event.ActionEvent;

public class GameNodeController {

    private int moven = 0;
    private boolean clicked = false;

    private String lastClicked = "";

    @FXML
    GridPane board = new GridPane();

    @FXML
    ScrollPane gameHistory = new ScrollPane();

    @FXML
    VBox gameHistoryContent = new VBox();

    public void initialize() {
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
    }

    private void printTileToHistory(int r, int c) {

        if (clicked) {
            moven++;

            ImageView img = new ImageView(new Image(getClass().getResourceAsStream("Chess_BlackPawn.png")));
            Node n = board.getChildren().get(r*8+c);

            img.setFitWidth(((TilePane) n).getWidth());
            img.setFitHeight(((TilePane) n).getHeight());

            ((TilePane) n).getChildren().add(img);

            if (moven%2 == 0) {
                Button btn = new Button();
                btn.setText(moven/2 + " " + lastClicked + " " + r + ":" + c);
                gameHistoryContent.getChildren().add(btn);
            } else {
                lastClicked = r + ":" + c;
            }
            clicked = false;
        } else {
            clicked = true;
        }
    }

    private void refreshBoardGraphic() {

        board.getChildren().forEach(c -> {
            ImageView img = new ImageView(new Image(getClass().getResourceAsStream("Chess_BlackPawn.png")));
            if (c instanceof TilePane) {
                img.setFitWidth(((TilePane) c).getWidth());
                img.setFitHeight(((TilePane) c).getHeight());

                ((TilePane) c).getChildren().remove(0, ((TilePane) c).getChildren().size());
                ((TilePane) c).getChildren().add(img);
            }
        });
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
