package abdoulhamid.oriane.tp6.controllers;

import abdoulhamid.oriane.tp6.beans.MotsCroisesTP6;
import abdoulhamid.oriane.tp6.commons.controller.AbstractController;
import abdoulhamid.oriane.tp6.utils.ChargerGrille;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class GridViewController extends AbstractController implements Initializable {

    @FXML
    private GridPane monGridPane;

    private MotsCroisesTP6 motsCroisesTP6;

    public GridViewController() {
        super();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Grid population
        initGrid();

        //Grid control
        for (Node n : monGridPane.getChildren()) {
            if (n instanceof TextField) {
                TextField tf = (TextField) n;
                int lig = ((int) n.getProperties().get("gridpane-row")) + 1;
                int col = ((int) n.getProperties().get("gridpane-column")) + 1;

                // Initialisation du TextField tf ayant pour coordonnées (lig, col)
                // (cf. sections 1.3, 1.4 et 1.5)
                String prop = String.valueOf(motsCroisesTP6.getProposition(lig, col));
                String horizontal = motsCroisesTP6.getDefinition(lig, col, true);
                String vertical = motsCroisesTP6.getDefinition(lig, col, false);
                String def = "";
                if (horizontal != null && vertical != null) {
                    def = horizontal + " / " + vertical;
                }
                if (horizontal != null && vertical == null) {
                    def = horizontal;
                }
                if (horizontal == null && vertical != null) {
                    def = vertical;
                }

                tf.setText(prop);
                tf.setTooltip(new Tooltip(def));
                tf.textProperty().bindBidirectional(motsCroisesTP6.propositionProperty(lig, col));

                tf.setOnMouseClicked(this::clicCase);

                //Disable {@TextField} if correct
                tf.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                    if (newValue.equals(String.valueOf(motsCroisesTP6.getSolution(lig, col)))) {
                        tf.setDisable(true);
                    }
                }));
            }
        }
    }

    @FXML
    public void clicCase(MouseEvent e) {
        if (e.getButton() == MouseButton.MIDDLE) {
            TextField clickedCase = (TextField) e.getSource();
            int lig = lig = ((int) clickedCase.getProperties().get("gridpane-row")) + 1;
            int col = col = ((int) clickedCase.getProperties().get("gridpane-column")) + 1;

            motsCroisesTP6.reveler(lig, col);
        }
    }

    /**
     * @return a random {@MotsCroisesTP6} grid
     */
    private MotsCroisesTP6 randomGrid() {
        ChargerGrille chargerGrille = new ChargerGrille();
        Random rand = new Random();
        List<Integer> gridNumbers = new ArrayList<>(chargerGrille.grillesDisponibles().keySet());
        int randGrid = gridNumbers.get(rand.nextInt(gridNumbers.size()));
        return chargerGrille.extraireGrille(randGrid);
    }


    /**
     * Initialize the grid with {@MotsCroisesTP6}
     */
    private void initGrid() {
        motsCroisesTP6 = getData();
        if (motsCroisesTP6 == null) {
            motsCroisesTP6 = randomGrid();
        }
        TextField modelTextField = (TextField) monGridPane.getChildren().get(0);
        monGridPane.getChildren().clear();
        for (int i = 1; i <= motsCroisesTP6.getHauteur(); i++) {
            for (int j = 1; j <= motsCroisesTP6.getLargeur(); j++) {
                if (!motsCroisesTP6.estCaseNoire(i, j)) {
                    TextField textField = new TextField();
                    textField.setPrefHeight(modelTextField.getPrefHeight());
                    textField.setPrefWidth(modelTextField.getPrefWidth());
                    for (Object key : modelTextField.getProperties().keySet()) {
                        textField.getProperties().put(key, modelTextField.getProperties().get(key));
                    }
                    monGridPane.add(textField, j - 1, i - 1);
                }
            }
        }
    }
}
