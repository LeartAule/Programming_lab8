package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import Dragon.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DragonController {
    private Dragon dragon;
    private ResourceBundle currentBundle;

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Label DCText;
    @FXML private ComboBox<Color> colorBox;
    @FXML private ComboBox<DragonType> TypeBox;
    @FXML private ComboBox<DragonCharacter> charBox;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField depthField;
    @FXML private TextField coordXField;
    @FXML private TextField coordYField;
    @FXML private Label dragon_param;

    @FXML
    void CancelClick(ActionEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void OkClick(ActionEvent event) {
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String name;
        DragonType type;
        Color color;
        DragonCharacter charac;
        int coor_x, coor_y, age;
        float depth;

        name = nameField.getText();

        if (name.trim().isEmpty()) {
            showAlertWithoutHeaderText("emptyName");
            return;
        }

        try{
            coor_x = Integer.parseInt(coordXField.getText());

        }catch (Exception e) {
            showAlertWithoutHeaderText("InvalidX");
            return;
        }

        try{
            coor_y = Integer.parseInt(coordYField.getText());



        }catch (Exception e) {
            showAlertWithoutHeaderText("InvalidY");
            return;
        }
        try {
            age = Integer.parseInt(ageField.getText());
            if (age < 0) {
                showAlertWithoutHeaderText("InvalidAge");
                return;
            }
        } catch (NumberFormatException e) {
            showAlertWithoutHeaderText("InvalidAge");
            return;
        }

        try {
            depth = Float.parseFloat(depthField.getText());
            if (depth < 0) {
                showAlertWithoutHeaderText("InvalidCave");
                return;
            }
        } catch (Exception e) {
            showAlertWithoutHeaderText("InvalidCave");
            return;
        }

        try {
            color = colorBox.getValue();
        }catch (NullPointerException e){
            showAlertWithoutHeaderText("emptyColor");
            return;
        }
        try {
            type = TypeBox.getValue();
        }catch (NullPointerException e){
            showAlertWithoutHeaderText("emptyType");
            return;
        }
        try {
            charac = charBox.getValue();
        }catch (NullPointerException e){
            showAlertWithoutHeaderText("emptyChar");
            return;
        }
        dragon = new Dragon(name, color, new Coordinates(coor_x, coor_y), age, type, charac, new DragonCave(depth));


        thisStage.close();
    }

    @FXML
    void initialize() {

        ObservableList<DragonType> dragonTypeObservableList = FXCollections.observableArrayList(DragonType.AIR, DragonType.CHAOS, DragonType.EARTH,
                DragonType.FIRE, DragonType.ICE, DragonType.UNDERGROUND, DragonType.VOID, DragonType.WATER);
        TypeBox.getItems().addAll(dragonTypeObservableList);

        ObservableList<Color> colorObservableList = FXCollections.observableArrayList(Color.YELLOW, Color.WHITE, Color.BLUE,
                Color.CYAN, Color.GREEN, Color.PURPLE, Color.RED, Color.BLACK);
        colorBox.getItems().addAll(colorObservableList);

        colorBox.getItems().addAll(colorObservableList);
        ObservableList<DragonCharacter> characterObservableList = FXCollections.observableArrayList(DragonCharacter.CHAOTIC_EVIL, DragonCharacter.CUNNING,
                DragonCharacter.EVIL, DragonCharacter.FICKLE, DragonCharacter.UNKNOWN);
        charBox.getItems().addAll(characterObservableList);
    }

    private void showAlert(String fieldName, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(currentBundle.getString("Error"));
        alert.setHeaderText(currentBundle.getString("IncorrectField") + "\"" + fieldName + "\"");
        alert.setContentText(content);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println(currentBundle.getString("windowClose"));
            }
        });
    }

    private void showAlertWithoutHeaderText(String strAlert) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning alert");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(currentBundle.getString(strAlert));

        alert.showAndWait();
    }

    public void setCurrentBundle(ResourceBundle currentBundle){
        this.currentBundle = currentBundle;
    }

    public Dragon getDragon(){
        return dragon;
    }

    public void setParametersDragon(){
        dragon_param.setText(currentBundle.getString("parameters_dragon"));
    }

}
