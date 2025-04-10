package com.parkinglot.model;

import com.parkinglot.enums.ParkingSpotType;

public abstract class ParkingSpot {
    private String id;
    private boolean isOccupied;
    private Vehicle vehicle;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(String id, ParkingSpotType parkingSpotType) {
        this.id = id;
        this.parkingSpotType = parkingSpotType;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public String getId() {
        return id;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
} 