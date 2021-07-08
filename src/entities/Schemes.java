package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Schemes {
    private SimpleIntegerProperty idSchemes, diameterTube, sizeFrame;
    private SimpleStringProperty nameRegion, nameSchemes, plantNameBD, nameTypeBD, plantNameBOI, nameTypeBOI, plantNameII, liningTube;

    public Schemes(int idSchemes, String nameRegion, String nameSchemes, String plantNameBD,
                   String nameTypeBD, String plantNameBOI, String nameTypeBOI, String plantNameII,
                   int diameterTube, String liningTube, int sizeFrame) {
        this.idSchemes = new SimpleIntegerProperty(idSchemes);
        this.nameRegion = new SimpleStringProperty(nameRegion);
        this.nameSchemes = new SimpleStringProperty(nameSchemes);
        this.plantNameBD = new SimpleStringProperty(plantNameBD);
        this.nameTypeBD = new SimpleStringProperty(nameTypeBD);
        this.plantNameBOI = new SimpleStringProperty(plantNameBOI);
        this.nameTypeBOI = new SimpleStringProperty(nameTypeBOI);
        this.plantNameII = new SimpleStringProperty(plantNameII);
        this.diameterTube = new SimpleIntegerProperty(diameterTube);
        this.liningTube = new SimpleStringProperty(liningTube);
        this.sizeFrame = new SimpleIntegerProperty(sizeFrame);
    }

    public Schemes(String nameRegion, String nameSchemes, String plantNameBD, String nameTypeBD, String plantNameBOI,
                   String nameTypeBOI, String plantNameII, int diameterTube, String liningTube, int sizeFrame) {
        this.nameRegion = new SimpleStringProperty(nameRegion);
        this.nameSchemes = new SimpleStringProperty(nameSchemes);
        this.plantNameBD = new SimpleStringProperty(plantNameBD);
        this.nameTypeBD = new SimpleStringProperty(nameTypeBD);
        this.plantNameBOI = new SimpleStringProperty(plantNameBOI);
        this.nameTypeBOI = new SimpleStringProperty(nameTypeBOI);
        this.plantNameII = new SimpleStringProperty(plantNameII);
        this.diameterTube = new SimpleIntegerProperty(diameterTube);
        this.liningTube = new SimpleStringProperty(liningTube);
        this.sizeFrame = new SimpleIntegerProperty(sizeFrame);
    }

    public Schemes(String nameRegion, String nameSchemes, String plantNameBD, String nameTypeBD, String plantNameBOI, String nameTypeBOI) {
        this.nameRegion = new SimpleStringProperty(nameRegion);
        this.nameSchemes = new SimpleStringProperty(nameSchemes);
        this.plantNameBD = new SimpleStringProperty(plantNameBD); //название
        this.nameTypeBD = new SimpleStringProperty(nameTypeBD); //заводской№
        this.plantNameBOI = new SimpleStringProperty(plantNameBOI); //дата
        this.nameTypeBOI = new SimpleStringProperty(nameTypeBOI); // примечание
    }

    public int getIdSchemes(){ return idSchemes.get();}
    public void setIdSchemes(int value){ idSchemes.set(value);}

    public String getNameRegion(){ return nameRegion.get();}
    public void setNameRegion(String value){ nameRegion.set(value);}

    public String getNameSchemes(){ return nameSchemes.get();}
    public void setNameSchemes(String value){ nameSchemes.set(value);}

    public String getPlantNameBD(){ return plantNameBD.get();}
    public void setPlantNameBD(String value){ plantNameBD.set(value);}

    public String getNameTypeBD(){ return nameTypeBD.get();}
    public void setNameTypeBD(String value){ nameTypeBD.set(value);}

    public String getPlantNameBOI(){ return plantNameBOI.get();}
    public void setPlantNameBOI(String value){ plantNameBOI.set(value);}

    public String getNameTypeBOI(){ return nameTypeBOI.get();}
    public void setNameTypeBOI(String value){ nameTypeBOI.set(value);}

    public String getPlantNameII(){ return plantNameII.get();}
    public void setPlantNameII(String value){ plantNameII.set(value);}

    public int getDiameterTube(){ return diameterTube.get();}
    public void setDiameterTube(int value){ diameterTube.set(value);}

    public String getLiningTube(){ return liningTube.get();}
    public void setLiningTube(String value){ liningTube.set(value);}

    public int getSizeFrame(){ return sizeFrame.get();}
    public void setSizeFrame(int value){ sizeFrame.set(value);}
}
