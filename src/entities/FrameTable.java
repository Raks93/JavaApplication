package entities;

import javafx.beans.property.SimpleIntegerProperty;

public class FrameTable {
    private SimpleIntegerProperty idFrame, sizeFrame;

    public FrameTable(int idFrame, int sizeFrame) {
        this.idFrame = new SimpleIntegerProperty(idFrame);
        this.sizeFrame = new SimpleIntegerProperty(sizeFrame);
    }

    public FrameTable(int sizeFrame) {
        this.sizeFrame = new SimpleIntegerProperty(sizeFrame);
    }

    public int getIdFrame(){ return idFrame.get();}
    public void setIdFrame(int value){ idFrame.set(value);}

    public int getSizeFrame(){ return sizeFrame.get();}
    public void setSizeFrame(int value){ sizeFrame.set(value);}
}
