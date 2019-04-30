package Gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController {
    private int gameCounter;

    @FXML
    private AnchorPane window = new AnchorPane();

    @FXML
    private TabPane gameTabs = new TabPane();

    @FXML
    private Button newTabButton;

    public void initialize() {
        window.setPrefWidth(400);
        window.setPrefHeight(500);
    }

    public void newPanel() throws IOException {
        Tab t = new Tab();
        t.setText("Game " + gameCounter);

        Parent root = FXMLLoader.load(getClass().getResource("GameNode.fxml"));

        t.setContent(root);
        gameTabs.getTabs().add(t);
    }
}
