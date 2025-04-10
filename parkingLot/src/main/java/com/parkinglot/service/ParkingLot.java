package com.parkinglot.service;

import com.parkinglot.enums.ParkingSpotType;
import com.parkinglot.enums.VehicleType;
import com.parkinglot.factory.ParkingSpotFactory;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.ParkingTicket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.strategy.PaymentStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private static final int MAX_CAPACITY = 40000;
    private final Map<ParkingSpotType, List<ParkingSpot>> parkingSpots;
    private final Map<String, ParkingTicket> activeTickets;
    private final double hourlyRate = 10.0; // $10 per hour

    private ParkingLot() {
        this.parkingSpots = new ConcurrentHashMap<>();
        this.activeTickets = new ConcurrentHashMap<>();
        initializeParkingSpots();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    private void initializeParkingSpots() {
        // Initialize different types of parking spots
        for (ParkingSpotType type : ParkingSpotType.values()) {
            parkingSpots.put(type, new ArrayList<>());
            int spotCount = switch (type) {
                case HANDICAPPED -> 4000;  // 10% of total
                case COMPACT -> 16000;     // 40% of total
                case LARGE -> 16000;       // 40% of total
                case MOTORCYCLE -> 4000;   // 10% of total
            };
            
            for (int i = 0; i < spotCount; i++) {
                String spotId = type.toString() + "_" + i;
                parkingSpots.get(type).add(ParkingSpotFactory.createParkingSpot(spotId, type));
            }
        }
    }

    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) {
        if (getTotalOccupiedSpots() >= MAX_CAPACITY) {
            throw new RuntimeException("Parking lot is full!");
        }

        ParkingSpotType spotType = getSpotTypeForVehicle(vehicle.getVehicleType());
        Optional<ParkingSpot> availableSpot = findAvailableSpot(spotType);

        if (availableSpot.isEmpty()) {
            throw new RuntimeException("No available parking spot for this vehicle type!");
        }

        ParkingSpot spot = availableSpot.get();
        spot.parkVehicle(vehicle);

        String ticketId = UUID.randomUUID().toString();
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle.getLicenseNumber());
        activeTickets.put(ticketId, ticket);

        return ticket;
    }

    public double calculateFee(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid ticket ID!");
        }

        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hours = duration.toHours() + (duration.toMinutesPart() > 0 ? 1 : 0); // Round up to the next hour
        return hours * hourlyRate;
    }

    public void processPayment(String ticketId, PaymentStrategy paymentStrategy) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid ticket ID!");
        }

        double amount = calculateFee(ticketId);
        boolean paymentSuccess = paymentStrategy.processPayment(amount);

        if (paymentSuccess) {
            ticket.setExitTime(LocalDateTime.now());
            ticket.setAmount(amount);
            ticket.setPaid(true);
            
            // Find and free the parking spot
            for (List<ParkingSpot> spots : parkingSpots.values()) {
                for (ParkingSpot spot : spots) {
                    if (spot.isOccupied() && spot.getVehicle().getLicenseNumber().equals(ticket.getVehicleLicenseNumber())) {
                        spot.removeVehicle();
                        break;
                    }
                }
            }
            
            activeTickets.remove(ticketId);
        } else {
            throw new RuntimeException("Payment failed!");
        }
    }

    private ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        return switch (vehicleType) {
            case MOTORCYCLE -> ParkingSpotType.MOTORCYCLE;
            case CAR -> ParkingSpotType.COMPACT;
            case TRUCK, VAN -> ParkingSpotType.LARGE;
        };
    }

    private Optional<ParkingSpot> findAvailableSpot(ParkingSpotType spotType) {
        return parkingSpots.get(spotType).stream()
                .filter(spot -> !spot.isOccupied())
                .findFirst();
    }

    public Map<ParkingSpotType, Integer> getAvailableSpotCounts() {
        Map<ParkingSpotType, Integer> availableCounts = new EnumMap<>(ParkingSpotType.class);
        for (ParkingSpotType type : ParkingSpotType.values()) {
            long count = parkingSpots.get(type).stream()
                    .filter(spot -> !spot.isOccupied())
                    .count();
            availableCounts.put(type, (int) count);
        }
        return availableCounts;
    }

    private int getTotalOccupiedSpots() {
        return parkingSpots.values().stream()
                .mapToInt(spots -> (int) spots.stream()
                        .filter(ParkingSpot::isOccupied)
                        .count())
                .sum();
    }
} 