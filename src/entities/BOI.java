package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BOI {
    private SimpleIntegerProperty idBOI;
    private SimpleStringProperty plantNameBOI, dateTOBOI, nameBOI;

    public BOI(int idBOI, String plantNameBOI, String dateTOBOI, String nameBOI) {
        this.idBOI = new SimpleIntegerProperty(idBOI);
        this.plantNameBOI = new SimpleStringProperty(plantNameBOI);
        this.dateTOBOI = new SimpleStringProperty(dateTOBOI);
        this.nameBOI = new SimpleStringProperty(nameBOI);
    }

    public BOI(String plantNameBOI, String nameBOI) {
        this.plantNameBOI = new SimpleStringProperty(plantNameBOI);
        this.nameBOI = new SimpleStringProperty(nameBOI);
    }

    public String getPlantNameBOI(){ return plantNameBOI.get();}
    public void setNPlantNameBOIBOI(String value){ plantNameBOI.set(value);}

    public int getIdBOI(){ return idBOI.get();}
    public void setIdBOI(int value){ idBOI.set(value);}

    public String getDateTOBOI(){ return dateTOBOI.get();}
    public void setDateTOBOI(String value){ dateTOBOI.set(value);}

    public String getNameBOI(){ return nameBOI.get();}
    public void setNameBOI(String value){ nameBOI.set(value);}

}
