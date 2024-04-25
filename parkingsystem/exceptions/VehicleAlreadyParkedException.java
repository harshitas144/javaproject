package org.example.parkingsystem.exceptions;

public class VehicleAlreadyParkedException extends Exception {
    public VehicleAlreadyParkedException(String message) {
        super(message);
    }
}
