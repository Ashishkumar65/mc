package service;

import java.util.List;

import model.Floor;
import model.ParkingLot;
import model.Slot;
import model.Vehicle;
import repository.ParkingLotRepository;

import java.util.ArrayList;

public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    // Create a parking lot
    public void createParkingLot(String parkingLotId, int noOfFloors, int noOfSlotsPerFloor) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i <= noOfFloors; i++) {
            List<Slot> slots = new ArrayList<>();
            for (int j = 1; j <= noOfSlotsPerFloor; j++) {
                String vehicleType = (j % 2 == 0) ? "CAR" : "BIKE"; // Simple logic for types
                slots.add(new Slot(j, vehicleType));
            }
            floors.add(new Floor(i, slots));
        }
        ParkingLot parkingLot = new ParkingLot(parkingLotId, floors);
        parkingLotRepository.createParkingLot(parkingLotId, parkingLot);
        System.out.println("Created parking lot with " + noOfFloors + " floors and " + noOfSlotsPerFloor + " slots per floor.");
    }

    // Park a vehicle (thread-safe)
    public String parkVehicle(String parkingLotId, Vehicle vehicle) {
        ParkingLot parkingLot = parkingLotRepository.getParkingLot(parkingLotId);
        parkingLot.getLock().lock();
        try {
            for (Floor floor : parkingLot.getFloors()) {
                floor.getLock().lock();
                try {
                    for (Slot slot : floor.getSlots()) {
                        if (!slot.isOccupied() && slot.getVehicleType().equals(vehicle.getType())) {
                            slot.park(vehicle);
                            String ticketId = parkingLotId + "_" + floor.getFloorNumber() + "_" + slot.getSlotNumber();
                            System.out.println("Parked vehicle. Ticket ID: " + ticketId);
                            return ticketId;
                        }
                    }
                } finally {
                    floor.getLock().unlock();
                }
            }
            System.out.println("Parking Lot Full");
            return null;
        } finally {
            parkingLot.getLock().unlock();
        }
    }

    // Unpark a vehicle (thread-safe)
    public void unparkVehicle(String parkingLotId, String ticketId) {
        // Parse ticketId to get floor and slot
        String[] parts = ticketId.split("_");
        int floorNumber = Integer.parseInt(parts[1]);
        int slotNumber = Integer.parseInt(parts[2]);

        ParkingLot parkingLot = parkingLotRepository.getParkingLot(parkingLotId);
        parkingLot.getLock().lock();
        try {
            Floor floor = parkingLot.getFloors().get(floorNumber - 1);
            floor.getLock().lock();
            try {
                Slot slot = floor.getSlots().get(slotNumber - 1);
                if (slot.isOccupied()) {
                    slot.unpark();
                    System.out.println("Unparked vehicle from Ticket ID: " + ticketId);
                } else {
                    System.out.println("Slot is already empty.");
                }
            } finally {
                floor.getLock().unlock();
            }
        } finally {
            parkingLot.getLock().unlock();
        }
    }

    // Display available slots for a vehicle type
    public void displayAvailableSlots(String parkingLotId, String vehicleType) {
        ParkingLot parkingLot = parkingLotRepository.getParkingLot(parkingLotId);
        for (Floor floor : parkingLot.getFloors()) {
            for (Slot slot : floor.getSlots()) {
                if (!slot.isOccupied() && slot.getVehicleType().equals(vehicleType)) {
                    System.out.println("Available slot: Floor " + floor.getFloorNumber() + ", Slot " + slot.getSlotNumber());
                }
            }
        }
    }
}
