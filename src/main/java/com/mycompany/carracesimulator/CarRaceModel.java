package com.mycompany.carracesimulator;

/**
 * Stores the data and finishing order for the four race cars.
 *
 * @author alvee
 */
public class CarRaceModel {

    private String[] carTypes = {"Red race car", "Blue race car", "Green race car", "Orange race car"};
    private int[] carNumbers = {1, 2, 3, 4};
    private String[] carColors = {"Red", "Blue", "Green", "Orange"};
    private double[] xPositions = new double[4];
    private double[] yPositions = new double[4];
    private double[] speeds = {75, 85, 65, 80};
    private boolean[] finished = new boolean[4];
    private int[] finishOrder = new int[4];
    private int finishedCount;

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

    public boolean isFinished(int carIndex) {
        return finished[carIndex];
    }

    public int getFinishedCount() {
        return finishedCount;
    }

    public int getFinishedCarIndex(int place) {
        return finishOrder[place];
    }

    public void recordFinish(int carIndex) {
        if (!finished[carIndex]) {
            finished[carIndex] = true;
            finishOrder[finishedCount] = carIndex;
            finishedCount++;
        }
    }

    public void resetPositions() {
        finishedCount = 0;
        for (int i = 0; i < carNumbers.length; i++) {
            xPositions[i] = 10;
            yPositions[i] = 25 + i * 90;
            finished[i] = false;
            finishOrder[i] = -1;
        }
    }
}
