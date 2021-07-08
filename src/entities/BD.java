package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BD {
    private SimpleIntegerProperty idBD;
    private SimpleStringProperty plantNameBD, dateBD, nameBD;

    public BD(int idBD, String plantNameBD, String dateBD, String nameBD) {
        this.idBD = new SimpleIntegerProperty(idBD);
        this.plantNameBD = new SimpleStringProperty(plantNameBD);
        this.dateBD = new SimpleStringProperty(dateBD);
        this.nameBD = new SimpleStringProperty(nameBD);
    }

    public BD(String plantNameBD, String nameBD) {
        this.plantNameBD = new SimpleStringProperty(plantNameBD);
        this.nameBD = new SimpleStringProperty(nameBD);
    }

    public String getPlantNameBD(){ return plantNameBD.get();}
    public void setNPlantNameBD(String value){ plantNameBD.set(value);}

    public int getIdBD(){ return idBD.get();}
    public void setIdBD(int value){ idBD.set(value);}

    public String getDateBD(){ return dateBD.get();}
    public void setDateBD(String value){ dateBD.set(value);}

    public String getNameBD(){ return nameBD.get();}
    public void setNameBD(String value){ nameBD.set(value);}
}
