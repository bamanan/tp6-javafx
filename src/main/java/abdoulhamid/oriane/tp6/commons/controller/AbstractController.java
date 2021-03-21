package abdoulhamid.oriane.tp6.commons.controller;


import abdoulhamid.oriane.tp6.beans.MotsCroisesTP6;

public abstract class AbstractController implements Controller {

    private MotsCroisesTP6 data;

    public AbstractController(MotsCroisesTP6 data) {
        this.data = data;
    }

    public AbstractController() {
        this(null);
    }

    @Override
    public void setData(MotsCroisesTP6 data) {
        this.data = data;
    }

    @Override
    public MotsCroisesTP6 getData() {
        return data;
    }

}
