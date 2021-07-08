package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TypeBD {
    private SimpleIntegerProperty idTypeBD;
    private SimpleStringProperty nameTypeBD;

    public TypeBD(int idTypeBD, String nameTypeBD) {
        this.idTypeBD = new SimpleIntegerProperty(idTypeBD);
        this.nameTypeBD = new SimpleStringProperty(nameTypeBD);
    }

    public int getIdTypeBD(){ return idTypeBD.get();}
    public void setIdTypeBD(int value){ idTypeBD.set(value);}

    public String getNameTypeBD(){ return nameTypeBD.get();}
    public void setNameTypeBD(String value){ nameTypeBD.set(value);}

}
