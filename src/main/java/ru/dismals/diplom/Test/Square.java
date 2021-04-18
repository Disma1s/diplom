package ru.dismals.diplom.Test;

/**
 * @author Yurii Tyshchuk
 */
public class Square extends Shape{

    private String color;

    public Square() {
        super();
    }

    public Square(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
