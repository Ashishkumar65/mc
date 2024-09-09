package model;
public class Vehicle {
    private String registrationNumber;
    private String color;
    private String type;

    public Vehicle(String registrationNumber, String color, String type) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
