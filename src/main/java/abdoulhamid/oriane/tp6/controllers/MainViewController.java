package abdoulhamid.oriane.tp6.controllers;

import abdoulhamid.oriane.tp6.commons.controller.AbstractController;
import abdoulhamid.oriane.tp6.commons.fxml.FXMLViewLoader;
import abdoulhamid.oriane.tp6.utils.ChargerGrille;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class MainViewController extends AbstractController implements Initializable {
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private MenuItem mBtnSave;

    @FXML
    private MenuItem mBtnLoad;

    @FXML
    private MenuItem mBtnQuit;

    @FXML
    private MenuItem mBtnRandomGid;

    @FXML
    private Menu gridsMenu;

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnQuit;

    private final FXMLViewLoader fxmlViewLoader;

    public MainViewController() {
        super();
        this.fxmlViewLoader = new FXMLViewLoader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateGridsMenu();

        //
        mBtnRandomGid.setOnAction((e) -> {
            GridViewController gridViewController = new GridViewController();
            fxmlViewLoader.setController(gridViewController);
            setCenterPane(fxmlViewLoader.load());
        });
        btnNewGame.setOnAction((e) -> {
            GridViewController gridViewController = new GridViewController();
            fxmlViewLoader.setController(gridViewController);
            setCenterPane(fxmlViewLoader.load());
        });

        //Quit application
        mBtnQuit.setOnAction(e -> {
            Platform.exit();
        });
        btnQuit.setOnAction(e -> {
            Platform.exit();
        });
    }

    private void populateGridsMenu() {
        ChargerGrille grilles = new ChargerGrille();
        gridsMenu.getItems().clear();
        int gridNumber = 0;
        for (String gridName : grilles.grillesDisponibles().values()) {

            MenuItem menuItem = new MenuItem();
            menuItem.setText(gridName);
            gridsMenu.getItems().add(menuItem);

            int finalGridNumber = ++gridNumber;
            menuItem.setOnAction((e) -> {
                GridViewController gridViewController = new GridViewController();
                gridViewController.setData(grilles.extraireGrille(finalGridNumber));
                fxmlViewLoader.setController(gridViewController);
                setCenterPane(fxmlViewLoader.load());
            });
        }
    }

    public void setCenterPane(Pane pane) {
        mainBorderPane.setCenter(pane);
    }


}
