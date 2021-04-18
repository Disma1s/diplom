package ru.dismals.diplom.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurii Tyshchuk
 */
public class ClientCode {

    public void start() {
        Shape shape = new Square();
        Square square = new Square();

        List<Shape> shapes = new ArrayList<Shape>() {
            {
                add(new Square());
                add(new Circle());
                add(new Square());
                add(new Circle());
                add(new Square());
                add(new Circle());
            }
        };

    }

}
