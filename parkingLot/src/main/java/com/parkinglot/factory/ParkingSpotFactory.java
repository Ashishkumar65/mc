package com.parkinglot.factory;

import com.parkinglot.enums.ParkingSpotType;
import com.parkinglot.model.ParkingSpot;

public class ParkingSpotFactory {
    public static ParkingSpot createParkingSpot(String id, ParkingSpotType spotType) {
        return switch (spotType) {
            case HANDICAPPED -> new HandicappedSpot(id);
            case COMPACT -> new CompactSpot(id);
            case LARGE -> new LargeSpot(id);
            case MOTORCYCLE -> new MotorcycleSpot(id);
        };
    }
}

class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot(String id) {
        super(id, ParkingSpotType.HANDICAPPED);
    }
}

class CompactSpot extends ParkingSpot {
    public CompactSpot(String id) {
        super(id, ParkingSpotType.COMPACT);
    }
}

class LargeSpot extends ParkingSpot {
    public LargeSpot(String id) {
        super(id, ParkingSpotType.LARGE);
    }
}

class MotorcycleSpot extends ParkingSpot {
    public MotorcycleSpot(String id) {
        super(id, ParkingSpotType.MOTORCYCLE);
    }
} 