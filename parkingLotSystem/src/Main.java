import model.Vehicle;
import repository.ParkingLotRepository;
import service.ParkingLotService;

public class Main {
    public static void main(String[] args) {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        ParkingLotService parkingLotService = new ParkingLotService(parkingLotRepository);

        // Create parking lot
        parkingLotService.createParkingLot("PL123", 2, 4);

        // Park vehicles
        String ticket1 = parkingLotService.parkVehicle("PL123", new Vehicle("KA-01-HH-1234", "Black", "CAR"));
        String ticket2 = parkingLotService.parkVehicle("PL123", new Vehicle("KA-01-HH-5678", "White", "BIKE"));

        // Display available slots for CAR
        parkingLotService.displayAvailableSlots("PL123", "CAR");

        // Unpark vehicles
        parkingLotService.unparkVehicle("PL123", ticket1);
        parkingLotService.unparkVehicle("PL123", ticket2);

        // Try to park again
        String ticket3 = parkingLotService.parkVehicle("PL123", new Vehicle("KA-01-HH-1111", "Red", "CAR"));
    }
}
