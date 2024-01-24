package com.example.montyhall.model;

public class Player {
    private int selectedDoor;

    public void selectDoor() {
        this.selectedDoor = (int) (Math.random() * 3);
    }

    public void resetSelection() {
        this.selectedDoor = -1;
    }

    public void selectDoor(int door) {
        this.selectedDoor = door;
    }

    public int hasSelectedDoor() {
        return selectedDoor;
    }
}
