package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Tube {
    private SimpleIntegerProperty idTube, diameterTube;
    private SimpleStringProperty liningTube, dateTOTube;

    public Tube(int idTube, String dateTOTube, int diameterTube, String liningTube) {
        this.idTube = new SimpleIntegerProperty(idTube);
        this.dateTOTube = new SimpleStringProperty(dateTOTube);
        this.diameterTube = new SimpleIntegerProperty(diameterTube);
        this.liningTube = new SimpleStringProperty(liningTube);
    }

    public Tube(int diameterTube, String liningTube) {
        this.diameterTube = new SimpleIntegerProperty(diameterTube);
        this.liningTube = new SimpleStringProperty(liningTube);
    }

    public int getIdTube(){ return idTube.get();}
    public void setIdTube(int value){ idTube.set(value);}

    public int getDiameterTube(){ return diameterTube.get();}
    public void setDiameterTube(int value){ diameterTube.set(value);}

    public String getLiningTube(){ return liningTube.get();}
    public void setLiningTube(String value){ liningTube.set(value);}

    public String getDateTOTube(){ return dateTOTube.get();}
    public void setDateTOTube(String value){ dateTOTube.set(value);}
}
