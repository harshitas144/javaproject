package org.example.parkingsystem.vehicles;

    public class Car implements Vehicle {
        private String licensePlate;

        public Car(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        @Override
        public String getLicensePlate() {
            return licensePlate;
        }

        @Override
        public double calculateParkingFee(int hours) {
            // Fee calculation for bikes (can be customized)
            return hours * 50; // $35 per hour
        }
    }

