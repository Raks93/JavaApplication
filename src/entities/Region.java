package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Region {
    private SimpleIntegerProperty idRegion;
    private SimpleStringProperty nameRegion;

    public Region(int idRegion, String nameRegion) {
        this.idRegion = new SimpleIntegerProperty(idRegion);
        this.nameRegion = new SimpleStringProperty(nameRegion);
    }

    public Region(String nameRegion) {
        this.nameRegion = new SimpleStringProperty(nameRegion);
    }

    public int getIdRegion(){ return idRegion.get();}
    public void setIdRegion(int value){ idRegion.set(value);}

    public String getNameRegion(){ return nameRegion.get();}
    public void setNameRegion(String value){ nameRegion.set(value);}

}
