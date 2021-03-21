package abdoulhamid.oriane.tp6;

import abdoulhamid.oriane.tp6.commons.fxml.FXMLViewLoader;
import abdoulhamid.oriane.tp6.controllers.MainViewController;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainTP6 extends Application {
    private final FXMLViewLoader fxmlViewLoader;
    public MainTP6() {
        this.fxmlViewLoader = new FXMLViewLoader();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("TP6 Abdoul Hamid-Oriane");
            BorderPane root = (BorderPane) fxmlViewLoader.load(MainViewController.class);
            Scene scene = new Scene(root, 660, 660);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
