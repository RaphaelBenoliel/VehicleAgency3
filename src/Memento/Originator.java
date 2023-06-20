package Memento;

import vehicle.Vehicle;

import java.util.ArrayList;

public class Originator {
    private ArrayList<Vehicle> state;

    public void setState(ArrayList<Vehicle> state) {
        this.state = state;
    }

    public ArrayList<Vehicle> getState() {
        return new ArrayList<>(state); // Return a deep copy of the state
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public boolean setMemento(Memento memento) {
        if (memento != null) {
            state =  memento.getState();
            return true;
        }
        return false;
    }
}
