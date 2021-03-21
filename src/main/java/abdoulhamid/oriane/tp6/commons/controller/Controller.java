package abdoulhamid.oriane.tp6.commons.controller;

import abdoulhamid.oriane.tp6.beans.MotsCroisesTP6;
import javafx.scene.Node;
import javafx.scene.Parent;

public interface Controller {
    /**
     * set the grid to use
     * @param {@MotsCroisesTP6} data
     */
    void setData(MotsCroisesTP6 data);

    /**
     *
     * @return {@MotsCroisesTP6}
     */
    MotsCroisesTP6 getData();

}
