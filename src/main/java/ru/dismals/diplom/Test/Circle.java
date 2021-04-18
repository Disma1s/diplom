package ru.dismals.diplom.Test;

/**
 * @author Yurii Tyshchuk
 */
public class Circle extends Shape {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public Circle() {
        super();
    }

    public int getRadius() {
        return radius;
    }
}
