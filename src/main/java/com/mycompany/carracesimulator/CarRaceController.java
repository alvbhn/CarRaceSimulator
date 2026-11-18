package com.mycompany.carracesimulator;

import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Controls car movement, race buttons, and race messages.
 *
 * @author alvee
 */
public class CarRaceController {

    @FXML
    private Pane racePane;

    @FXML
    private Button startButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button resetButton;

    @FXML
    private Slider speedSlider;

    @FXML
    private Label raceMessageLabel;

    @FXML
    private Group carOne;

    @FXML
    private Group carTwo;

    @FXML
    private Group carThree;

    @FXML
    private Group carFour;

    private CarRaceModel raceModel = new CarRaceModel();
    private Group[] cars;
    private TranslateTransition[] carAnimations;

    @FXML
    public void initialize() {
        cars = new Group[]{carOne, carTwo, carThree, carFour};
        carAnimations = new TranslateTransition[raceModel.getCarCount()];

        for (int i = 0; i < cars.length; i++) {
            cars[i].setLayoutX(raceModel.getXPosition(i));
            cars[i].setLayoutY(raceModel.getYPosition(i));

            // Move the front of each car 700 pixels to the finish line.
            double duration = 700 / raceModel.getSpeed(i) * 1000;
            carAnimations[i] = new TranslateTransition(Duration.millis(duration), cars[i]);
            carAnimations[i].setFromX(0);
            carAnimations[i].setToX(700);

            cars[i].translateXProperty().addListener((observable, oldValue, newValue) -> {
                checkFinishLine();
            });
        }

        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            for (int i = 0; i < carAnimations.length; i++) {
                carAnimations[i].setRate(speedSlider.getValue());
            }
        });

        pauseButton.setDisable(true);
        updateRaceMessage(raceModel.getCarCount() + " cars ready to race");
    }

    private void checkFinishLine() {
        for (int i = 0; i < cars.length; i++) {
            raceModel.setPosition(i, cars[i].getLayoutX() + cars[i].getTranslateX(),
                    cars[i].getLayoutY());

            if (!raceModel.isFinished(i) && cars[i].getTranslateX() >= 700) {
                raceModel.recordFinish(i);
                if (raceModel.getFinishedCount() == raceModel.getCarCount()) {
                    startButton.setDisable(true);
                    pauseButton.setDisable(true);
                    updateRaceMessage("Race finished");
                } else {
                    updateRaceMessage("Race in progress");
                }
            }
        }
    }

    private void updateRaceMessage(String status) {
        String message = status;
        if (raceModel.getFinishedCount() > 0) {
            int winner = raceModel.getFinishedCarIndex(0);
            message += "\nCongratulations! Car " + raceModel.getCarNumber(winner)
                    + " (" + raceModel.getCarColor(winner) + ") wins!";
            message += "\nFinishing order:";
            for (int i = 0; i < raceModel.getFinishedCount(); i++) {
                int carIndex = raceModel.getFinishedCarIndex(i);
                message += "\n" + (i + 1) + ". Car " + raceModel.getCarNumber(carIndex)
                        + " - " + raceModel.getCarColor(carIndex);
            }
        }
        raceMessageLabel.setText(message);
    }

    @FXML
    private void startButtonPressed() {
        for (int i = 0; i < carAnimations.length; i++) {
            if (!raceModel.isFinished(i)) {
                carAnimations[i].play();
            }
        }
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        updateRaceMessage("Race started");
    }

    @FXML
    private void pauseButtonPressed() {
        for (int i = 0; i < carAnimations.length; i++) {
            carAnimations[i].pause();
        }
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        updateRaceMessage("Race paused");
    }

    @FXML
    private void resetButtonPressed() {
        for (int i = 0; i < carAnimations.length; i++) {
            carAnimations[i].stop();
            cars[i].setTranslateX(0);
        }
        raceModel.resetPositions();
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        updateRaceMessage(raceModel.getCarCount() + " cars ready to race");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
