package model;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {
    private String parkingLotId;
    private List<Floor> floors;
    private ReentrantLock lock = new ReentrantLock(); // Thread-safe access to parking lot

    public ParkingLot(String parkingLotId, List<Floor> floors) {
        this.parkingLotId = parkingLotId;
        this.floors = floors;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}

