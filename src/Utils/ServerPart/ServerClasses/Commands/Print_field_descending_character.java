package Utils.ServerPart.ServerClasses.Commands;

import Dragon.*;
import Exceptions.InvalidCountOfArgumentException;
import Utils.ServerPart.DataBaseManager;

import java.io.IOException;
import java.util.*;

public class Print_field_descending_character extends AbstractCommand {
    public Print_field_descending_character() {
        command = "print_field_descending_character";
        TextInfo = ": Вывести коллекцию, сортируя по character.";
        NeedAnStr = false;
    }


    public String execute(DataBaseManager dataBaseManager, String arg) throws IOException, InvalidCountOfArgumentException {
        if(dataBaseManager.getCollection().isEmpty()) return "collection_empty";
        String msg = "";
        Comparator<DragonCharacter> dragonCharacterComparator = new Comparator<DragonCharacter>() {
            public int compare(DragonCharacter d1, DragonCharacter d2) {
                return d1.compareTo(d2);
            }
        };
        List<DragonCharacter> dragonCharacters = new ArrayList<>();
        for (Map.Entry<Integer, Dragon> dragonEntry : dataBaseManager.getCollection().entrySet()) {
            if(dragonEntry.getValue().getUserName().equals(dataBaseManager.getUSER()))
                dragonCharacters.add(dragonEntry.getValue().getCharacter());
        }
        Collections.sort(dragonCharacters, dragonCharacterComparator);
        for (DragonCharacter dragonCharacter : dragonCharacters) {
            msg = msg + (dragonCharacter) + " \n";
        }

        return msg;
    }
}
