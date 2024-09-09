package model;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Floor {
    private int floorNumber;
    private List<Slot> slots;
    private ReentrantLock lock = new ReentrantLock(); // Thread-safe access to floor

    public Floor(int floorNumber, List<Slot> slots) {
        this.floorNumber = floorNumber;
        this.slots = slots;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public ReentrantLock getLock() {
        return lock;
    }
    public int getFloorNumber() {
        return floorNumber;
    }
}

