package vehicle;

import javax.swing.*;

public class ElectricBicycle extends Vehicle implements ILandTransportation, Motorized {
    private int wheels;
    private int fuel;
    private int life;
    private String roadType;

    public ElectricBicycle(String model, int maxPassengers, int maxSpeed, String roadType, int life, ImageIcon img){
        super(model,maxPassengers,maxSpeed,img);
        this.roadType = roadType;
        this.wheels = 2;
        this.fuel = 20;
        this.life = life;
    }

    @Override
    public int getWheels() { return wheels; }

    @Override
    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    @Override
    public String getRoadType() {
        return roadType;
    }

    @Override
    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    @Override
    public void setAverageFuelConsumption(int fuelConsumption) { this.fuel = fuelConsumption; }

    @Override
    public void setAverageEngineLife(int engineLife) { this.life = engineLife;}

    @Override
    public int getAverageFuelConsumption() { return fuel; }

    @Override
    public int getAverageEngineLife() { return life; }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + super.toString() +
                "\nIt has " + wheels + " wheels." +
                "\nCan move on " + roadType +
                "\nEngine: " + getAverageFuelConsumption() + "L, "
                + "lifetime of " + getAverageEngineLife() + " years.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectricBicycle electricBicycle = (ElectricBicycle) o;
        return wheels == electricBicycle.wheels && Float.compare(electricBicycle.fuel, fuel) == 0 && Float.compare(electricBicycle.life, life) == 0 && roadType.equals(electricBicycle.roadType); }
}