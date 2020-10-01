package Dragon;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 *
 *Класс Дракон
 * Храниться в коллекции
 *
 *
 *
 */

public class Dragon implements ColorText, Comparable<Dragon>, Serializable {


    private Integer id; //Значение поля должно быть больше 0
    //Значение поля дожно быть уникальным
    // Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null
    private Color color; //Поле не может быть null
    private Coordinates coordinates;//Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля дожно генерироваться автоматически
    private int age; //Значение должно быть больше 0
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonCave cave; //Поле может быть null
    private String userName = "null";


    public Dragon() {

        this.creationDate = LocalDateTime.now();
    }


    /*
    public Dragon(String name, int age, Color color, DragonType type, DragonCharacter character) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.character = character;
        this.type = type;
        ID_ALL++;
        this.id = ID_ALL;
        this.creationDate = LocalDateTime.now();
    }
*/

    public Dragon(String UserName){
        this.creationDate = LocalDateTime.now();
        this.userName = UserName;
    }
    /**
     * @param name - имя дракона
     * @param color - цвет дракона
     * @param coordinates - координаты дракона (x, y)
     * @param age - возраст дракона
     * @param type - тип дракона
     * @param character - характер
     * @param cave - пещера дракона (её глубина)
     */
    public Dragon(String name, Color color, Coordinates coordinates, int age, DragonType type,
                  DragonCharacter character, DragonCave cave) {
        this.name = name;
        this.color = color;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.age = age;
        this.type = type;
        this.character = character;
        this.cave = cave;
    }


//////////Геттеры и сеттеры

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacter dragonCharacter) {
        this.character = dragonCharacter;
    }

    public DragonCave getCave() {
        return this.cave;
    }

    public void setCave(DragonCave cave) {
        this.cave = cave;
    }

    public LocalDateTime GetTime(){
        return creationDate;
    }

    public void setLocaleDate(LocalDateTime localeDate){
        creationDate = localeDate;
    }

    public Dragon getDragon(){
        return this;
    }


////////
    /**
     * Нужен для сравнивания экзепляров коллекции
     */
    public int compareTo(Dragon dr) {

        int result = this.id.compareTo(dr.getId());

        if (result==0){
            result = this.name.compareTo(dr.name);
        }

        if (result==0){
            result = this.coordinates.compareTo(dr.coordinates);
        }

        return result;
    }


    @Override
    public String toString(){
        return "Dragon{id = " + id + " name = " +
                ColorText.Text(name, color) + ", owner:  " + ColorText.Text(userName, Color.YELLOW) + "} \n";
    }



    //вывод со всеми полями
    public String toDragonString() {
        return "Dragon{" +
                "id=" + id +
                ", name='" + ColorText.Text(name, color) + '\'' +
                ", age = "+ age + ",  color = " + color + ", type = " + type + ", character = " + character + ",\n cave = " + cave
                +", coordinates=" + coordinates.toString() + ", Creation Time = " + GetTime() +
                ", owner: " + ColorText.Text(userName, Color.YELLOW) + "}";
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        return false;
    }


    public SimpleIntegerProperty getIdProperty() {
        return new SimpleIntegerProperty(id);
    }

    public SimpleStringProperty getNameProperty() {
        return new SimpleStringProperty(name);
    }

    public SimpleIntegerProperty getAgeProperty() {
        return new SimpleIntegerProperty(age);
    }

    public Coordinates getCoordinatesProperty(){
        return coordinates;
    }

    public SimpleFloatProperty getDepthProperty(){
        return new SimpleFloatProperty(cave.getDepth());
    }

    public SimpleStringProperty getColorProperty() {
        return new SimpleStringProperty(color.toString());
    }

    public SimpleStringProperty getTypeProperty() {
        return new SimpleStringProperty(type.toString());
    }

    public SimpleStringProperty getCharProperty() {
        return new SimpleStringProperty(character.toString());
    }

    public SimpleStringProperty getUserProperty() {
        return new SimpleStringProperty(userName);
    }



    private int size = 0;

    public void setNum(int size){
        this.size = size;
    }

    public int getNum(){
        return size;
    }

}
