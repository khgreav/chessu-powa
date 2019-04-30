package Gui;

import common.Tile;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import java.awt.event.ActionEvent;

class CustomEvent extends Event {

    private String parameter;

    public static final EventType<CustomEvent> CUSTOM = new EventType(ANY, "CUSTOM");

    public CustomEvent(String parameter) {
        super(CustomEvent.CUSTOM);
        this.parameter = parameter;
    }

    public String getParameter() {
        return this.parameter;
    }

}

public class GameNodeController {

    int moven = 0;

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
                    tile.setStyle("-fx-background-color: #999922;");
                } else {
                    tile.setStyle("-fx-background-color: #992299;");
                }

                tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        printTileToHistory(moven, r, c);
                    }
                });

                board.add(tile, i, j);
            }
        }
    }

    public void printTileToHistory(int moven, int r, int c) {
        moven++;

        Button btn = new Button();
        btn.setText(moven + ". " + r + ":" + c);

        gameHistoryContent.getChildren().add(btn);

        refreshBoardGraphic();
    }

    private void refreshBoardGraphic() {

        board.getChildren().forEach(c -> {

            ImageView img = new ImageView(new Image(getClass().getResourceAsStream("Chess_BlackPawn.png")));
            //img.setPreserveRatio(true);

           if (c instanceof TilePane)
               img.setFitWidth(((TilePane) c).getTileWidth());
               img.setFitHeight(((TilePane) c).getTileHeight());

            img.setFitWidth(((TilePane) c).getWidth());
            img.setFitHeight(((TilePane) c).getHeight());

               ((TilePane) c).getChildren().removeAll();
                ((TilePane) c).getChildren().add(img);

        });
    }
}
