package Gui;

import Common.*;
import Pieces.PieceType;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import Pieces.Piece;
import Pieces.PieceColor;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static Pieces.PieceColor.W;

public class GameNodeController {

    @FXML
    public Spinner<Integer> fidgetSpinner;

    private Timer autoPlayTimer;

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

    /**
     * Initializes the gameNode controller and creates own game instance.
     * Sets the basic game layout and adds triggers for each tile in board.
     */
    public void initialize() {

        gameBoard = new Board();
        game = GameFactory.createChessGame(gameBoard);

        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50000, 1000);

        fidgetSpinner.setValueFactory(valueFactory);

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

    private boolean isCheckMate(PieceColor playerTurn) {
        int validMoveCounter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile currentTile = null;
                if (!gameBoard.getTile(i,j).isEmpty() && gameBoard.getTile(i,j).getPiece().getColor() == playerTurn) {
                    currentTile = gameBoard.getTile(i,j);
                } else {
                    continue;
                }
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if (!gameBoard.getTile(k,l).isEmpty() && gameBoard.getTile(k,l).getPiece().getType() == PieceType.KI) {
                            continue;
                        }
                        if (!currentTile.getPiece().isValidMovement(currentTile, gameBoard.getTile(k,l), gameBoard.tiles)) {
                            continue;
                        }
                        if (game.moveCheck(currentTile, gameBoard.getTile(k,l), currentTile.getPiece().getColor())) {
                            continue;
                        } else {
                            validMoveCounter++;
                        }
                    }
                }
            }
        }
        return (validMoveCounter == 0);
    }

    /**
     * Function for checking which tile was clicked and if there is piece that can move somewhere.
     * If clicked again when there was previous tile successfully selected. Calls Game fucntion move to move it's piece.
     *
     * @param r int value of row in grid
     * @param c int value of col in grid
     */
    private void MovePiece(int r, int c) {
        if (isCheckMate(PieceColor.values()[turn % 2])) {
            System.out.println("Checkmate");
        }
        Tile clickedTile = gameBoard.getTile(r, c);
        Piece piece = clickedTile.getPiece();

        refreshTileColors();

        if (piece != null && piece.getColor() == PieceColor.values()[turn % 2]) {
            lastClickedTile = clickedTile;
            board.getChildren().get(r * 8 + c).setStyle("-fx-background-color: #12bf24;");

            markTileColors(lastClickedTile);
        } else {
            if (lastClickedTile != null) {
                if (game.move(lastClickedTile, clickedTile, PieceColor.values()[turn % 2], PieceType.P)) {
                    turn++;
                    refreshTilePieceGraphic();
                    refreshTileColors();
                    refreshHistory();
                }
            }

            lastClickedTile = null;
        }
    }

    /**
     * Simple function to get the correct image. For piece type and color.
     *
     * @param piece Piece value for getting the image.
     * @return Returns image of piece.
     */
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

    /**
     * Sets the picture according to piece type and color for each tile.
     */
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

    /**
     * Marks the tiles where the piece can move to.
     *
     * @param from Starting tile.
     */
    private void markTileColors(Tile from) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Node n = board.getChildren().get(i * 8 + j);
                if (n instanceof TilePane) {
                    if (from.getPiece().isValidMovement(from, gameBoard.getTile(i, j), gameBoard.tiles)) {
                        if ((i + j) % 2 == 0) {
                            if (!gameBoard.getTile(i, j).isEmpty() && gameBoard.getTile(i, j).getPiece().getType() == PieceType.KI) {
                                continue;
                            } else if (game.moveCheck(from, gameBoard.getTile(i, j), from.getPiece().getColor())) {
                                continue;
                            } else {
                                n.setStyle("-fx-background-color: #e4bf55;");
                            }
                        } else {
                            if (!gameBoard.getTile(i, j).isEmpty() && gameBoard.getTile(i, j).getPiece().getType() == PieceType.KI) {
                                continue;
                            } else if (game.moveCheck(from, gameBoard.getTile(i, j), from.getPiece().getColor())) {
                                continue;
                            } else {
                                n.setStyle("-fx-background-color: #c37f21;");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets the basic tile colors.
     */
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

    /**
     * Appends the list of moves to history.
     */
    private void refreshHistory() {
        Stack<BoardMove> moves = game.getMoves();

        gameHistoryContent.getChildren().remove(0, gameHistoryContent.getChildren().size());

        for (int i = 0; i < moves.size(); i += 2) {
            String str = (i + 1) + ". " + moves.elementAt(i).getFrom().toString();

            if (moves.elementAt(i).getRemovedPiece() != null)
                str += "x";

            str += "" + moves.elementAt(i).getTo().toString();

            if (moves.elementAt(i).isCheck()) {
                str += "+";
            }

            if (i + 1 < moves.size()) {
                str += " " + moves.elementAt(i + 1).getFrom().toString();

                if (moves.elementAt(i + 1).getRemovedPiece() != null)
                    str += "x";

                str += "" + moves.elementAt(i + 1).getTo().toString();

                if (moves.elementAt(i + 1).isCheck()) {
                    str += "+";
                }
            }


            Button button = new Button();

            if (((turn - 1) / 2) == i / 2)
                button.setStyle("-fx-background-color: #e5792a;");

            button.setText(str);

            gameHistoryContent.getChildren().add(button);
        }
    }

    /**
     * Shows dialog if the file loading fails
     */
    private void failedToLoadFile() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(board.getScene().getWindow());

        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Invalid input file!"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     *  Loads the file and calls the notation parser.
     *  Tries to play the game.
     *
     * @throws FileNotFoundException
     */
    public void loadFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load game");
        File file = fileChooser.showOpenDialog(board.getScene().getWindow());

        if (file == null)
            return;


        List<DoubleParsedNotation> movesLikeJagger = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            stream.forEach(l -> {
                try {
                    movesLikeJagger.add(NotationMoveParser.Parse(l));
                } catch (Exception e) {
                    failedToLoadFile();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            movesLikeJagger.forEach(m -> {
                Tile from = gameBoard.getTile(m.getWhite().getFromr(), m.getWhite().getFromc());
                Tile to = gameBoard.getTile(m.getWhite().getTor(), m.getWhite().getToc());
                game.move(from, to, PieceColor.W, m.getWhite().getType());

                if (m.getBlack() != null) {
                    from = gameBoard.getTile(m.getBlack().getFromr(), m.getBlack().getFromc());
                    to = gameBoard.getTile(m.getBlack().getTor(), m.getBlack().getToc());
                    game.move(from, to, PieceColor.B, m.getBlack().getType());
                }
            });
        } catch (Exception e) {
            failedToLoadFile();
        }

        resetGame();
    }

    /**
     * Saves the game to file
     */
    public void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load game");
        File file = fileChooser.showSaveDialog(board.getScene().getWindow());

        if (file == null)
            return;

        try {
            Stack<BoardMove> moves = game.getMoves();
            FileWriter fw = new FileWriter(file.getPath());

            for (int i = 0; i < moves.size(); i += 2) {
                String str = (i + 2) / 2 + ". " + NotationMoveParser.getSignFromPieceType(moves.elementAt(i).getMovingPiece().getType()) + "" + moves.elementAt(i).getFrom().toString();

                if (moves.elementAt(i).getRemovedPiece() != null)
                    str += "x";

                str += "" + moves.elementAt(i).getTo().toString();

                if (moves.elementAt(i).isCheck()) {
                    str += "+";
                }

                if (i + 1 < moves.size()) {
                    str += " " + NotationMoveParser.getSignFromPieceType(moves.elementAt(i).getMovingPiece().getType()) + "" + moves.elementAt(i + 1).getFrom().toString();

                    if (moves.elementAt(i + 1).getRemovedPiece() != null)
                        str += "x";

                    str += "" + moves.elementAt(i + 1).getTo().toString();

                    if (moves.elementAt(i + 1).isCheck()) {
                        str += "+";
                    }
                }
                fw.write(str + "\r\n");
            }
            fw.close();
        } catch (IOException ignored) {

        }
    }

    /**
     * Resets the game state to turn 1.
     */
    public void resetGame() {
        Stack<BoardMove> moves = game.getMoves();
        turn = moves.size();

        while (turn > 0) {
            prevMove();
        }

        refreshTileColors();
        refreshHistory();
        refreshTilePieceGraphic();
    }

    /**
     * Goes back one move in history
     */
    public void prevMove() {
        Stack<BoardMove> moves = game.getMoves();

        if (turn > 0) {
            turn--;

            BoardMove move = moves.elementAt(turn);
            move.getFrom().putPiece(move.getMovingPiece());
            move.getTo().removePiece();

            if (move.getRemovedPiece() != null)
                move.getTo().putPiece(move.getRemovedPiece());

            refreshTileColors();
            refreshHistory();
            refreshTilePieceGraphic();
        }

    }

    /**
     * Makes one move in history.
     */
    public void nextMove() {
        Stack<BoardMove> moves = game.getMoves();
        if (turn < moves.size()) {
            BoardMove move = moves.elementAt(turn);

            move.getTo().removePiece();
            move.getTo().putPiece(move.getMovingPiece());
            move.getFrom().removePiece();

            turn++;

            refreshTileColors();
            refreshHistory();
            refreshTilePieceGraphic();
        }
    }

    /**
     * Starts the automatic play.
     *
     */
    public void startAutoPlay() {
        if (autoPlayTimer != null) {
            return;
        }
        autoPlayTimer = new Timer();

        TimerTask autoPlayTimerTask = new TimerTask() {
            @Override
            public void run() {
                nextMoveAutoPlay();

                Stack<BoardMove> moves = game.getMoves();
                if (turn == moves.size()) {
                    autoPlayTimer.cancel();
                    autoPlayTimer = null;

                }

            }
        };

        autoPlayTimer.scheduleAtFixedRate(autoPlayTimerTask, 0, fidgetSpinner.valueProperty().get());
    }

    /**
     * Support function for autoplay.
     */
    private void nextMoveAutoPlay() {
        Stack<BoardMove> moves = game.getMoves();
        if (turn < moves.size()) {
            BoardMove move = moves.elementAt(turn);

            move.getTo().removePiece();
            move.getTo().putPiece(move.getMovingPiece());
            move.getFrom().removePiece();

            turn++;

            Platform.runLater(
                    () -> {
                        refreshTileColors();
                        refreshHistory();
                        refreshTilePieceGraphic();
                    }
            );
        }
    }

    /**
     * Turns off the autoplay.
     */
    public void pauseAutoPlay() {
        if (autoPlayTimer != null)
            autoPlayTimer.cancel();
        autoPlayTimer = null;
    }

    /**
     * Removes one move.
     */
    public void undo() {
        resetGame();

        game.undo();

        refreshHistory();
        refreshTilePieceGraphic();
        refreshTileColors();
    }

    /**
     * Returns one move from redo stack.
     */
    public void redo() {
        game.redo();
        refreshHistory();
        refreshTilePieceGraphic();
        refreshTileColors();
    }
}
