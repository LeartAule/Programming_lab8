package controllers;

import Dragon.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MoveController {
    //Контроллер для передвижения дракона из таблицы визуализации
    public MoveController(){
    }

    private MainController mainController;
    private Dragon dragon;
    private static int coordX = 0;
    private static int coordY = 0;

    @FXML
    private TextField XField;

    @FXML
    private Label MoveDragonLabel;

    @FXML
    private TextField YField;

    @FXML
    private Button CancelLabel;

    @FXML
    void CancelButton(ActionEvent event) {
        dragon = null;
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }



    @FXML
    void Okbutton(ActionEvent event){
        int x = coordX;
        int y = coordY;
        try {
            y = Integer.parseInt(YField.getText());
            x = Integer.parseInt(XField.getText());
        }catch (Exception e){
            return;
        }
        coordX = x;
        coordY = y;
        dragon.setCoordinates(new Coordinates(coordX, coordY));
        Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void DownButton(ActionEvent event) {
        coordY = coordY - 1;
        if(coordY < -10) coordY = -10;
        YField.setText(String.valueOf(coordY));
    }

    @FXML
    void LeftButton(ActionEvent event) {
        coordX = coordX - 1;
        if(coordX < -100 )coordX = -100;
        XField.setText(String.valueOf(coordX));
    }

    @FXML
    void RightButton(ActionEvent event) {
        coordX = coordX + 1;
        if(coordX > 250 ) coordX = 250;
        XField.setText(String.valueOf(coordX));
    }

    @FXML
    void UpButton(ActionEvent event) {
        coordY = coordY + 1;
        if(coordY > 100) coordY = 100;
        YField.setText(String.valueOf(coordY));
    }


    public void setDragon(Dragon dragon){
        this.dragon = dragon;
    }

    public Dragon getDragon(){
        return dragon;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    void initialize() {
        MoveDragonLabel.setText(new MainController().getCurrentBundle().getString("moveDragon"));
        CancelLabel.setText(new MainController().getCurrentBundle().getString("cancel_button"));




    }

    public void setInitialize(Dragon dragon){
        this.dragon = dragon;
        XField.setText(dragon.getCoordinates().getX().toString());
        YField.setText(dragon.getCoordinates().getY().toString());
        coordY = dragon.getCoordinates().getY();
        coordX = dragon.getCoordinates().getX();
    }
}
