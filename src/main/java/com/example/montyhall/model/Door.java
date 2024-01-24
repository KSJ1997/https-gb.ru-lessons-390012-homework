package com.example.montyhall.model;

public class Door {
    private boolean hasCar;
    private boolean isOpened;

    public void placeCar() {
        this.hasCar = true;
    }

    public boolean hasCar() {
        return hasCar;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isOpened() {
        return isOpened;
    }
}
