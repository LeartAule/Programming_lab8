package Utils.ClientPart;

import Dragon.*;
import Utils.ServerPart.ServerClasses.Commands.AbstractCommand;
import Utils.ServerPart.ServerClasses.Commands.Remove;
import Utils.ServerPart.ServerClasses.Commands.Update;
import controllers.MainController;
import javafx.animation.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


import javafx.util.Duration;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;

public class VisualizationPart {
    private ResourceBundle currentBundle;
    private MainController mainController;
    private ClientHandler updateCollectionClientMessagesHandler;
    private Pane pane;

    private HashMap<String, Color> users = new HashMap<>();
    private HashMap<Circle, Dragon> circleDragonHashMap = new HashMap<>();

    public VisualizationPart(Pane pane, ClientHandler updateCollectionClientMessagesHandler, MainController mainController){
        this.pane = pane;
        this.updateCollectionClientMessagesHandler = updateCollectionClientMessagesHandler;
        this.mainController = mainController;

        currentBundle = MainController.getCurrentBundle();
    }
    private List<Dragon> oldDragon = new LinkedList<Dragon>();

    public void setupVisualisation(){

        //PriorityQueue<Dragon> dragon = updateCollectionClientMessagesHandler.getClientDq();
        List<Dragon> dragons = updateCollectionClientMessagesHandler.getClientList();
        oldDragon = dragons;
        circleDragonHashMap.clear();
        pane.getChildren().clear();
        for (Dragon dragon1 : dragons){
            setupDragon(dragon1);
        }
        setChangeSizeListeners();

    }

    private void setupDragon(Dragon dragon){
        new Random();
        if (!users.containsKey(dragon.getUserName())){
            users.put(dragon.getUserName(), Color.color(Math.random(), Math.random(), Math.random()));
            //users.put(dragon.getId(), Color.valueOf(dragon.getColor().toString()));
        }

        //Ограничение параметров дракона
        float radius =  dragon.getAge()/100.0F;
        if (radius > 10 ) radius = 5;
        if (radius < 1 ) radius = 1;
        int x, y;
        x = dragon.getCoordinates().getX();
        y = dragon.getCoordinates().getY();
        if(x > 250 ) x = 250;
        if(y > 100) y = 100;
        if(x < -100 )x = -100;
        if(y < -10) y = -10;

        Circle circle = new Circle(radius * (pane.getHeight() + pane.getWidth() * 0.666) / 100);

        setCoordinates(circle, x, y);

        circle.setStroke(users.get(dragon.getUserName()));
        circle.setFill(users.get(dragon.getUserName()).deriveColor(1,1,1,0.7));
        circle.setOnMousePressed(circleOnMousePressedEventHandler);
        circleDragonHashMap.put(circle, dragon);

        pane.getChildren().add(circle);
    }

    private void setChangeSizeListeners(){
        //System.out.println("setChangeSizeListeners");
        pane.widthProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) ->{
            pane.getChildren().clear();
            for (Dragon dragon : updateCollectionClientMessagesHandler.getClientList()){
                setupDragon(dragon);
            }
        });
        pane.heightProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) ->{
            pane.getChildren().clear();
            for (Dragon dragon : updateCollectionClientMessagesHandler.getClientList()){
                setupDragon(dragon);
            }
        });
    }

    private void setCoordinates(Circle circle, double x, double y){
        double newX = x * pane.getWidth() / 250 + pane.getHeight() / 2;
        double newY = -y * pane.getHeight() / 250 + pane.getWidth() / 2;
        circle.setLayoutX(newX);
        circle.setLayoutY(newY);
    }

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                    new KeyValue (new SimpleDoubleProperty(), 0),
                    new KeyValue (new SimpleDoubleProperty(), 0.3)
            ),
            new KeyFrame(Duration.seconds(0),
                    new KeyValue (new SimpleDoubleProperty(), 1),
                    new KeyValue (new SimpleDoubleProperty(), 1.0)
            )
    );

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
        }
    };

    EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent t){
            if (t.getSource() instanceof Circle){
                Circle selectedCircle = ((Circle) (t.getSource()));
                Dragon selectedDragon = circleDragonHashMap.get(selectedCircle);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(currentBundle.getString("info"));
                alert.setHeaderText(currentBundle.getString("uPick") + selectedDragon.getName());
                alert.setContentText(mainController.getDragonInLocalLanguage(selectedDragon));

                ButtonType close = new ButtonType(currentBundle.getString("exit"));
                ButtonType edit = new ButtonType(currentBundle.getString("update"));
                ButtonType delete = new ButtonType(currentBundle.getString("removeID"));
                ButtonType move = new ButtonType(currentBundle.getString("move"));
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(delete, edit, move, close);

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == close){
                    alert.close();
                } else if (option.get() == edit){
                    AbstractCommand update = null;
                    try {
                        update = new Update();
                        update.setUserName(updateCollectionClientMessagesHandler
                        .getLogin());

                        Dragon dragon = mainController.getDragonFromController(mainController.getCurrentBundle().getString("updateCom"));
                        dragon.setId(selectedDragon.getId());
                        update.setDragon(dragon);
                    } catch (TransformerException | ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    update.setString(selectedDragon.getId().toString());
                    mainController.sendPreparedCommand(update, selectedDragon);
                } else if (option.get() == delete){
                    AbstractCommand removeById = new Remove();
                    removeById.setString(selectedDragon.getId().toString());
                    removeById.setUserName(updateCollectionClientMessagesHandler.getLogin());
                    mainController.sendPreparedCommand(removeById, selectedDragon);
                } else if(option.get() == move){
                    try {
                        Dragon dragon = mainController.MoveDragon(selectedDragon);
                        if(dragon != null){
                            Update update = new Update();
                            update.setDragon(dragon);
                            update.setUserName(updateCollectionClientMessagesHandler.getLogin());
                            sendPreparedCommand(update, selectedDragon, selectedCircle);

                        }
                    } catch (IOException | TransformerException | ParserConfigurationException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    };

    public void setCurrentBundle(ResourceBundle currentBundle){
        this.currentBundle = currentBundle;
    }

    private void SetTransition(Circle circle, Coordinates newCoordinates){
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(10));
           transition.setToX(newCoordinates.getX());
           transition.setToY(newCoordinates.getY());

            transition.setNode(circle);
            transition.play();
    }



    private void sendPreparedCommand(AbstractCommand command, Dragon dragon, Circle circle){
        try{
            if (command != null){
                updateCollectionClientMessagesHandler.sendDragonCommand(command, dragon, dragon.getId());
                String ans = updateCollectionClientMessagesHandler.receiveAnswer().trim();
                while(true){
                    if(!ans.equals("")) break;
                    ans = updateCollectionClientMessagesHandler.receiveAnswer().trim();
                }

                if(ans.equals("suc_update")){
                    SetTransition(circle, dragon.getCoordinates());
                    mainController.showString(Alert.AlertType.INFORMATION, currentBundle.getString("info"), currentBundle.getString(ans) +" " +dragon.getId());
                }
                mainController.showString(Alert.AlertType.INFORMATION, currentBundle.getString("info"), currentBundle.getString(ans));
            } else{
                mainController.showAlert(Alert.AlertType.ERROR, currentBundle.getString("Error"), currentBundle.getString("InvalidData"));
            }
        }catch (Exception e){
            mainController.showAlert(Alert.AlertType.ERROR, currentBundle.getString("Error"), currentBundle.getString("UnexpectedException") + e.getMessage());
        }
    }


}