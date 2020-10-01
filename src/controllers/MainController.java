package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import Dragon.*;
import Utils.ServerPart.ServerClasses.Commands.*;
import Utils.ClientPart.ClientHandler;
import Utils.ClientPart.Receiver;
import Utils.ClientPart.TablePart;
import Utils.ClientPart.VisualizationPart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;




public class MainController implements Initializable{
        private ClientHandler clientHandler;
        private VisualizationPart visualisationWorker;
        private static ResourceBundle currentBundle;
        private TablePart filterTableWorker;




        @FXML private Menu menuFile;
        @FXML private MenuItem exit;
        @FXML private Menu menuHelp;
        @FXML private MenuItem help;
        @FXML private MenuItem info;
        @FXML private Menu selectLanguage;
        @FXML private Label usernameLabel;
        @FXML private Label updateTableLable;
        @FXML private Label addCmdLabel;
        @FXML private Button insert;
        @FXML private Label removeCmdLabel;
        @FXML private Button remove_key;
        @FXML private Button removeLower;
        @FXML private Button remove_greater;
        @FXML private Button remove_lower_key;
        @FXML private Button clear;
        @FXML private Label updateCmdLabel;
        @FXML private Button update;
        @FXML private Label printCmdLabel;
        @FXML private Button print_type_descnding;//опечатка
        @FXML private Button print_character_descending;
        @FXML private Button script;
        @FXML private Tab tabDragon;
        @FXML private TextField idField;
        @FXML private TextField nameField;
        @FXML private TextField ageField;
        @FXML private TextField colorField;
        @FXML private TextField typeField;
        @FXML private TextField charField;
        @FXML private TextField caveField;
        @FXML private TextField locXField;
        @FXML private TextField locYField;
        @FXML private TextField userField;
        @FXML private TableView<Dragon> dbTable;
        @FXML private TableColumn<Dragon, Integer> idColumn;
        @FXML private TableColumn<Dragon, String> nameColumn;
        @FXML private TableColumn<Dragon, Integer> ageColumn;
        @FXML private TableColumn<Dragon, String> ColorColumn;
        @FXML private TableColumn<Dragon, String> typeColumn;
        @FXML private TableColumn<Dragon, String> charColumn;
        @FXML private TableColumn<Dragon, Float> CaveColumn;
        @FXML private TableColumn<Dragon, Integer> locXColumn;
        @FXML private TableColumn<Dragon, Integer> locYColumn;
        @FXML private TableColumn<Dragon, String> userColumn;
        @FXML private Tab tabVisual;
        @FXML private Pane pane;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            currentBundle = ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("ru", "RU"));
            clientHandler = new ClientHandler(this);
            ClientHandler updateCollectionClientMessagesHandler = new ClientHandler(this);
            clientHandler.connect("localhost", 50000);
            visualisationWorker = new VisualizationPart(pane, updateCollectionClientMessagesHandler, this);
            filterTableWorker = new TablePart(updateCollectionClientMessagesHandler, visualisationWorker, idField, nameField, locXField, locYField,
                    ageField, colorField, typeField, charField, caveField, userField, idColumn, nameColumn, ageColumn, locXColumn, locYColumn, ColorColumn,
                    typeColumn, charColumn, CaveColumn, userColumn, dbTable, this);



            String fxmFile = "../JavaFX/authorization.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmFile));
            Parent root = fxmlLoader.load();
            AuthorizationController authorizationController = fxmlLoader.getController();
            authorizationController.setClientMessagesHandler(clientHandler);
            authorizationController.setregistrationLoginPos(Pos.CENTER);
            authorizationController.setMainController(this);
            Stage authorizationStage = new Stage();
            authorizationStage.setTitle("Authorization");
            authorizationStage.setResizable(false);
            authorizationStage.setScene(new Scene(root));
            authorizationStage.requestFocus();
            authorizationStage.initModality(Modality.WINDOW_MODAL);
            authorizationStage.showAndWait();

            if (!clientHandler.isAuthorized()){
                System.exit(0);
            } else {

                //fillTable();
                usernameLabel.setText(currentBundle.getString("username") + clientHandler.getLogin());
                changeLanguage();
                filterTableWorker.setupFilterTable();
                filterTableWorker.setUpTimer();
                getDataFromTableWithDoubleClick();
            }
        }catch (Exception e){
            e.printStackTrace();
            //showString(Alert.AlertType.ERROR, currentBundle.getString("Error"), currentBundle.getString("UnexpectedException"));
        }
    }



    @FXML
    void SelectionTab(){

    }

    @FXML
    void visualisationSelect() {

        //visualisationWorker.setupVisualisation();
    }


    @FXML
    void TypeDescendingClick(ActionEvent event) throws IOException {
        Print_field_descending_type type = new Print_field_descending_type();
        type.setUserName(clientHandler.getLogin());
        clientHandler.sendCommand(type);
        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), getCurrentBundle().getString(string) );
    }

    @FXML
    void charDescendingClick(ActionEvent event) throws IOException {
       Print_field_descending_character character = new Print_field_descending_character();
        character.setUserName(clientHandler.getLogin());
        clientHandler.sendCommand(character);
        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), getCurrentBundle().getString(string));
    }

    @FXML
    void clearClick(ActionEvent event) throws IOException {
        Clear clear = new Clear();
        clear.setUserName(clientHandler.getLogin());
        clientHandler.sendCommand(clear);
        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), getCurrentBundle().getString(string) );
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(-1);
    }

    @FXML
    void help(ActionEvent event) {
        showHelp(getCurrentBundle().getString("Info"));
    }

    @FXML
    void info(ActionEvent event) throws IOException {
        PrintInfoClass printInfoClass = new PrintInfoClass();
        printInfoClass.setUserName(clientHandler.getLogin());
        clientHandler.sendCommand(printInfoClass);
        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showINFO(getCurrentBundle().getString("Info"), string);
    }



    @FXML
    void insertClick(ActionEvent event) throws IOException {
    try {
        Insert insert = (Insert) getCommandWithSimpleArg(new Insert(clientHandler.getLogin()), getCurrentBundle().getString("WriteId"));


        if (insert == null) {
            return;
        }

        Dragon dragon = getDragonFromController(getCurrentBundle().getString("add"));
        dragon.setId(Integer.parseInt(insert.getString()));
        dragon.setUserName(clientHandler.getLogin());
        insert.setDragon(dragon);
        System.out.println(insert.getDragon().getName() != null);
        if (insert.getDragon().getName() != null) {
            clientHandler.sendCommand(insert);
            String string;
            while (true) {
                string = clientHandler.receiveAnswer().trim();
                if (!string.equals("")) break;
            }

            showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), getCurrentBundle().getString(string));
        } else {
            showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), "IncorrectDataField");
        }
    }catch (NullPointerException ignore){}
    }



    @FXML
    void removeByKeyClick(ActionEvent event) throws IOException {
        Remove remove = (Remove) getCommandWithSimpleArg(new Remove(), getCurrentBundle().getString("removeID"));
        remove.setUserName(clientHandler.getLogin());

        clientHandler.sendCommand(remove);
        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), string );
    }

    @FXML
    void removeGreaterClick(ActionEvent event) throws IOException {

        Remove_greater removeLowerKey = new Remove_greater();//Лень было название менять
        removeLowerKey.setUserName(clientHandler.getLogin());
        Dragon dragon = getDragonFromController("removeLower");
        dragon.setUserName(clientHandler.getLogin());
        removeLowerKey.setUserName(clientHandler.getLogin());
        dragon.setId(Integer.parseInt(removeLowerKey.getString()));
        removeLowerKey.setDragon(dragon);
        clientHandler.sendCommand(removeLowerKey);
        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), getCurrentBundle().getString(string) );
    }

    @FXML
    void removeLowerClick(ActionEvent event) throws IOException {
        Remove_lower removeLowerKey = new Remove_lower();
        removeLowerKey.setUserName(clientHandler.getLogin());
        Dragon dragon = getDragonFromController("removeLower");
        dragon.setUserName(clientHandler.getLogin());
        removeLowerKey.setUserName(clientHandler.getLogin());
        dragon.setId(Integer.parseInt(removeLowerKey.getString()));
        removeLowerKey.setDragon(dragon);
        clientHandler.sendCommand(removeLowerKey);
        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), getCurrentBundle().getString(string) );
    }

    @FXML
    void removeLowerKeyClick(ActionEvent event) throws IOException {
        Remove_lower_key remove_lower_key = (Remove_lower_key) getCommandWithSimpleArg(new Remove_lower_key(), getCurrentBundle().getString("remove_lower_key"));
        remove_lower_key.setUserName(clientHandler.getLogin());
        clientHandler.sendCommand(remove_lower_key);

        String string;
        while(true){
            string = clientHandler.receiveAnswer().trim();
            if(!string.equals("")) break;
        }

        showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), getCurrentBundle().getString(string) );
    }

    @FXML
    void scriptClick(ActionEvent event) throws IOException {
        String fxmFile = "../JavaFX/ScriptTest.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmFile));
        fxmlLoader.setResources(currentBundle);

        Parent root = fxmlLoader.load();
        ScriptController scriptController = fxmlLoader.getController();
        scriptController.setMainController(this);
        scriptController.setClientHandler(clientHandler);

        Stage dialogStage = new Stage();
        dialogStage.setTitle(getCurrentBundle().getString("script"));
        dialogStage.setResizable(false);
        dialogStage.setScene(new Scene(root));
        dialogStage.requestFocus();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.showAndWait();
    }

    @FXML
    void switchCanadian(ActionEvent event) {
        currentBundle = ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("en", "CA"));
        changeLanguage();
    }

    @FXML
    void switchDank(ActionEvent event) {
        currentBundle = ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("da", "DA"));
        changeLanguage();
    }

    @FXML
    void switchRomania(ActionEvent event) {
        currentBundle = ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("ro", "RO"));
        changeLanguage();
    }

    @FXML
    void switchRussian(ActionEvent event) {
        currentBundle = ResourceBundle.getBundle("bundles.ClassLanguage", new Locale("ru", "RU"));
        changeLanguage();
    }




    //Обновление таблицы
    @FXML
    public synchronized void updateTableClick(){
        filterTableWorker.setUpdateRQ(true);
//      visualisationWorker.setupVisualisation();
        filterTableWorker.setupFilterTable();

    }


        @FXML void updateClick(){
            try{
                Update update = (Update)getCommandWithSimpleArg(new Update(), getCurrentBundle().getString("updateCom"));

                Dragon dragon = getDragonFromController("updateCom");
                update.setUserName(clientHandler.getLogin());
                dragon.setUserName(clientHandler.getLogin());
                dragon.setId(Integer.parseInt(update.getString()));
                update.setDragon(dragon);
                clientHandler.sendCommand(update);

                String string;
                while(true){
                    string = clientHandler.receiveAnswer().trim();
                    if(!string.equals("")) break;
                }

                showString(Alert.AlertType.INFORMATION, getCurrentBundle().getString("Info"), string );


            } catch (Exception e){
                //showAlert(Alert.AlertType.ERROR, currentBundle.getString("Error"), currentBundle.getString("UnexpectedException") + e.getMessage());
            }
        }




//Заполнение таблицы (Логичнее было бы перенести в TablePart)
        private void fillTable(){
            try{

                /*clientHandler.sendCommand(new Show());
                receiveAnswer();*/

                if(filterTableWorker.getUpdateRQ()) {
                    filterTableWorker.getDragonData().clear();
                    filterTableWorker.getDragonData().addAll(clientHandler.getClientList());
                    idColumn.setCellValueFactory(dragonData -> dragonData.getValue().getIdProperty().asObject());
                    nameColumn.setCellValueFactory(dragonData -> dragonData.getValue().getNameProperty());
                    locXColumn.setCellValueFactory(dragonData -> dragonData.getValue().getCoordinates().getXProperty().asObject());
                    locYColumn.setCellValueFactory(dragonData -> dragonData.getValue().getCoordinates().getYProperty().asObject());
                    ageColumn.setCellValueFactory(dragonData -> dragonData.getValue().getAgeProperty().asObject());
                    CaveColumn.setCellValueFactory(dragonData -> dragonData.getValue().getDepthProperty().asObject());
                    ColorColumn.setCellValueFactory(dragonData -> dragonData.getValue().getColor().getColorProperty(dragonData.getValue().getColor()));
                    typeColumn.setCellValueFactory(dragonData -> dragonData.getValue().getType().getTypeProperty(dragonData.getValue().getType()));
                    charColumn.setCellValueFactory(dragonData -> dragonData.getValue().getCharacter().getCharProperty(dragonData.getValue().getCharacter()));
                    userColumn.setCellValueFactory(dragonData -> dragonData.getValue().getUserProperty());
                    dbTable.setItems(filterTableWorker.getDragonData());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        private void getDataFromTableWithDoubleClick(){
            dbTable.setRowFactory(tv ->{
                TableRow<Dragon> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2){
                        if (!row.isEmpty()){
                            Dragon selectedDragon = row.getItem();
                            Update update = null;
                            try {
                                update = (Update) getCommandWithSimpleArg(new Update(), "updateCom");
                            } catch (TransformerException | ParserConfigurationException e) {
                                e.printStackTrace();
                            }catch (NullPointerException ex){
                                return;
                            }

                            Dragon dragon = getDragonFromController("updateCom");
                            if(dragon.equals(null) || update.equals(null)) return;
                            dragon.setId(Integer.parseInt(update.getString()));
                            update.setDragon(dragon);
                            if (update != null){
                                sendPreparedCommand(update, selectedDragon);
                                String ans = "";
                                while(ans.equals("")){
                                    try {
                                        ans = clientHandler.receiveAnswer().trim();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                showString(Alert.AlertType.INFORMATION, currentBundle.getString("Info") ,currentBundle.getString(ans));
                            }
                        }
                    }
                });
                return row;
            });
        }


        public void sendPreparedCommand(AbstractCommand command, Dragon dragon){
            try{
                if (command != null){
                    clientHandler.sendDragonCommand(command, dragon, dragon.getId());
                    String ans = clientHandler.receiveAnswer().trim();
                    while(true){
                        if(!ans.equals("")) break;
                        ans = clientHandler.receiveAnswer().trim();
                    }

                    showString(Alert.AlertType.INFORMATION, currentBundle.getString("info"), getCurrentBundle().getString(ans));

                } else{
                    showAlert(Alert.AlertType.ERROR, currentBundle.getString("Error"), currentBundle.getString("InvalidData"));
                }
            }catch (Exception e){
                //showAlert(Alert.AlertType.ERROR, currentBundle.getString("Error"), currentBundle.getString("UnexpectedException") + e.getMessage());
            }
        }



        //Получение ответов от сервера
        private void receiveAnswer(){
            try{
                String answer;
                answer = clientHandler.receiveCollection();
                if (answer.contains("suc_show")){
                        filterTableWorker.setUpdateRQ(true);
                    }
            }catch (Exception e){
                showAlert(Alert.AlertType.ERROR, getCurrentBundle().getString("Error"), getCurrentBundle().getString("UnexpectedException") + e.getMessage());
            }
        }


        //Смена локали
        private void changeLanguage(){
            visualisationWorker.setCurrentBundle(currentBundle);

            //Блок верхнего меню
            menuFile.setText(currentBundle.getString("file"));
            exit.setText(currentBundle.getString("exit"));

            menuHelp.setText(currentBundle.getString("help"));
            help.setText(currentBundle.getString("helpCom"));
            info.setText(currentBundle.getString("info"));

            selectLanguage.setText(currentBundle.getString("language"));


            //Блок нижнего меню (ТулБар)
            usernameLabel.setText(currentBundle.getString("username") + clientHandler.getLogin());
            updateTableLable.setText(currentBundle.getString("updateTable"));



            //Блок команд:
            addCmdLabel.setText(currentBundle.getString("addCommands"));
            insert.setText(currentBundle.getString("insertCom"));

            removeCmdLabel.setText(currentBundle.getString("removeCommands"));
            remove_key.setText(currentBundle.getString("removeID"));
            removeLower.setText(currentBundle.getString("removeLower"));
            remove_greater.setText(currentBundle.getString("removeGreater"));
            remove_lower_key.setText(currentBundle.getString("remove_lower_key"));;
            clear.setText(currentBundle.getString("clearCom"));

            updateCmdLabel.setText(currentBundle.getString("updateCommands"));
            update.setText(currentBundle.getString("updateCom"));

            printCmdLabel.setText(currentBundle.getString("printCommands"));
            print_type_descnding.setText(currentBundle.getString("printDescendingType"));
            print_character_descending.setText(currentBundle.getString("printDescendingChar"));
            script.setText(currentBundle.getString("script"));

            //Блок таблиц и визуализации
            tabDragon.setText(currentBundle.getString("tabDragon"));
            tabVisual.setText(currentBundle.getString("tabVisual"));
        }



        public AbstractCommand getCommandWithSimpleArg(AbstractCommand command, String labelText){
            try{
                String fxmFile = "../JavaFX/Arg.fxml";
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmFile));
                fxmlLoader.setResources(currentBundle);

                Parent root = fxmlLoader.load();
                ArgController argDialogController = fxmlLoader.getController();
                argDialogController.getArgName().setText(labelText);
                argDialogController.getArgName().setAlignment(Pos.CENTER);

                Stage dialogStage = new Stage();
                dialogStage.setTitle(currentBundle.getString("InputData"));
                dialogStage.setResizable(false);
                dialogStage.setScene(new Scene(root));
                dialogStage.requestFocus();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.showAndWait();

                String receivedArg = argDialogController.getArg();
                if (receivedArg != null){
                    command.setString(receivedArg);
                    return command;
                }else{
                    return null;
                }
            }catch (Exception e){
                return null;
            }

        }

// Начало блока уведомлений
    public void showAlert(Alert.AlertType alertType, String title, String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.setHeight(900);
        alert.setWidth(1000);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK){
                System.out.println(currentBundle.getString("windowClose"));
            }
        });
    }


    public void showHelp(String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(currentBundle.getString("suc_help"));
        alert.setHeight(900);
        alert.setWidth(1000);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK){
                System.out.println(currentBundle.getString("windowClose"));
            }
        });
    }


    private void showINFO(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(currentBundle.getString("HMColl") +": " + content);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK){
                System.out.println(currentBundle.getString("windowClose"));
            }
        });
    }


    public void showString(Alert.AlertType alertType, String title, String content){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK){
                System.out.println(currentBundle.getString("windowClose"));
            }
        });
    }

// <!-- Конец блока уведомлений -->

        public String getDragonInLocalLanguage(Dragon dragon){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss", currentBundle.getLocale());
            return currentBundle.getString("DragonCreatedByUser") + dragon.getUserName() +" " + currentBundle.getString("with") +
                    " id = " + dragon.getId() + "\n" +
                    currentBundle.getString("age") + dragon.getAge() +
                    currentBundle.getString("cooridanateX") + dragon.getCoordinates().getX() + ", y= " + dragon.getCoordinates().getY() + "}\n" +
                    currentBundle.getString("DragonColor") + dragon.getColor() +
                    currentBundle.getString("Dtype") + dragon.getType() +
                    currentBundle.getString("Dchar") + dragon.getCharacter() +
                    currentBundle.getString("Dcave") + NumberFormat.getInstance(getCurrentBundle().getLocale()).format(dragon.getCave().getDepth()) +
                    currentBundle.getString("CreationTime") + dragon.GetTime().format(formatter);

        }


        public Dragon MoveDragon(Dragon dragon) throws IOException {
            String fxmFile = "../JavaFX/move.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmFile));
            fxmlLoader.setResources(currentBundle);

            Parent root = fxmlLoader.load();
            MoveController moveController = fxmlLoader.getController();
            moveController.setMainController(this);
            moveController.setInitialize(dragon);

            Stage dialogStage = new Stage();
            dialogStage.setTitle(getCurrentBundle().getString("moveDragon"));
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(root));
            dialogStage.requestFocus();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();

            if(moveController.getDragon() != null) return moveController.getDragon();
            else return null;

        }


        public Dragon getDragonFromController(String tittleText){
        try{
            String fxmFile = "../JavaFX/Test.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmFile));
            fxmlLoader.setResources(currentBundle);

            Parent root = fxmlLoader.load();
            DragonController dragonController = fxmlLoader.getController();
            dragonController.setCurrentBundle(currentBundle);
            dragonController.setParametersDragon();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(tittleText);
            dialogStage.setResizable(false);
            dialogStage.setScene(new Scene(root));
            dialogStage.requestFocus();
            dialogStage.initModality(Modality.WINDOW_MODAL);


            dialogStage.showAndWait();

            Dragon dragon = dragonController.getDragon();
            if(dragon.getName() != null){
                return dragon;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


        void setCurrentBundle(ResourceBundle currentBundle){
            MainController.currentBundle = currentBundle;
        }

        public static ResourceBundle getCurrentBundle(){
            return currentBundle;
        }

        public void tableReset(){
            fillTable();
        }

}
