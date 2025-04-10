package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

public abstract class Vehicle {
    private String licenseNumber;
    private VehicleType vehicleType;

    public Vehicle(String licenseNumber, VehicleType vehicleType) {
        this.licenseNumber = licenseNumber;
        this.vehicleType = vehicleType;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
} 