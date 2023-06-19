package Decorators;
import vehicle.*;

public abstract class ColorDecorator extends Vehicle{
    protected Vehicle vehicle;
    protected String color;

    public ColorDecorator(Vehicle vehicle, String color) {
        super(vehicle);
        this.vehicle = vehicle;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String clr) {
        this.color = clr;
    }
}