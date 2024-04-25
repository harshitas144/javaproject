package org.example.parkingsystem.vehicles;

import java.util.ArrayList;
import java.util.Calendar;
import org.example.parkingsystem.exceptions.VehicleAlreadyParkedException;

public abstract class AbstractParkingLot {
    protected ArrayList<Vehicle> parkedVehicles;
    protected ArrayList<Long> parkedVehicleTimestamps; // Stores timestamps for parked vehicles
    protected int totalSlots;
    protected int availableSlots;

    public AbstractParkingLot(int totalSlots) {
        this.totalSlots = totalSlots;
        this.availableSlots = totalSlots;
        this.parkedVehicles = new ArrayList<>();
        this.parkedVehicleTimestamps = new ArrayList<>(); // Initialize here
    }

    protected abstract void parkVehicle(String licensePlate, int duration) throws VehicleAlreadyParkedException;

    public abstract void removeVehicle(String licensePlate);

    public void displayParkedVehicles() {
        System.out.println("Parked vehicles:");
        if (parkedVehicles.isEmpty()) {
            System.out.println("  No vehicles parked currently.");
            return;
        }

        for (int i = 0; i < parkedVehicles.size(); i++) {
            Vehicle vehicle = parkedVehicles.get(i);
            long parkedTime = parkedVehicleTimestamps.get(i);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(parkedTime);

            // Format parked time for better readability
            String formattedTime = calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                    String.format("%02d", calendar.get(Calendar.MINUTE));

            System.out.printf("  - Position: %d, License plate: %s (Parked since: %s)\n",
                    i + 1, vehicle.getLicensePlate(), formattedTime);
        }
    }
}


