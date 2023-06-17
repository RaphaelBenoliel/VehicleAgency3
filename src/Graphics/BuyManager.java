package Graphics;

import vehicle.*;
public class BuyManager {

    private static Vehicle isInBuy = null;

    public static synchronized boolean isVehicleInBuy(Vehicle vehicle) {
        return isInBuy != null && isInBuy.equals(vehicle);
    }

    public static synchronized void starBuy(Vehicle vehicle) {
        if (isVehicleInBuy(vehicle)) {
            throw new IllegalStateException("A buy process is already in progress for this vehicle.");
        }

        isInBuy = vehicle;
        Thread buyThread = new Thread(() -> {
            synchronized (BuyManager.class) {
                isInBuy = null;
            }
        });
        buyThread.start();
    }

    public static synchronized boolean isAnyVehicleInBuyProgress() {
        return isInBuy != null;
    }
}
