package Dragon;


import javafx.beans.property.SimpleStringProperty;

/**
 * Класс Color
 */
public enum Color {
    RED, YELLOW, WHITE, BLUE, GREEN, PURPLE, CYAN, UNKNOWN, BLACK;


    public static Color byOrdinal(String s){
        try {
            Color color = Color.valueOf(s);
            return color;

        }catch (IllegalArgumentException e){
            return Color.UNKNOWN;
        }
    }

    public SimpleStringProperty getColorProperty(Color color){
        String str = color.toString();
        return new SimpleStringProperty(str);
    }
}
