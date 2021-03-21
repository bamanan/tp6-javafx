package abdoulhamid.oriane.tp6.commons.fxml;

import abdoulhamid.oriane.tp6.commons.controller.AbstractController;
import abdoulhamid.oriane.tp6.commons.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class FXMLViewLoader implements ViewLoader {

    private Controller controller;
    private Pane node;


    public FXMLViewLoader(Controller controller, Pane node) {
        this.controller = controller;
        this.node = node;
    }

    public FXMLViewLoader(Pane node) {
        this.node = node;
    }

    public FXMLViewLoader() {
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Pane load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getController().getClass().getResource(ViewPath.get(getController().getClass())));
            fxmlLoader.setController(getController());
            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  node;
    }

    @Override
    public Pane load(Class<? extends Controller> controllerClass) {
        try {
            System.out.println(ViewPath.get(controllerClass));
            FXMLLoader fxmlLoader = new FXMLLoader(controllerClass.getResource(ViewPath.get(controllerClass)));
            //fxmlLoader.setController(controllerClass);
            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  node;
    }

    @Override
    public Pane load(Class<? extends Controller> controllerClass, Controller parent) {
        return null;
    }

    @Override
    public Pane loadFromURl(URL url) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getController().getClass().getResource(url.toString()));
            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  node;
    }


}
