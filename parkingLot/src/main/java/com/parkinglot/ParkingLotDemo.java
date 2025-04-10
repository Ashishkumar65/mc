package com.parkinglot;

import com.parkinglot.enums.VehicleType;
import com.parkinglot.factory.VehicleFactory;
import com.parkinglot.model.ParkingTicket;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.DisplayBoard;
import com.parkinglot.service.ParkingLot;
import com.parkinglot.strategy.CashPayment;
import com.parkinglot.strategy.CreditCardPayment;

public class ParkingLotDemo {
    public static void main(String[] args) {
        try {
            // Get instances
            ParkingLot parkingLot = ParkingLot.getInstance();
            DisplayBoard displayBoard = DisplayBoard.getInstance();

            // Show initial state
            System.out.println("Initial Parking Lot Status:");
            displayBoard.showAvailableSpots();

            // Create and park vehicles
            Vehicle car = VehicleFactory.createVehicle("CAR123", VehicleType.CAR);
            Vehicle truck = VehicleFactory.createVehicle("TRUCK456", VehicleType.TRUCK);
            Vehicle motorcycle = VehicleFactory.createVehicle("MOTO789", VehicleType.MOTORCYCLE);

            // Park vehicles and get tickets
            ParkingTicket carTicket = parkingLot.parkVehicle(car);
            System.out.println("Car parked. Ticket ID: " + carTicket.getTicketId());

            ParkingTicket truckTicket = parkingLot.parkVehicle(truck);
            System.out.println("Truck parked. Ticket ID: " + truckTicket.getTicketId());

            ParkingTicket motorcycleTicket = parkingLot.parkVehicle(motorcycle);
            System.out.println("Motorcycle parked. Ticket ID: " + motorcycleTicket.getTicketId());

            // Show updated state
            System.out.println("\nParking Lot Status after parking vehicles:");
            displayBoard.showAvailableSpots();

            // Simulate some time passing (not needed in real implementation)
            Thread.sleep(2000);

            // Process payments and exit
            System.out.println("Processing payments:");
            
            // Car pays with credit card
            double carFee = parkingLot.calculateFee(carTicket.getTicketId());
            System.out.println("Car parking fee: $" + carFee);
            parkingLot.processPayment(carTicket.getTicketId(), 
                new CreditCardPayment("1234-5678-9012-3456", "123", "12/25"));

            // Truck pays with cash
            double truckFee = parkingLot.calculateFee(truckTicket.getTicketId());
            System.out.println("Truck parking fee: $" + truckFee);
            parkingLot.processPayment(truckTicket.getTicketId(), new CashPayment());

            // Show final state
            System.out.println("\nFinal Parking Lot Status:");
            displayBoard.showAvailableSpots();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
} 