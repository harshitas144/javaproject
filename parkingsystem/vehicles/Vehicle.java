package org.example.parkingsystem.vehicles;

    public interface Vehicle {
        String getLicensePlate();
        double calculateParkingFee(int hours);
    }
