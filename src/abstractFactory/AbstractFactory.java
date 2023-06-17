package abstractFactory;


import javax.swing.*;

public interface AbstractFactory<T> {
    T create(String vehicleType, String model, int maxSpeed, int maxPassengers, ImageIcon image);
}