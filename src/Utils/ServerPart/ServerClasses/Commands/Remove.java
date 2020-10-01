package Utils.ServerPart.ServerClasses.Commands;

import Dragon.Dragon;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.Map;

public  class Remove extends AbstractCommand {

    public Remove(){
        command = "remove_key";
        TextInfo = "{key} : удалить элемент из коллекции по его ключу.\n (Удалаются только те экземпляры, владельцем которых являетесь Вы)";

        NeedAnStr = true;
    }

    /**
     *
     * @param arg - Имя пользователя
     * @return
     * @throws IOException
     * @throws InvalidCountOfArgumentException
     */

    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {

        if(dataBaseManager.getCollection().isEmpty()) return "collection_empty";

        boolean bool = false;
        Integer key = Integer.parseInt(string);


        for (Map.Entry<Integer, Dragon> dragonEntry : dataBaseManager.getCollection().entrySet()) {
            if (dragonEntry.getKey().equals(key)){
                    bool = true;

            }
        }
        if(bool){
            String msg = dataBaseManager.getCollection().get(key).getName();
            dataBaseManager.getCollection().remove(key);
            if(dataBaseManager.removeFromDataBase(key, user)) return ("suc_remove");

            return "err_remove";//У вас нет такого дракноа
        }else{
            return ("err_remove");//У вас нет такого дракона
        }

    }
}
