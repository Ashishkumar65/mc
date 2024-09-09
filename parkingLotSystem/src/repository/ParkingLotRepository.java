package repository;

import model.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<String, ParkingLot> parkingLots = new HashMap<>();

    public void createParkingLot(String parkingLotId, ParkingLot parkingLot) {
        parkingLots.put(parkingLotId, parkingLot);
    }

    public ParkingLot getParkingLot(String parkingLotId) {
        return parkingLots.get(parkingLotId);
    }
}

