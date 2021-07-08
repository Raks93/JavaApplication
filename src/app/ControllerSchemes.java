package app;

import database.DBHandler;
import entities.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSchemes {
    private String idRecord;
    private ObservableList<BD> bdList;
    private ObservableList<BOI> boiList;
    private ObservableList<Tube> tubeList;
    private ObservableList<Region> regionList;
    private ObservableList<II> iiList;
    private ObservableList<FrameTable> frameList;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> bdBox;

    @FXML
    private ComboBox<String> boiBox;

    @FXML
    private ComboBox<String> regionBox;

    @FXML
    private ComboBox<String> iiBox;

    @FXML
    private ComboBox<String> tubeBox;

    @FXML
    private ComboBox<String> frameBox;

    @FXML
    private Label idLebel;

    @FXML
    void initialize() {
        cancelButton.setOnAction(event -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });

        okButton.setOnAction(event -> {
            DBHandler dbHandler = new DBHandler();
            if (idField.isVisible()) {
                dbHandler.updateSchemes(idRecord, idField.getText(), Integer.toString(regionList.get(regionBox.getSelectionModel().getSelectedIndex()).getIdRegion()),
                        nameField.getText(), Integer.toString(bdList.get(bdBox.getSelectionModel().getSelectedIndex() - 1).getIdBD()),
                        Integer.toString(boiList.get(boiBox.getSelectionModel().getSelectedIndex() - 1).getIdBOI()),
                        Integer.toString(iiList.get(iiBox.getSelectionModel().getSelectedIndex()).getIdII()),
                        Integer.toString(tubeList.get(tubeBox.getSelectionModel().getSelectedIndex() - 1).getIdTube()),
                        Integer.toString(frameList.get(frameBox.getSelectionModel().getSelectedIndex()).getIdFrame()));
            }
            else {
                dbHandler.insertSchemes(Integer.toString(regionList.get(regionBox.getSelectionModel().getSelectedIndex()).getIdRegion()),
                        nameField.getText(), Integer.toString(bdList.get(bdBox.getSelectionModel().getSelectedIndex() - 1).getIdBD()),
                        Integer.toString(boiList.get(boiBox.getSelectionModel().getSelectedIndex() - 1).getIdBOI()),
                        Integer.toString(iiList.get(iiBox.getSelectionModel().getSelectedIndex()).getIdII()),
                        Integer.toString(tubeList.get(tubeBox.getSelectionModel().getSelectedIndex() - 1).getIdTube()),
                        Integer.toString(frameList.get(frameBox.getSelectionModel().getSelectedIndex()).getIdFrame()));
            }
            cancelButton.fire();
        });

        idField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkState();
            }
        });

        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkState();
            }
        });

        bdBox.setOnAction(event -> {
            checkState();
        });

        boiBox.setOnAction(event -> {
            checkState();
        });

        tubeBox.setOnAction(event -> {
            checkState();
        });
    }

    public void checkState() {
        okButton.setDisable(false);
        if  (!(nameField.getText().equals(""))) {
            if (tubeBox.getSelectionModel().getSelectedIndex() != 0) {
                if (boiBox.getSelectionModel().getSelectedIndex() != 0) {
                    if (bdBox.getSelectionModel().getSelectedIndex() != 0) {
                        if (idField.isVisible())
                            if ((idField.getText().equals("")) || !(idField.getText().matches("^[1-9][0-9]*$")))
                                okButton.setDisable(true);
                    }
                    else
                        okButton.setDisable(true);
                }
                else
                    okButton.setDisable(true);
            }
            else
                okButton.setDisable(true);
        }
        else
            okButton.setDisable(true);


    }

    public void setParameters(String id){
        idRecord = id;
        setResultList();

        if (Integer.parseInt(idRecord)> 0) {
            idField.setText(id);
        }
        else {
            idField.setVisible(false);
            idLebel.setVisible(false);
        }
        setValuesForm();


        okButton.setDisable(true);
    }

    private void setValuesForm() {
        int lenNameBD = 10, lenTypeBD = 3, lenNameBOI = 10, lenTypeBOI = 3, lenTube = 7;
        int countBD = bdList.size();
        int countBOI = boiList.size();
        int countTube = tubeList.size();
        int countRegion = regionList.size();
        int countII = iiList.size();
        int countFrame = frameList.size();


        ObservableList<String> nameRegion = FXCollections.observableArrayList(), nameBD = FXCollections.observableArrayList(),
                nameBOI = FXCollections.observableArrayList(), nameII = FXCollections.observableArrayList(),
                nameTube = FXCollections.observableArrayList(), sizeFrame = FXCollections.observableArrayList();


        for (int i = 0; i < countBD; i++) {
            if (lenNameBD < bdList.get(i).getPlantNameBD().length())
                lenNameBD = bdList.get(i).getPlantNameBD().length();
            if (lenTypeBD < bdList.get(i).getNameBD().length())
                lenTypeBD = bdList.get(i).getNameBD().length();
        }

        for (int i = 0; i < countBOI; i++) {
            if (lenNameBOI < boiList.get(i).getPlantNameBOI().length())
                lenNameBOI = boiList.get(i).getPlantNameBOI().length();
            if (lenTypeBOI < boiList.get(i).getNameBOI().length())
                lenTypeBOI = boiList.get(i).getNameBOI().length();
        }
        for (int i = 0; i < countTube; i++) {

            if (lenTube < String.valueOf(tubeList.get(i).getDiameterTube()).length())
                lenTube = String.valueOf(tubeList.get(i).getDiameterTube()).length();
        }

        lenNameBD += 1;
        lenNameBOI += 1;
        lenTube += 1;

        nameBD.add(String.format("%-" + lenNameBD  + "s", "Заводской№") +
                String.format("%-" + lenTypeBD  + "s", "Тип"));
        nameBOI.add(String.format("%-" + lenNameBOI  + "s", "Заводской№") +
                String.format("%-" + lenTypeBOI  + "s", "Тип"));
        nameTube.add(String.format("%-" + lenTube  + "s", "Диаметр") +
                String.format("%-9s", "Футировка"));

        for (int i = 0; i < countFrame; i++) {
            sizeFrame.add(String.valueOf(frameList.get(i).getSizeFrame()));
        }
        for (int i = 0; i < countRegion; i++) {
            nameRegion.add(regionList.get(i).getNameRegion());
        }
        for (int i = 0; i < countII; i++) {
            nameII.add(iiList.get(i).getPlantNameII());
        }


        for (int i = 0; i < countBD; i++) {
            nameBD.add(String.format("%-" + lenNameBD + "s", bdList.get(i).getPlantNameBD()) +
                    String.format("%-" + lenTypeBD + "s", bdList.get(i).getNameBD()));
        }
        for (int i = 0; i < countBOI; i++) {
            nameBOI.add(String.format("%-" + lenNameBOI + "s", boiList.get(i).getPlantNameBOI()) +
                    String.format("%-" + lenTypeBOI + "s", boiList.get(i).getNameBOI()));
        }
        for (int i = 0; i < countTube; i++) {
            nameTube.add(String.format("%-" + lenTube  + "d", tubeList.get(i).getDiameterTube()) +
                    String.format("%-9s", tubeList.get(i).getLiningTube()));
        }

        regionBox.setItems(nameRegion);
        iiBox.setItems(nameII);
        frameBox.setItems(sizeFrame);
        bdBox.setItems(nameBD);
        boiBox.setItems(nameBOI);
        tubeBox.setItems(nameTube);

        if (idField.isVisible()) {
            DBHandler db = new DBHandler();
            ObservableList<Schemes> schemes = db.selectSchemesWithID(idRecord);
            regionBox.setValue(schemes.get(0).getNameRegion());
            iiBox.setValue(schemes.get(0).getPlantNameII());
            frameBox.setValue(String.valueOf(schemes.get(0).getSizeFrame()));
            bdBox.setValue(String.format("%-" + lenNameBD + "s", schemes.get(0).getPlantNameBD()) +
                    String.format("%-" + lenTypeBD + "s", schemes.get(0).getNameTypeBD()));
            boiBox.setValue(String.format("%-" + lenNameBOI + "s", schemes.get(0).getPlantNameBOI()) +
                    String.format("%-" + lenTypeBOI + "s", schemes.get(0).getNameTypeBOI()));
            tubeBox.setValue(String.format("%-" + lenTube  + "d", schemes.get(0).getDiameterTube()) +
                    String.format("%-9s", schemes.get(0).getLiningTube()));
            nameField.setText(schemes.get(0).getNameSchemes());
        }
        else {
            regionBox.setValue(regionList.get(0).getNameRegion());
            iiBox.setValue(iiList.get(0).getPlantNameII());
            frameBox.setValue(String.valueOf(frameList.get(0).getSizeFrame()));
            bdBox.setValue(String.format("%-" + lenNameBD + "s", bdList.get(0).getPlantNameBD()) +
                    String.format("%-" + lenTypeBD + "s", bdList.get(0).getNameBD()));
            boiBox.setValue(String.format("%-" + lenNameBOI + "s", boiList.get(0).getPlantNameBOI()) +
                    String.format("%-" + lenTypeBOI + "s", boiList.get(0).getNameBOI()));
            tubeBox.setValue(String.format("%-" + lenTube  + "d", tubeList.get(0).getDiameterTube()) +
                    String.format("%-9s", tubeList.get(0).getLiningTube()));
        }
    }


    private void setResultList() {
        DBHandler db = new DBHandler();
        try {
            bdList = db.selectBD();
            boiList = db.selectBOI();
            tubeList = db.selectTube();
            regionList = db.selectRegion();
            iiList = db.selectII();
            frameList = db.selectFrameTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
