package Graphics;
import vehicle.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestManager {
    private static final Map<Vehicle, Boolean> isInTest = new ConcurrentHashMap<>();

    public static boolean isVehicleInTest(Vehicle vehicle) {
        return isInTest.containsKey(vehicle);
    }

    public static void startTest(Vehicle vehicle, double distance) {
        if (isVehicleInTest(vehicle)) {
            throw new IllegalStateException("A test is already in progress for this vehicle.");
        }

        isInTest.put(vehicle, true);

        Thread testThread = new Thread(() -> {
            try {
                vehicle.startTest(distance);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isInTest.remove(vehicle);
            }
        });
        testThread.start();
    }
    public static boolean isAnyVehicleInTest() {
        return !isInTest.isEmpty();
    }
}
