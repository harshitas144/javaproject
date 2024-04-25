package org.example.parkingsystem.vehicles;

public class Bike implements Vehicle {
    private String licensePlate;

    public Bike(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public double calculateParkingFee(int hours) {
        // Fee calculation for bikes (can be customized)
        return hours * 35; // $35 per hour
    }
}
