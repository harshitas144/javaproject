package org.example.parkingsystem.vehicles;

public class Jeep implements Vehicle{
    private String licensePlate;

    public Jeep(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public double calculateParkingFee(int hours) {
        // Fee calculation for bikes (can be customized)
        return hours * 75; // $35 per hour
    }
}
