package Dragon;

import javafx.beans.property.SimpleStringProperty;

public enum DragonCharacter {
    CUNNING, EVIL, CHAOTIC_EVIL, FICKLE, UNKNOWN;



    public static DragonCharacter byOrdinal(String s){
        try {
            DragonCharacter dragonCharacter = DragonCharacter.valueOf(s);
            return dragonCharacter;

        }catch (IllegalArgumentException e){
            return DragonCharacter.UNKNOWN;
        }
    }

    public SimpleStringProperty getCharProperty(DragonCharacter character){
        String str = character.toString();
        return new SimpleStringProperty(str);
    }
}
/**
 *UNKNOWN используется в случае неправильного ввода всех остлаьных enum-ов
 */