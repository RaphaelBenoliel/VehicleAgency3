package Memento;

import java.util.LinkedList;

public class Caretaker {
    private static final int SIZE = 3;
    private LinkedList<Memento> mementoList = new LinkedList<>();
    public void addMemento(Memento memento) {
        if (mementoList.size() == SIZE) {
            mementoList.removeFirst();
        }
        mementoList.add(memento);
    }
    public Memento getMemento() {
        if (mementoList.isEmpty()) {
            return null; // Return null if there are no mementos available
        }
        return mementoList.getLast(); // Get the last memento from the list
    }
}
