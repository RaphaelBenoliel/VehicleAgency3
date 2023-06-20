package Memento;

import vehicle.Vehicle;

import java.util.ArrayList;

public class Memento {
    private ArrayList<Vehicle> state;
    public Memento(ArrayList<Vehicle> state) {
        this.state = new ArrayList<>(state);;
    }
    public ArrayList<Vehicle> getState() {
        return state;
    }

}
