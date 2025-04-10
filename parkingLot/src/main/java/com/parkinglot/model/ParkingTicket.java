package com.parkinglot.model;

import java.time.LocalDateTime;

public class ParkingTicket {
    private String ticketId;
    private String vehicleLicenseNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount;
    private boolean isPaid;

    public ParkingTicket(String ticketId, String vehicleLicenseNumber) {
        this.ticketId = ticketId;
        this.vehicleLicenseNumber = vehicleLicenseNumber;
        this.entryTime = LocalDateTime.now();
        this.isPaid = false;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getVehicleLicenseNumber() {
        return vehicleLicenseNumber;
    }
} 