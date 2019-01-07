package FactoryPattern;

public class ColorFactory extends AbstractFactory{

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    //  使用 getShape 方法获取形状类型的对象
    public Color getColor(String color){
        if (color == null){
            return null;
        }

        if (color.equalsIgnoreCase("RED")){
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }

        return null;
    }
}