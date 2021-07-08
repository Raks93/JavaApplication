package app;

import database.DBHandler;
import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class App {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView myTable;

    @FXML
    private ComboBox<String> comboChoose;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button changeButton;

    @FXML
    void initialize() {
        ObservableList<String> tables = FXCollections.observableArrayList("БД", "БОИ", "Рамы", "ИИ", "Участки", "Схемы", "Катушки", "Виды БД", "Виды БОИ", "ТО");
        comboChoose.setItems(tables);
        comboChoose.setValue("ИИ");
        chooseII();

        changeButton.setOnAction(event -> {
            changeCurrentRecord();
            deleteButton.setDisable(true);
            changeButton.setDisable(true);
        });

        deleteButton.setOnAction(event -> {
            DBHandler dbHandler = new DBHandler();
            if (questionBox()) {
                switch (comboChoose.getSelectionModel().getSelectedIndex()) {
                    case (0):
                        dbHandler.deleteBD(getCurrentCell(0));
                        chooseBD();
                        break;
                    case (1):
                        dbHandler.deleteBOI(getCurrentCell(0));
                        chooseBOI();
                        break;
                    case (2):
                        dbHandler.deleteFrame(getCurrentCell(0));
                        chooseFrame();
                        break;
                    case (3):
                        dbHandler.deleteII(getCurrentCell(0));
                        chooseII();
                        break;
                    case (4):
                        dbHandler.deleteRegion(getCurrentCell(0));
                        chooseRegion();
                        break;
                    case (5):
                        dbHandler.deleteSchemes(getCurrentCell(0));
                        chooseSchemes();
                        break;
                    case (6):
                        dbHandler.deleteTube(getCurrentCell(0));
                        chooseTube();
                        break;
                    case (7):
                        dbHandler.deleteTypeBD(getCurrentCell(0));
                        chooseTypeBD();
                        break;
                    case (8):
                        dbHandler.deleteTypeBOI(getCurrentCell(0));
                        chooseTypeBOI();
                        break;
                }
                deleteButton.setDisable(true);
                changeButton.setDisable(true);
            }
        });

        addButton.setOnAction(event -> {
            String str;
            DBHandler dbHandler = new DBHandler();
            switch (comboChoose.getSelectionModel().getSelectedIndex()) {
                case (0):
                    addThreeFields();
                    chooseBD();
                    break;
                case (1):
                    addThreeFields();
                    chooseBOI();
                    break;
                case (2):
                    str = inputMenu();
                    if (!(str.equals("")) || (str.matches("^[1-9][0-9]*$"))) {
                        dbHandler.insertFrame(str);
                        chooseFrame();
                    }
                    break;
                case (3):
                    addThreeFields();
                    chooseII();
                    break;
                case (4):
                    str = inputMenu();
                    if (!(str.equals(""))) {
                        dbHandler.insertRegion(str);
                        chooseRegion();
                    }
                    break;
                case (5):
                    addScheme();
                    chooseSchemes();
                    break;
                case (6):
                    addThreeFields();
                    chooseTube();
                    break;
                case (7):
                    str = inputMenu();
                    if (!(str.equals(""))) {
                        dbHandler.insertTypeBD(str);
                        chooseTypeBD();
                    }
                    break;
                case (8):
                    str = inputMenu();
                    if (!(str.equals(""))) {
                        dbHandler.insertTypeBOI(str);
                        chooseTypeBOI();
                    }
                    break;
            }
            deleteButton.setDisable(true);
            changeButton.setDisable(true);
        });

        myTable.setOnMouseClicked(event -> {
            deleteButton.setDisable(false);
            changeButton.setDisable(false);
        });

        comboChoose.setOnAction(event -> {
            changeButton.setDisable(true);
            deleteButton.setDisable(true);
            addButton.setVisible(true);
            deleteButton.setVisible(true);
            changeButton.setVisible(true);
            switch (comboChoose.getSelectionModel().getSelectedIndex()) {
                case  (0):
                    chooseBD();
                    break;
                case (1):
                    chooseBOI();
                    break;
                case (2):
                    chooseFrame();
                    break;
                case (3):
                    chooseII();
                    break;
                case (4):
                    chooseRegion();
                    break;
                case (5):
                    chooseSchemes();
                    break;
                case (6):
                    chooseTube();
                    break;
                case (7):
                    chooseTypeBD();
                    break;
                case (8):
                    chooseTypeBOI();
                    break;
                case (9):
                    chooseTO();
                    addButton.setVisible(false);
                    deleteButton.setVisible(false);
                    changeButton.setVisible(false);
                    break;
            }
        });
    }

    public void chooseBD() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<BD> bd = dbHandler.selectBD();

        myTable.setItems(bd);

        TableColumn<BD, Integer> idColumn = new TableColumn<BD, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<BD, Integer>("idBD"));
        myTable.getColumns().add(idColumn);

        TableColumn<BD, String> plantColumn = new TableColumn<BD, String>("Заводской№");
        plantColumn.setCellValueFactory(new PropertyValueFactory<BD, String>("plantNameBD"));
        myTable.getColumns().add(plantColumn);

        TableColumn<BD, String> dateColumn = new TableColumn<BD, String>("Дата ТО");
        dateColumn.setCellValueFactory(new PropertyValueFactory<BD, String>("dateBD"));
        myTable.getColumns().add(dateColumn);

        TableColumn<BD, Integer> typeColumn = new TableColumn<BD, Integer>("Тип");
        typeColumn.setCellValueFactory(new PropertyValueFactory<BD, Integer>("nameBD"));
        myTable.getColumns().add(typeColumn);

        idColumn.setMinWidth(150);
        plantColumn.setMinWidth(150);
        dateColumn.setMinWidth(150);
        typeColumn.setMinWidth(150);
    }
    
    public void chooseBOI() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<BOI> bd = dbHandler.selectBOI();

        myTable.setItems(bd);

        TableColumn<BOI, Integer> idColumn = new TableColumn<BOI, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<BOI, Integer>("idBOI"));
        myTable.getColumns().add(idColumn);

        TableColumn<BOI, String> plantColumn = new TableColumn<BOI, String>("Заводской№");
        plantColumn.setCellValueFactory(new PropertyValueFactory<BOI, String>("plantNameBOI"));
        myTable.getColumns().add(plantColumn);

        TableColumn<BOI, String> dateColumn = new TableColumn<BOI, String>("Дата ТО");
        dateColumn.setCellValueFactory(new PropertyValueFactory<BOI, String>("dateTOBOI"));
        myTable.getColumns().add(dateColumn);

        TableColumn<BOI, Integer> typeColumn = new TableColumn<BOI, Integer>("Тип");
        typeColumn.setCellValueFactory(new PropertyValueFactory<BOI, Integer>("nameBOI"));
        myTable.getColumns().add(typeColumn);

        idColumn.setMinWidth(150);
        plantColumn.setMinWidth(150);
        dateColumn.setMinWidth(150);
        typeColumn.setMinWidth(150);
    }

    public void chooseFrame() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();
        ObservableList<FrameTable> bd = dbHandler.selectFrameTable();

        myTable.setItems(bd);

        TableColumn<FrameTable, Integer> idColumn = new TableColumn<FrameTable, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<FrameTable, Integer>("idFrame"));
        myTable.getColumns().add(idColumn);

        TableColumn<FrameTable, Integer> sizeFrame = new TableColumn<FrameTable, Integer>("Размер рамы");
        sizeFrame.setCellValueFactory(new PropertyValueFactory<FrameTable, Integer>("sizeFrame"));
        myTable.getColumns().add(sizeFrame);

        idColumn.setMinWidth(300);
        sizeFrame.setMinWidth(300);
    }

    public void chooseII() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<II> bd = dbHandler.selectII();

        myTable.setItems(bd);

        TableColumn<II, Integer> idColumn = new TableColumn<II, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<II, Integer>("idII"));
        myTable.getColumns().add(idColumn);

        TableColumn<II, String> plantColumn = new TableColumn<II, String>("Заводской№");
        plantColumn.setCellValueFactory(new PropertyValueFactory<II, String>("plantNameII"));
        myTable.getColumns().add(plantColumn);

        TableColumn<II, String> dateColumn = new TableColumn<II, String>("Дата захоронения");
        dateColumn.setCellValueFactory(new PropertyValueFactory<II, String>("dateTransferII"));
        myTable.getColumns().add(dateColumn);

        idColumn.setMinWidth(200);
        plantColumn.setMinWidth(200);
        dateColumn.setMinWidth(200);
    }

    public void chooseRegion() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<Region> bd = dbHandler.selectRegion();

        myTable.setItems(bd);

        TableColumn<Region, Integer> idColumn = new TableColumn<Region, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Region, Integer>("idRegion"));
        myTable.getColumns().add(idColumn);

        TableColumn<Region, String> plantColumn = new TableColumn<Region, String>("Участок");
        plantColumn.setCellValueFactory(new PropertyValueFactory<Region, String>("nameRegion"));
        myTable.getColumns().add(plantColumn);

        idColumn.setMinWidth(300);
        plantColumn.setMinWidth(300);
    }

    public void chooseTube() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<Tube> bd = dbHandler.selectTube();

        myTable.setItems(bd);

        TableColumn<Tube, Integer> idColumn = new TableColumn<Tube, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Tube, Integer>("idTube"));
        myTable.getColumns().add(idColumn);

        TableColumn<Tube, String> dateColumn = new TableColumn<Tube, String>("Дата ТО");
        dateColumn.setCellValueFactory(new PropertyValueFactory<Tube, String>("dateTOTube"));
        myTable.getColumns().add(dateColumn);

        TableColumn<Tube, Integer> diameterColumn = new TableColumn<Tube, Integer>("Диаметр катушки");
        diameterColumn.setCellValueFactory(new PropertyValueFactory<Tube, Integer>("diameterTube"));
        myTable.getColumns().add(diameterColumn);

        TableColumn<Tube, String> liningColumn = new TableColumn<Tube, String>("Футировка");
        liningColumn.setCellValueFactory(new PropertyValueFactory<Tube, String>("liningTube"));
        myTable.getColumns().add(liningColumn);

        idColumn.setMinWidth(150);
        dateColumn.setMinWidth(150);
        diameterColumn.setMinWidth(150);
        liningColumn.setMinWidth(150);
    }

    public void chooseTypeBD() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<TypeBD> bd = dbHandler.selectTypeBD();

        myTable.setItems(bd);

        TableColumn<TypeBD, Integer> idColumn = new TableColumn<TypeBD, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<TypeBD, Integer>("idTypeBD"));
        myTable.getColumns().add(idColumn);

        TableColumn<TypeBD, String> plantColumn = new TableColumn<TypeBD, String>("Тип БД");
        plantColumn.setCellValueFactory(new PropertyValueFactory<TypeBD, String>("nameTypeBD"));
        myTable.getColumns().add(plantColumn);

        idColumn.setMinWidth(300);
        plantColumn.setMinWidth(300);
    }

    public void chooseTypeBOI() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<TypeBOI> bd = dbHandler.selectTypeBOI();

        myTable.setItems(bd);

        TableColumn<TypeBOI, Integer> idColumn = new TableColumn<TypeBOI, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<TypeBOI, Integer>("idTypeBOI"));
        myTable.getColumns().add(idColumn);

        TableColumn<TypeBOI, String> plantColumn = new TableColumn<TypeBOI, String>("Тип БОИ");
        plantColumn.setCellValueFactory(new PropertyValueFactory<TypeBOI, String>("nameTypeBOI"));
        myTable.getColumns().add(plantColumn);

        idColumn.setMinWidth(300);
        plantColumn.setMinWidth(300);
    }

    public void chooseSchemes() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<Schemes> bd = dbHandler.selectSchemes();

        myTable.setItems(bd);

        TableColumn<Schemes, Integer> idColumn = new TableColumn<Schemes, Integer>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Schemes, Integer>("idSchemes"));
        myTable.getColumns().add(idColumn);

        TableColumn<Schemes, String> nameRegion = new TableColumn<Schemes, String>("Участок");
        nameRegion.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameRegion"));
        myTable.getColumns().add(nameRegion);

        TableColumn<Schemes, String> nameSchemes = new TableColumn<Schemes, String>("Схема");
        nameSchemes.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameSchemes"));
        myTable.getColumns().add(nameSchemes);

        TableColumn<Schemes, String> plantNameBD = new TableColumn<Schemes, String>("БД");
        plantNameBD.setCellValueFactory(new PropertyValueFactory<Schemes, String>("plantNameBD"));
        myTable.getColumns().add(plantNameBD);

        TableColumn<Schemes, String> nameTypeBD = new TableColumn<Schemes, String>("Тип БД");
        nameTypeBD.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameTypeBD"));
        myTable.getColumns().add(nameTypeBD);

        TableColumn<Schemes, String> plantNameBOI = new TableColumn<Schemes, String>("БОИ");
        plantNameBOI.setCellValueFactory(new PropertyValueFactory<Schemes, String>("plantNameBOI"));
        myTable.getColumns().add(plantNameBOI);

        TableColumn<Schemes, String> nameTypeBOI = new TableColumn<Schemes, String>("Тип БОИ");
        nameTypeBOI.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameTypeBOI"));
        myTable.getColumns().add(nameTypeBOI);

        TableColumn<Schemes, String> plantNameII = new TableColumn<Schemes, String>("ИИ");
        plantNameII.setCellValueFactory(new PropertyValueFactory<Schemes, String>("plantNameII"));
        myTable.getColumns().add(plantNameII);

        TableColumn<Schemes, Integer> diameterTube = new TableColumn<Schemes, Integer>("Катушка");
        diameterTube.setCellValueFactory(new PropertyValueFactory<Schemes, Integer>("diameterTube"));
        myTable.getColumns().add(diameterTube);

        TableColumn<Schemes, String> liningTube = new TableColumn<Schemes, String>("Футировка");
        liningTube.setCellValueFactory(new PropertyValueFactory<Schemes, String>("liningTube"));
        myTable.getColumns().add(liningTube);

        TableColumn<Schemes, Integer> sizeFrame = new TableColumn<Schemes, Integer>("Рама");
        sizeFrame.setCellValueFactory(new PropertyValueFactory<Schemes, Integer>("sizeFrame"));
        myTable.getColumns().add(sizeFrame);
    }

    public void chooseTO() {
        myTable.getColumns().clear();

        DBHandler dbHandler = new DBHandler();

        ObservableList<Schemes> bd = dbHandler.selectTO();

        myTable.setItems(bd);

        TableColumn<Schemes, String> nameRegion = new TableColumn<Schemes, String>("Участок");
        nameRegion.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameRegion"));
        myTable.getColumns().add(nameRegion);

        TableColumn<Schemes, String> nameSchemes = new TableColumn<Schemes, String>("Схема");
        nameSchemes.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameSchemes"));
        myTable.getColumns().add(nameSchemes);

        TableColumn<Schemes, String> plantNameBD = new TableColumn<Schemes, String>("Название");
        plantNameBD.setCellValueFactory(new PropertyValueFactory<Schemes, String>("plantNameBD"));
        myTable.getColumns().add(plantNameBD);

        TableColumn<Schemes, String> nameTypeBD = new TableColumn<Schemes, String>("Заводской№");
        nameTypeBD.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameTypeBD"));
        myTable.getColumns().add(nameTypeBD);

        TableColumn<Schemes, String> plantNameBOI = new TableColumn<Schemes, String>("Дата ТО");
        plantNameBOI.setCellValueFactory(new PropertyValueFactory<Schemes, String>("plantNameBOI"));
        myTable.getColumns().add(plantNameBOI);

        TableColumn<Schemes, String> nameTypeBOI = new TableColumn<Schemes, String>("Примечания");
        nameTypeBOI.setCellValueFactory(new PropertyValueFactory<Schemes, String>("nameTypeBOI"));
        myTable.getColumns().add(nameTypeBOI);
    }

    public String inputMenu() {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Добавить");
        dialog.setHeaderText("Введите имя новой записи");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        return result.get().trim();
        return "";
    }

    public boolean questionBox() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Операция удаления");
        alert.setHeaderText(null);
        alert.setContentText("Вы уверены, что хотите удалить запись?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK)
            return true;

        return false;
    }

    public String getCurrentCell(int cell) {
        TablePosition tablePosition = (TablePosition) myTable.getSelectionModel().getSelectedCells().get(0);
        Object item = myTable.getItems().get(tablePosition.getRow());
        TableColumn col = (TableColumn) myTable.getColumns().get(cell);

        return col.getCellObservableValue(item).getValue().toString();
    }

    public void addThreeFields() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/app/fxml/threeField.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        ThreeField controllerThreeField = loader.getController();

        switch (comboChoose.getSelectionModel().getSelectedIndex()) {
            case (0):
                controllerThreeField.setFieldsBlock("0", "335", "01.01.2000", "", true);
                break;
            case (1):
                controllerThreeField.setFieldsBlock("0", "335", "01.01.2000", "", false);
                break;
            case (3):
                controllerThreeField.setFields("0", "335", "01.01.2000");
                break;
            case (6):
                controllerThreeField.setFieldsTube("0", "01.01.2000", "200", "Да");
                break;
        }


        stage.showAndWait();
    }

    public void addScheme() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/app/fxml/schemes.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        ControllerSchemes controller = loader.getController();
        controller.setParameters("0");

        stage.showAndWait();
    }

    public void changeCurrentRecord() {
        FXMLLoader loader = new FXMLLoader();
        int currentCombo = comboChoose.getSelectionModel().getSelectedIndex();

        switch (currentCombo) {
            case (0):
            case (1):
            case (3):
            case (6):
                loader.setLocation(getClass().getResource("/app/fxml/threeField.fxml"));
                break;
            case (2):
            case (4):
            case (7):
            case (8):
                loader.setLocation(getClass().getResource("/app/fxml/twoField.fxml"));
                break;
            case (5):
                loader.setLocation(getClass().getResource("/app/fxml/schemes.fxml"));
                break;
        }

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        switch (currentCombo) {
            case (0):
                ThreeField controllerBD = loader.getController();
                controllerBD.setFieldsBlock(getCurrentCell(0), getCurrentCell(1), getCurrentCell(2), getCurrentCell(3), true);
                break;
            case (1):
                ThreeField controllerBOI = loader.getController();
                controllerBOI.setFieldsBlock(getCurrentCell(0), getCurrentCell(1), getCurrentCell(2), getCurrentCell(3), false);

                break;
            case (2):
            case (4):
            case (7):
            case (8):
                TwoField controllerTwoField = loader.getController();
                controllerTwoField.setFields(getCurrentCell(0), getCurrentCell(1), currentCombo);
                break;
            case (3):
                ThreeField controllerII = loader.getController();
                controllerII.setFields(getCurrentCell(0), getCurrentCell(1), getCurrentCell(2));
                break;
            case (5):
                ControllerSchemes controllerSchemes = loader.getController();
                controllerSchemes.setParameters(getCurrentCell(0));
                break;
            case (6):
                ThreeField controllerTube = loader.getController();
                controllerTube.setFieldsTube(getCurrentCell(0), getCurrentCell(1), getCurrentCell(2), getCurrentCell(3));
                break;
        }

        stage.showAndWait();

        switch (currentCombo) {
            case  (0):
                chooseBD();
                break;
            case (1):
                chooseBOI();
                break;
            case (2):
                chooseFrame();
                break;
            case (3):
                chooseII();
                break;
            case (4):
                chooseRegion();
                break;
            case (5):
                chooseSchemes();
                break;
            case (6):
                chooseTube();
                break;
            case (7):
                chooseTypeBD();
                break;
            case (8):
                chooseTypeBOI();
                break;
        }
    }
}
