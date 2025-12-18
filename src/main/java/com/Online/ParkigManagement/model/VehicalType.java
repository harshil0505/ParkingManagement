package com.Online.ParkigManagement.model;

public enum VehicalType {
    TWO_WHEELER,
    THREE_WHEELER,
    FOUR_WHEELER,
    TRUCK;

    public Object toUpperCase() {
        return this.name().toUpperCase();
    }
}