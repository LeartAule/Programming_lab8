package Utils.ServerPart.ServerClasses.Commands;

import Dragon.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.Map;

public class Insert extends AbstractCommand {

    public Insert(){
        command = "insert";
        TextInfo = "{key} : добавить новый элемент с заданным ключом";


        NeedAnStr = true;
        NeedAnObject = true;
    }

    public Insert(String username){
        user = username;

        command = "insert";
        TextInfo = "{key} : добавить новый элемент с заданным ключом";


        NeedAnStr = true;
        NeedAnObject = true;
    }


    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        Integer id = getDragon().getId();

        for (Map.Entry<Integer, Dragon> dragonEntry : dataBaseManager.getCollection().entrySet()) {
            if (dragonEntry.getKey().equals(id)) {
                String str = "err_insert";
                return str;

            }
        }

            dataBaseManager.getCollection().put(id, dragon);
            dataBaseManager.addToDataBase(dragon);



            return "suc_insert";

    }
}
