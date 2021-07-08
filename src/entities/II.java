package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class II {
    private SimpleIntegerProperty idII;
    private SimpleStringProperty plantNameII, dateTransferII;

    public II(int idII, String plantNameII, String dateTransferII) {
        this.idII = new SimpleIntegerProperty(idII);
        this.plantNameII = new SimpleStringProperty(plantNameII);
        this.dateTransferII = new SimpleStringProperty(dateTransferII);
    }

    public II(String plantNameII) {
        this.plantNameII = new SimpleStringProperty(plantNameII);
    }

    public int getIdII(){ return idII.get();}
    public void setIdII(int value){ idII.set(value);}

    public String getPlantNameII(){ return plantNameII.get();}
    public void setPlantNameII(String value){ plantNameII.set(value);}

    public String getDateTransferII(){ return dateTransferII.get();}
    public void setDateTransferII(String value){ dateTransferII.set(value);}

}
