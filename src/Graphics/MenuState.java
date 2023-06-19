package Graphics;

import vehicle.Vehicle;

import java.util.List;

public class MenuState {
    private int totalDistance;
    private List<Vehicle> vehicleList;

    public MenuState(int totalDistance, List<Vehicle> vehicleList) {
        this.totalDistance = totalDistance;
        this.vehicleList = vehicleList;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
}
