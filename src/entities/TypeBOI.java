package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TypeBOI {
    private SimpleIntegerProperty idTypeBOI;
    private SimpleStringProperty nameTypeBOI;

    public TypeBOI(int idTypeBOI, String nameTypeBOI) {
        this.idTypeBOI = new SimpleIntegerProperty(idTypeBOI);
        this.nameTypeBOI = new SimpleStringProperty(nameTypeBOI);
    }

    public int getIdTypeBOI(){ return idTypeBOI.get();}
    public void setIdTypeBOI(int value){ idTypeBOI.set(value);}

    public String getNameTypeBOI(){ return nameTypeBOI.get();}
    public void setNameTypeBOI(String value){ nameTypeBOI.set(value);}

}
