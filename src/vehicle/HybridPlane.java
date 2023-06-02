package vehicle;

import javax.swing.*;

public class HybridPlane extends Amphibious implements IAirTransportation{

    private String useInfo;

    public HybridPlane(String model, int maxPassengers, int maxSpeed, int wheels, boolean withWindDirection,
                       String countryFlag, int averageFuelConsumption, int averageEngineLife, ImageIcon image) {
        super( model, maxPassengers,maxSpeed,wheels,withWindDirection,countryFlag,averageFuelConsumption, averageEngineLife,image);
        this.useInfo = "military";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + super.toString() +
                "\nIt is used for:" + useInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HybridPlane that)) return false;
        if (!super.equals(o)) return false;
        return getUseInfo().equals(that.getUseInfo());
    }

    @Override
    public String getUseInfo() {
        return useInfo;
    }

    @Override
    public void setUseInfo(String useInfo) {
        this.useInfo = useInfo;
    }
}