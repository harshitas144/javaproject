package org.example.parkingsystem;

import org.example.parkingsystem.exceptions.VehicleAlreadyParkedException;
import org.example.parkingsystem.vehicles.*;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

class ParkingSystem extends AbstractParkingLot {
    public ParkingSystem(int totalSlots) {
        super(totalSlots);
    }

    @Override
    protected void parkVehicle(String licensePlate, int duration) throws VehicleAlreadyParkedException {
        if (availableSlots == 0) {
            System.out.println("Sorry, there are no available parking slots.");
            return;
        }

        for (Vehicle vehicle : parkedVehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                throw new VehicleAlreadyParkedException("Vehicle with license plate " + licensePlate + " is already parked.");
            }
        }

        Vehicle parkedVehicle;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type of vehicle (car, jeep, bike, heavy):");
        String vehicleType = scanner.next();

        switch (vehicleType.toLowerCase()) {
            case "car":
                parkedVehicle = new Car(licensePlate);
                break;
            case "jeep":
                parkedVehicle = new Jeep(licensePlate);
                break;
            case "bike":
                parkedVehicle = new Bike(licensePlate);
                break;
            case "heavy":
                parkedVehicle = new HeavyVehicle(licensePlate);
                break;
            default:
                System.out.println("Invalid vehicle type. Please try again.");
                return;
        }

        parkedVehicles.add(parkedVehicle);
        availableSlots--;

        // Add current timestamp when parking a vehicle
        parkedVehicleTimestamps.add(Calendar.getInstance().getTimeInMillis());

        double fee = parkedVehicle.calculateParkingFee(duration);
        System.out.printf("Vehicle parked successfully (Position: %d). Parking fee for %d hours: %.2f\n",
                parkedVehicles.size(), duration, fee);
        System.out.println("Available slots: " + availableSlots);
    }

    @Override
    public void removeVehicle(String licensePlate) {
        int positionToRemove = -1;
        for (int i = 0; i < parkedVehicles.size(); i++) {
            if (parkedVehicles.get(i).getLicensePlate().equals(licensePlate)) {
                positionToRemove = i;
                break;
            }
        }

        if (positionToRemove == -1) {
            System.out.println("Vehicle with license plate " + licensePlate + " not found.");
            return;
        }

        parkedVehicles.remove(positionToRemove);
        parkedVehicleTimestamps.remove(positionToRemove);
        availableSlots++;
        System.out.println("Vehicle removed successfully.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the total number of parking slots:");
        int totalSlots = 0;
        try {
            totalSlots = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        ParkingSystem parkingSystem = new ParkingSystem(totalSlots);

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Park a vehicle");
            System.out.println("2. Remove a vehicle (by license plate)");
            System.out.println("3. View parked vehicles");
            System.out.println("4. Exit");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter the license plate number of the vehicle:");
                    String licensePlate = scanner.next();

                    System.out.println("Enter the parking duration in hours:");
                    int duration = 0;
                    try {
                        duration = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine(); // Clear the input buffer
                        continue;
                    }

                    try {
                        parkingSystem.parkVehicle(licensePlate, duration);
                    } catch (VehicleAlreadyParkedException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter the license plate number of the vehicle to be removed:");
                    String plateToRemove = scanner.next();
                    parkingSystem.removeVehicle(plateToRemove);
                    break;
                case 3:
                    parkingSystem.displayParkedVehicles();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
