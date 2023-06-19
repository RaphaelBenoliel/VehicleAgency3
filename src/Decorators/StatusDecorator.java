package Decorators;

import vehicle.*;

public class StatusDecorator extends Vehicle {
    private String status;

    public StatusDecorator(Vehicle vehicle, String status) {
        super(vehicle);
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return super.toString() + ", Status: " + getStatus();
    }
}