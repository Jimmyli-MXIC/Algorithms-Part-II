package FactoryPattern;

public class AbstractFactoryPatternDemo {

    public static void main(String[] args){

        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        Shape shape1 = shapeFactory.getShape("Circle");

        shape1.draw();
    }
}
