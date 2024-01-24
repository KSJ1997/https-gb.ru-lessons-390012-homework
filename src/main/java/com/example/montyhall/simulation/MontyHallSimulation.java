package com.example.montyhall.simulation;

import com.example.montyhall.model.Door;
import com.example.montyhall.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallSimulation {
    private static final int NUM_DOORS = 3;
    private static final Random random = new Random();

    private final int totalGames;
    private final Map<Integer, Boolean> results;

    public MontyHallSimulation() {
        this.totalGames = 1000; 
        this.results = new HashMap<>();
    }

    public void runSimulation() {
        for (int i = 1; i <= totalGames; i++) {
            Door[] doors = initializeDoors();
            Player player = new Player();
            player.selectDoor();

            
            int hostOpenedDoor = openDoorWithoutCar(doors, player);

            
            boolean switchDoor = random.nextBoolean();
            if (switchDoor) {
                switchDoor(doors, hostOpenedDoor, player);
            }

            
            boolean isWin = doors[player.hasSelectedDoor()].isOpened() && doors[player.hasSelectedDoor()].hasCar();
            results.put(i, isWin);
        }

        printStatistics();
    }

    private Door[] initializeDoors() {
        Door[] doors = new Door[NUM_DOORS];
        for (int i = 0; i < NUM_DOORS; i++) {
            doors[i] = new Door();
        }

        int carBehindDoor = random.nextInt(NUM_DOORS);
        doors[carBehindDoor].placeCar();

        return doors;
    }

    private int openDoorWithoutCar(Door[] doors, Player player) {
        int hostOpenedDoor;
        do {
            hostOpenedDoor = random.nextInt(NUM_DOORS);
        } while (hostOpenedDoor == player.hasSelectedDoor() || doors[hostOpenedDoor].hasCar());

        doors[hostOpenedDoor].open();

        return hostOpenedDoor;
    }

    private void switchDoor(Door[] doors, int hostOpenedDoor, Player player) {
        int remainingDoor;
        do {
            remainingDoor = random.nextInt(NUM_DOORS);
        } while (remainingDoor == hostOpenedDoor || remainingDoor == player.hasSelectedDoor());

        player.selectDoor(remainingDoor);
    }

    private void printStatistics() {
        long wins = results.values().stream().filter(Boolean::booleanValue).count();
        long losses = totalGames - wins;

        System.out.println("Monty Hall Simulation Results:");
        System.out.println("Total Games: " + totalGames);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
    }

    public static void main(String[] args) {
        MontyHallSimulation simulation = new MontyHallSimulation();
        simulation.runSimulation();
    }
}
