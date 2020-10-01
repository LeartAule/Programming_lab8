package Utils.ServerPart.ServerClasses.Commands;

import Dragon.Dragon;
import Utils.ServerPart.DataBaseManager;

import java.util.Map;


public class Remove_greater extends AbstractCommand{
    public Remove_greater() {
        command  = "remove_greater";
        TextInfo = " {element} : удалить из коллекции все элементы, превышающие заданный.\n (Удалаются только те экземпляры, владельцем которых являетесь Вы)";

        NeedAnStr = false;
        NeedAnObject =true;
    }


    public String execute(DataBaseManager dataBaseManager, String arg){



        if(dataBaseManager.getCollection().isEmpty()) return "collection_empty";
        dataBaseManager.getCollection().entrySet().removeIf(entry -> entry.getValue().compareTo(dragon) < 0 && entry.getValue().getUserName().equals(dataBaseManager.getUSER()));


        for (Map.Entry<Integer, Dragon> dragonEntry : dataBaseManager.getCollection().entrySet()) {
            String UserName = dragonEntry.getValue().getUserName().trim();
            if(dragonEntry.getValue().compareTo(dragon) < 0 && user.equals(UserName)){
                dataBaseManager.removeFromDataBase(dragonEntry.getValue().getId(), user);
            }

        }

        return "rg_suc";
    }


}
