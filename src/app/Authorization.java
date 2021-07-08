package app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Authorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorizationButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    void initialize() {

        authorizationButton.setOnAction(event -> {
            if (loginField.getText().trim().toLowerCase().equals("admin") && passField.getText().trim().equals("admin")) {
                authorizationButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/app/fxml/app.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setResizable(false);
                stage.showAndWait();

            }
            else {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Ошибка входа");
                alert.setHeaderText(null);
                alert.setContentText("Нет такого пользователя");

                alert.showAndWait();
            }
        });
    }


}
