package Utils.ServerPart.ServerClasses.Commands;

import Dragon.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public abstract class AbstractCommand implements Serializable, ColorText {

    protected boolean NeedAnObject = false; //Необходимость объекта Dragon для команды.
    protected String command;// Название команды
    protected String TextInfo;// Описание команды
    protected boolean NeedAnStr = false;//Необходимость аргумента для команды.
    protected Dragon dragon = new Dragon();
    protected String string = "";

    protected String user;




    public String getUserName() {
        return user;
    }

    public void setUserName(String userName) {
        user = userName;
    }

    //Getters and Setters
    public void setDragon(Dragon dragon){
        this.dragon = dragon;
    }

    public void setString(String string){
        this.string = string;
    }

    public Dragon getDragon(){
        return dragon;
    }

    public boolean isNeedAnStr() {
        return NeedAnStr;
    }


    public String getCommand() {
        return command;
    }


    public String execute(){
        return "AC Такая команда не существует";
    }



    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException{
        return execute();
    }


    //Запрос на принятие объекта
    public boolean getObjectExecute(){
        return NeedAnObject;
    }

    public String getString(){
        return string;
    }

    public String toPrint() {
        return command + " " + TextInfo;
    }





    @Override
    public String toString() {
        return command;
    }

    protected static LinkedHashMap<Integer, Dragon> SortCollection(LinkedHashMap<Integer, Dragon> collection ) {
        Comparator<Dragon> dragonComparator = new Comparator<Dragon>() {
            @Override
            public int compare(Dragon s, Dragon s1) {
                return s.getName().compareTo(s1.getName());
            }
        };
        List<Dragon> dragons = new ArrayList<>();
        for (Map.Entry<Integer, Dragon> dragonEntry : collection.entrySet()) {
            dragons.add(dragonEntry.getValue());
        }
        LinkedHashMap<Integer, Dragon> linkedHashMap = new LinkedHashMap<Integer, Dragon>();
        Collections.sort(dragons, dragonComparator);
        for(Dragon dragon : dragons){
            linkedHashMap.put(dragon.getId(), dragon);
        }
        collection = linkedHashMap;

        return collection;
    }


}
