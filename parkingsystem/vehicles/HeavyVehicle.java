package org.example.parkingsystem.vehicles;

public class HeavyVehicle implements Vehicle {
    private String licensePlate;

    public HeavyVehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public double calculateParkingFee(int hours) {
        // Fee calculation for bikes (can be customized)
        return hours * 100; // $35 per hour
    }
}
