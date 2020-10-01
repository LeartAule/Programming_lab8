package Utils.ClientPart;

import Dragon.Dragon;
import Utils.ServerPart.ServerClasses.Commands.Show;
import controllers.MainController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TablePart{
    private ClientHandler updateCollectionClientMessagesHandler;
    private ObservableList<Dragon> dragonData = FXCollections.observableArrayList();
    private VisualizationPart visualisationWorker;
    private MainController mainController;

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField cordXField;
    @FXML private TextField cordYField;
    @FXML private TextField DragonAgeField;
    @FXML private TextField ColorField;
    @FXML private TextField TypeField;
    @FXML private TextField CharField;
    @FXML private TextField CaveField;
    @FXML private TextField userField;

    @FXML private TableColumn<Dragon, Integer> idColumn;
    @FXML private TableColumn<Dragon, String> nameColumn;
    @FXML private TableColumn<Dragon, Integer> DragonAgeColumn;
    @FXML private TableColumn<Dragon, Integer> coordXColumn;
    @FXML private TableColumn<Dragon, Integer> coordYColumn;
    @FXML private TableColumn<Dragon, String> ColorColumn;
    @FXML private TableColumn<Dragon, String> TypeColumn;
    @FXML private TableColumn<Dragon, String> CharColumn;
    @FXML private TableColumn<Dragon, Float> CaveDepthColumn;
    @FXML private TableColumn<Dragon, String> userColumn;
    @FXML private TableView<Dragon> dbTable;
    private static boolean updateRQ = true;
    public TablePart(ClientHandler updateCollectionClientMessagesHandler, VisualizationPart visualisationWorker, TextField idField, TextField nameField,
                     TextField cordXField, TextField cordYField, TextField dragonAgeField, TextField colorField, TextField typeField, TextField charField,
                     TextField caveField, TextField userField, TableColumn<Dragon, Integer> idColumn, TableColumn<Dragon, String> nameColumn,
                     TableColumn<Dragon, Integer> DragonAgeColumn, TableColumn<Dragon, Integer> coordXColumn, TableColumn<Dragon, Integer> coordYColumn,
                     TableColumn<Dragon, String> ColorColumn, TableColumn<Dragon, String> TypeColumn, TableColumn<Dragon, String> CharColumn,
                     TableColumn<Dragon, Float> CaveDepthColumn, TableColumn<Dragon, String> userColumn, TableView<Dragon> dbTable, MainController mainController) {
        this.updateCollectionClientMessagesHandler = updateCollectionClientMessagesHandler;
        this.visualisationWorker = visualisationWorker;
        this.idField = idField;
        this.nameField = nameField;
        this.cordXField = cordXField;
        this.cordYField = cordYField;
        DragonAgeField = dragonAgeField;
        ColorField = colorField;
        TypeField = typeField;
        CharField = charField;
        CaveField = caveField;
        this.userField = userField;
        this.idColumn = idColumn;
        this.nameColumn = nameColumn;
        this.DragonAgeColumn = DragonAgeColumn;
        this.coordXColumn = coordXColumn;
        this.coordYColumn = coordYColumn;
        this.ColorColumn = ColorColumn;
        this.TypeColumn = TypeColumn;
        this.CharColumn = CharColumn;
        this.CaveDepthColumn = CaveDepthColumn;
        this.userColumn = userColumn;
        this.dbTable = dbTable;
        this.mainController = mainController;
       try{
            updateCollectionClientMessagesHandler.connect("localhost", 50000);
            //setUpTimer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public void setUpTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
            updateTable();


        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



    private void updateTable(){
        try {
            updateCollectionClientMessagesHandler.sendShowRequest();
            String answer = updateCollectionClientMessagesHandler.receiveCollection();

            if(answer.equals("err_show")) return;
            //System.out.println(answer + " ->updateTable");
            if (answer.contains("suc_show")){
                setUpdateRQ(true);
            }
            if (updateRQ){
                mainController.tableReset();
                visualisationWorker.setupVisualisation();

                updateRQ = false;

            }
        } catch (Exception e) {
            System.out.println(e + " -> updateTable -> TablePart");
        }
    }



    public ObservableList<Dragon> getDragonData(){
        return dragonData;
    }


    //Поиск по шаблону

    private void createListeners(FilteredList<Dragon> dragonData){
        idField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getId()).contains(value)){
                    return true;
                }else return false;
            });
        });

        nameField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getName()).contains(value)){
                    return true;
                }else return false;
            });
        });

        cordXField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getCoordinates().getX()).contains(value)){
                    return true;
                }else return false;
            });
        });
        cordYField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getCoordinates().getY()).contains(value)){
                    return true;
                }else return false;
            });
        });
        DragonAgeField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getAge()).contains(value)){
                    return true;
                }else return false;
            });
        });
        ColorField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getColor()).contains(value)){
                    return true;
                }else return false;
            });
        });
        TypeField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getType()).contains(value)){
                    return true;
                }else return false;
            });
        });
        CharField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getCharacter()).contains(value)){
                    return true;
                }else return false;
            });
        });
        CaveField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getCave().getDepth()).contains(value)){
                    return true;
                }else return false;
            });
        });
        userField.textProperty().addListener((observable, oldValue, newValue) ->{
            dragonData.setPredicate(dragon -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String value = newValue;
                if (String.valueOf(dragon.getUserName()).contains(value)){
                    return true;
                }else return false;
            });
        });
    }
    public void setUpdateRQ(Boolean updateRQ){
        this.updateRQ = updateRQ;
    }

    public boolean getUpdateRQ(){
        return updateRQ;
    }

    public void setupFilterTable(){
        updateTable();
        FilteredList<Dragon> filteredData = new FilteredList<>(dragonData, p -> true);
        createListeners(filteredData);
        SortedList<Dragon> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(dbTable.comparatorProperty());

        dbTable.setItems(sortedList);
    }

    private void filterIdColumn(ObservableList<Dragon> DragonData){
        boolean firstFilter = false;
        List<Dragon> addList = new ArrayList<>();
    }

}