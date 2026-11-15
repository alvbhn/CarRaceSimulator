package com.mycompany.carracesimulator;

/**
 * Stores the data for the four race cars.
 *
 * @author alvee
 */
public class CarRaceModel {

    private String[] carTypes = {"Sedan", "Coupe", "Hatchback", "Sports Car"};
    private int[] carNumbers = {1, 2, 3, 4};
    private String[] carColors = {"Red", "Blue", "Green", "Orange"};
    private double[] xPositions = new double[4];
    private double[] yPositions = new double[4];
    private double[] speeds = {75, 85, 65, 80};

    public CarRaceModel() {
        resetPositions();
    }

    public int getCarCount() {
        return carNumbers.length;
    }

    public String getCarType(int carIndex) {
        return carTypes[carIndex];
    }

    public int getCarNumber(int carIndex) {
        return carNumbers[carIndex];
    }

    public String getCarColor(int carIndex) {
        return carColors[carIndex];
    }

    public double getXPosition(int carIndex) {
        return xPositions[carIndex];
    }

    public double getYPosition(int carIndex) {
        return yPositions[carIndex];
    }

    public double getSpeed(int carIndex) {
        return speeds[carIndex];
    }

    public void setPosition(int carIndex, double x, double y) {
        xPositions[carIndex] = x;
        yPositions[carIndex] = y;
    }

    public void setSpeed(int carIndex, double speed) {
        speeds[carIndex] = speed;
    }

    public void resetPositions() {
        for (int i = 0; i < carNumbers.length; i++) {
            xPositions[i] = 10;
            yPositions[i] = 25 + i * 90;
        }
    }
}
