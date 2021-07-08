package app;

import database.DBHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TwoField {
    private String idRecord;
    private int currentCombo;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    void initialize() {
        cancelButton.setOnAction(event -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });

        okButton.setOnAction(event -> {
            DBHandler dbHandler = new DBHandler();
            dbHandler.updateTwoFields(idRecord, idField.getText(), nameField.getText(), currentCombo);
            cancelButton.fire();
        });

        nameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                nameFieldEvent();
                if (!(okButton.isDisable()))
                    idFieldEvent();
            }
        });

        idField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                idFieldEvent();
                if (!(okButton.isDisable()))
                    nameFieldEvent();
            }
        });
    }
    public void setFields(String id, String text, int index) {
        currentCombo = index;
        if (index == 2) {
            nameLabel.setText("Размер");
            nameLabel.setLayoutX(190);
        }
        else if (index == 4) {
            nameLabel.setText("Название");
            nameLabel.setLayoutX(177);
        }
        idRecord = id;
        idField.setText(id);
        nameField.setText(text);
        okButton.setDisable(true);
    }

    public void idFieldEvent() {
        if ((idField.getText().equals("")) || !(idField.getText().matches("^[1-9][0-9]*$")))
            okButton.setDisable(true);
        else
            okButton.setDisable(false);
    }

    public void nameFieldEvent() {
        if (currentCombo != 2) {
            if (nameField.getText().equals(""))
                okButton.setDisable(true);
            else
                okButton.setDisable(false);
        }
        else  {
            if ((nameField.getText().equals("")) || !(nameField.getText().matches("^[1-9][0-9]*$")))
                okButton.setDisable(true);
            else
                okButton.setDisable(false);
        }
    }
}