package Decorators;
import vehicle.*;

public class ColorFrameDecorator extends ColorDecorator{
    public ColorFrameDecorator(Vehicle vehicle, String color) {
        super(vehicle, color);
    }

    @Override
    public String toString() {
        return vehicle.toString() + ", Color: " + color;
    }
}