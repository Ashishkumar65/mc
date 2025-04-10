package com.parkinglot.factory;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.model.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String licenseNumber, VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> new Car(licenseNumber);
            case TRUCK -> new Truck(licenseNumber);
            case VAN -> new Van(licenseNumber);
            case MOTORCYCLE -> new Motorcycle(licenseNumber);
        };
    }
}

class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}

class Truck extends Vehicle {
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }
}

class Van extends Vehicle {
    public Van(String licenseNumber) {
        super(licenseNumber, VehicleType.VAN);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORCYCLE);
    }
} 