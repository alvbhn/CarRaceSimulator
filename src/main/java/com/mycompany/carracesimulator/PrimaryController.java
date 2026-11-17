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
 * Controls basic car movement and the race buttons.
 *
 * @author alvee
 */
public class PrimaryController {

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
        }

        pauseButton.setDisable(true);
        raceMessageLabel.setText(raceModel.getCarCount() + " cars ready to race");
    }

    @FXML
    private void startButtonPressed() {
        for (int i = 0; i < carAnimations.length; i++) {
            if (cars[i].getTranslateX() < 700) {
                carAnimations[i].play();
            }
        }
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        raceMessageLabel.setText("Race started");
    }

    @FXML
    private void pauseButtonPressed() {
        for (int i = 0; i < carAnimations.length; i++) {
            carAnimations[i].pause();
            raceModel.setPosition(i, cars[i].getLayoutX() + cars[i].getTranslateX(),
                    cars[i].getLayoutY());
        }
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        raceMessageLabel.setText("Race paused");
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
        raceMessageLabel.setText(raceModel.getCarCount() + " cars ready to race");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
