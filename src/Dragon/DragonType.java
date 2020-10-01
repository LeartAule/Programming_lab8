package Dragon;

import javafx.beans.property.SimpleStringProperty;

public enum DragonType {
    WATER, UNDERGROUND, AIR, FIRE, EARTH, ICE, CHAOS, UNKNOWN, VOID;

    public static DragonType byOrdinal(String s){
        try {
            DragonType dragonType = DragonType.valueOf(s);
            return dragonType;

        }catch (IllegalArgumentException e){
            return DragonType.UNKNOWN;
        }
    }

    public SimpleStringProperty getTypeProperty(DragonType type){
        String str = type.toString();
        return new SimpleStringProperty(str);
    }

}
/**
 *UNKNOWN используется в случае неправильного ввода всех остлаьных enum-ов
 */