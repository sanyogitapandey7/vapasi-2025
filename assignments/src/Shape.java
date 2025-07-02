import java.util.*;

public abstract class Shape {
    abstract double calculateArea();
    abstract double calculatePerimeter();
}
class Circle extends Shape
{
    double radius;
    Circle(double radius){
        this.radius=radius;
    }
    @Override
    double calculateArea() {
        System.out.println("Area of Circle");
        return 3.14 * radius* radius;
    }

    @Override
    double calculatePerimeter() {
        System.out.println("Parameter of Circle");
        return 6.28*radius;
    }
}

class Rectangle extends Shape{
    double length;
    double breadth;

    Rectangle(double length,double breadth){
        this.length=length;
        this.breadth=breadth;
    }
    @Override
    double calculateArea() {
        System.out.println("Area of Rectangle");
        return length*breadth;
    }

    @Override
    double calculatePerimeter() {
        System.out.println("Perimeter of Rectangle");
        return 2*(length+breadth);
    }
    double setDimensions(double length,double breadth){
        System.out.println("first method");
        return length+breadth;
    }
    double setDimensions(double side)
    {
        System.out.println("overloaded method");
        return 2*side;
    }
}

class Triangle extends Shape{
    double base;
    double height;
    Triangle(double base, double height){
        this.height=height;
        this.base=base;
    }
    @Override
    double calculateArea() {
        System.out.println("Area of Triangle");
        return 0.5*(base*height);
    }

    @Override
    double calculatePerimeter() {
        System.out.println("Perimeter of Triangle");
        return 0;
    }
}
class ShapeCalculator{
    public static void main(String[] args)
    {
        ArrayList<Shape> shapes=new ArrayList<Shape>();
        Shape circle=new Circle(4);
        shapes.add(circle);
        Rectangle rectangle=new Rectangle(3,5);
        shapes.add(rectangle);
        Triangle triangle=new Triangle(3,7);
        shapes.add(triangle);
        for (Shape shape : shapes) {
            double area=shape.calculateArea();
            System.out.println(area);
            double perimeter=shape.calculatePerimeter();
            System.out.println(perimeter);
        }
        double dimension1=rectangle.setDimensions(1);
        System.out.println(dimension1);
        double dimension2=rectangle.setDimensions(2,2);
        System.out.println(dimension2);
    }
}