package Graphics;
import vehicle.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestManager {
    private static final Map<Vehicle, Boolean> vehiclesInTest = new ConcurrentHashMap<>();

    public static boolean isVehicleInTest(Vehicle vehicle) {
        return vehiclesInTest.containsKey(vehicle);
    }

    public static void startTest(Vehicle vehicle, double distance) {
        if (isVehicleInTest(vehicle)) {
            throw new IllegalStateException("A test is already in progress for this vehicle.");
        }

        vehiclesInTest.put(vehicle, true);

        Thread testThread = new Thread(() -> {
            try {
                vehicle.startTest(distance);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                vehiclesInTest.remove(vehicle);
            }
        });
        testThread.start();
    }

    public static boolean isAnyVehicleInTest() {
        return !vehiclesInTest.isEmpty();
    }
}
