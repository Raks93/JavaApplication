package app;

import database.DBHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ThreeField {
    private String idRecord;
    private boolean check;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private CheckBox liningCheck;

    @FXML
    private ComboBox<String> chooseType;

    @FXML
    private Label chooseLabel;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField dateField;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    void initialize() {
            cancelButton.setOnAction(event -> {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            });

            okButton.setOnAction(event -> {
                DBHandler dbHandler = new DBHandler();
                if (liningCheck.isVisible()) {
                    if (Integer.parseInt(idRecord) > 0) {
                        dbHandler.updateTube(idRecord, idField.getText(), dateField.getText(), nameField.getText(), String.valueOf(liningCheck.isSelected()));
                    } else {
                        dbHandler.insertTube(nameField.getText(), dateField.getText(), String.valueOf(liningCheck.isSelected()));
                    }
                }
                else if (chooseType.isVisible()){
                    String idType;
                    if (check) {
                        idType = dbHandler.getIDTypeBD(chooseType.getValue());
                        if (Integer.parseInt(idRecord) > 0) {
                            dbHandler.updateBD(idRecord, idField.getText(), nameField.getText(), dateField.getText(), idType);
                        } else {
                            dbHandler.insertBD(nameField.getText(), dateField.getText(), idType);
                        }
                    }
                    else {
                        idType = dbHandler.getIDTypeBOI(chooseType.getValue());
                        if (Integer.parseInt(idRecord) > 0) {
                            dbHandler.updateBOI(idRecord, idField.getText(), nameField.getText(), dateField.getText(), idType);
                        } else {
                            dbHandler.insertBOI(nameField.getText(), dateField.getText(), idType);
                        }
                    }

                }
                else {
                    if (Integer.parseInt(idRecord) > 0) {
                        dbHandler.updateII(idRecord, idField.getText(), nameField.getText(), dateField.getText());
                    } else {
                        dbHandler.insertII(nameField.getText(), dateField.getText());
                    }
                }
                cancelButton.fire();
            });

            idField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    idFieldEvent();
                    if (!(okButton.isDisable())) {
                        nameFieldEvent();
                        if (!(okButton.isDisable())) {
                            dateFieldEvent();
                        }
                    }
                }
            });

            nameField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    nameFieldEvent();
                    if (!(okButton.isDisable())) {
                        dateFieldEvent();
                        if (!(okButton.isDisable())) {
                            if (idField.isVisible())
                                idFieldEvent();
                        }
                    }
                }
            });

            dateField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    dateFieldEvent();
                    if (!(okButton.isDisable())) {
                        nameFieldEvent();
                        if (!(okButton.isDisable())) {
                            if (idField.isVisible())
                                idFieldEvent();
                        }
                    }
                }
            });

        liningCheck.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                okButton.setDisable(false);
                if (idField.isVisible())
                    idFieldEvent();
                if (!(okButton.isDisable())) {
                    nameFieldEvent();
                    if (!(okButton.isDisable())) {
                        dateFieldEvent();
                    }
                }
            }
        });

        chooseType.setOnAction(event -> {
            okButton.setDisable(false);
            if (idField.isVisible())
                idFieldEvent();
            if (!(okButton.isDisable())) {
                nameFieldEvent();
                if (!(okButton.isDisable())) {
                    dateFieldEvent();
                }
            }
        });
    }

    public void setFields(String id, String name, String date) {
        idRecord = id;
        if (Integer.parseInt(idRecord) > 0) {
            idField.setText(id);
        }
        else {
            idField.setVisible(false);
            idLabel.setVisible(false);
        }
        dateLabel.setText("Дата захоронения");
        dateLabel.setLayoutX(300);
        nameField.setText(name);
        dateField.setText(date);
        okButton.setDisable(true);
    }

    public void setFieldsTube(String id, String date, String diameter, String lining)
    {
        idRecord = id;
        if (Integer.parseInt(idRecord) > 0) {
            idField.setText(id);
        }
        else {
            idField.setVisible(false);
            idLabel.setVisible(false);
        }
        nameLabel.setText("Диаметр");
        dateField.setText(date);
        nameField.setText(diameter);
        liningCheck.setVisible(true);
        if (lining.equals("Да"))
            liningCheck.fire();
        okButton.setDisable(true);
    }

    public void setFieldsBlock(String id, String name, String date, String type, boolean bool)
    {
        check = bool;
        idRecord = id;
        nameField.setText(name);
        dateField.setText(date);
        chooseType.setVisible(true);
        chooseLabel.setVisible(true);
        DBHandler dbHandler = new DBHandler();
        ObservableList<String> listNames;
        if (check) {
            listNames = dbHandler.selectNamesTypeBD();
        }
        else {
            listNames = dbHandler.selectNamesTypeBOI();
        }
        chooseType.setItems(listNames);
        if (Integer.parseInt(idRecord) > 0) {
            idField.setText(id);
            chooseType.setValue(type);
        }
        else {
            idField.setVisible(false);
            idLabel.setVisible(false);
            chooseType.setValue(listNames.get(0));
        }
        okButton.setDisable(true);
    }

    public void idFieldEvent() {
        if ((idField.getText().equals("")) || !(idField.getText().matches("^[1-9][0-9]*$")))
            okButton.setDisable(true);
        else
            okButton.setDisable(false);
    }

    public void nameFieldEvent() {
        if (liningCheck.isVisible()) {
            if ((nameField.getText().equals("")) || !(nameField.getText().matches("^[1-9][0-9]*$"))) {
                okButton.setDisable(true);
            }
            else
                okButton.setDisable(false);
        } else {
            if (nameField.getText().equals(""))
                okButton.setDisable(true);
            else
                okButton.setDisable(false);
        }
    }

    public void dateFieldEvent() {
        if ((dateField.getText().equals("")) || !(dateField.getText().matches("^[0-3][0-9][.][0-1][0-9][.][2][0-9]{3}$")))
        {
            okButton.setDisable(true);
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            sdf.setLenient(false);
            if (sdf.parse(dateField.getText(), new ParsePosition(0)) != null)
                okButton.setDisable(false);
            else
                okButton.setDisable(true);
        }
    }
}
