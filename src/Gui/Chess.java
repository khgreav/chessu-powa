/**
 * Main project class.
 * Author(s): Michal Bucher (xbuche01), Karel Hanák (xhanak34)
 */

package Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of the chess application.
 */
public class Chess extends Application {

    /**
     * Main method, launches the application.
     * @param args input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads fxml file and initializes the main window.
     * @param primaryStage top-level javafx container
     * @throws Exception if an error reading the fxml file occurs
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess");
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setScene(new Scene(root, 1200, 900));
        MainWindowController mainWindowController = new MainWindowController();
        mainWindowController.initialize();

        primaryStage.show();
    }
}
