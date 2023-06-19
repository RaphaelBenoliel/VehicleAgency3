package Graphics;

import vehicle.*;

import java.util.Map;
import java.util.concurrent.*;

public class TestManager {
    private static final int MAX_CONCURRENT_TESTS = 7;
    private static final Map<Vehicle, Boolean> isInTest = new ConcurrentHashMap<>();
    private static final ExecutorService executor = Executors.newFixedThreadPool(MAX_CONCURRENT_TESTS);

    public static boolean isVehicleInTest(Vehicle vehicle) {
        return isInTest.containsKey(vehicle);
    }

    public static void startTest(Vehicle vehicle, double distance) {
        if (isVehicleInTest(vehicle)) {
            throw new IllegalStateException("A test is already in progress for this vehicle.");
        }

        if (isAnyVehicleInTest()) {
            throw new IllegalStateException("Maximum concurrent tests limit reached.");
        }

        vehicle.setStatus("In Test");
        isInTest.put(vehicle, true);

        executor.execute(() -> {
            try {
                vehicle.startTest(distance);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                vehicle.setStatus("Available");
                isInTest.remove(vehicle);
            }
        });
    }

    public static boolean isAnyVehicleInTest() {
        return isInTest.size() >= MAX_CONCURRENT_TESTS;
    }

    public static void shutdown() {
        executor.shutdown();
    }
}
