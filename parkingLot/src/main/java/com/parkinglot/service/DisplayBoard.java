package com.parkinglot.service;

import com.parkinglot.enums.ParkingSpotType;
import java.util.Map;

public class DisplayBoard {
    private static DisplayBoard instance;
    private final ParkingLot parkingLot;

    private DisplayBoard() {
        this.parkingLot = ParkingLot.getInstance();
    }

    public static synchronized DisplayBoard getInstance() {
        if (instance == null) {
            instance = new DisplayBoard();
        }
        return instance;
    }

    public void showAvailableSpots() {
        Map<ParkingSpotType, Integer> availableSpots = parkingLot.getAvailableSpotCounts();
        
        System.out.println("\n=== Parking Lot Status ===");
        System.out.println("-------------------------");
        
        boolean isFull = true;
        for (Map.Entry<ParkingSpotType, Integer> entry : availableSpots.entrySet()) {
            System.out.printf("%s Spots Available: %d%n", 
                    entry.getKey().toString(), 
                    entry.getValue());
            if (entry.getValue() > 0) {
                isFull = false;
            }
        }
        
        if (isFull) {
            System.out.println("\n*** PARKING LOT IS FULL ***");
        }
        
        System.out.println("-------------------------\n");
    }
} 