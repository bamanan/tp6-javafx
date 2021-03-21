package abdoulhamid.oriane.tp6.commons.fxml;

import abdoulhamid.oriane.tp6.commons.controller.AbstractController;
import abdoulhamid.oriane.tp6.commons.controller.Controller;

public class ViewPath {
    /**
     * Convert a '.'-based fully-qualified name of {@code viewClass} to a '/'-based
     * resource path suffixed with ".fxml".
     */
    public static String get(Class<? extends Controller> controller) {
        String directoryBase = "/fxml/";
        return directoryBase.concat(controller.getSimpleName().toLowerCase().replace("controller", ".").concat("fxml"));
    }
}
