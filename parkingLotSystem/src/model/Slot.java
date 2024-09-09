package model;
public class Slot {
    private int slotNumber;
    private String vehicleType; // "CAR", "BIKE", "TRUCK"
    private boolean isOccupied;
    private Vehicle vehicle; // To store the parked vehicle

    public Slot(int slotNumber, String vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied = false;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void park(Vehicle vehicle) {
        this.isOccupied = true;
        this.vehicle = vehicle;
    }

    public void unpark() {
        this.isOccupied = false;
        this.vehicle = null;
    }
}
