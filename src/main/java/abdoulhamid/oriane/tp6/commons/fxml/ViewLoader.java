package abdoulhamid.oriane.tp6.commons.fxml;

import abdoulhamid.oriane.tp6.commons.controller.AbstractController;
import abdoulhamid.oriane.tp6.commons.controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;

import java.net.URL;

public interface ViewLoader {

    Pane load();
    Pane load(Class<? extends Controller> controllerClass);
    Pane load(Class<? extends Controller> controllerClass, Controller parent);
    Pane loadFromURl(URL url);

}
