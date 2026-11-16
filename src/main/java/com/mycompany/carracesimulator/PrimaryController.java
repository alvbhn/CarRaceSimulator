package com.mycompany.carracesimulator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

/**
 * Connects the race controls to the race data.
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

    private CarRaceModel raceModel = new CarRaceModel();

    @FXML
    public void initialize() {
        raceMessageLabel.setText(raceModel.getCarCount() + " cars ready to race");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
