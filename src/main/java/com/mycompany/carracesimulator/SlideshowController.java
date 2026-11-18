package com.mycompany.carracesimulator;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Shows the racers before opening the race screen.
 *
 * @author alvee
 */
public class SlideshowController {

    @FXML
    private ImageView carImageView;

    @FXML
    private Label carCaptionLabel;

    private CarRaceModel raceModel = new CarRaceModel();
    private int currentCar;
    private Timeline slideshowTimer;
    private MediaPlayer soundPlayer;

    @FXML
    public void initialize() {
        showCar();
        Media sound = new Media(getClass().getResource("chimes.wav").toExternalForm());
        soundPlayer = new MediaPlayer(sound);
        soundPlayer.setAutoPlay(true);

        slideshowTimer = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            nextCar();
        }));
        slideshowTimer.setCycleCount(raceModel.getCarCount());
        slideshowTimer.play();
    }

    private void showCar() {
        String imageFile = "car" + raceModel.getCarNumber(currentCar) + ".png";
        if (getClass().getResource(imageFile) != null) {
            Image carImage = new Image(getClass().getResource(imageFile).toExternalForm());
            carImageView.setImage(carImage);
        } else {
            carImageView.setImage(null);
        }
        carCaptionLabel.setText("Car " + raceModel.getCarNumber(currentCar)
                + " - " + raceModel.getCarColor(currentCar)
                + " " + raceModel.getCarType(currentCar));
    }

    private void nextCar() {
        currentCar++;
        if (currentCar < raceModel.getCarCount()) {
            showCar();
        } else {
            slideshowTimer.stop();
            soundPlayer.stop();
            try {
                App.setRoot("primary");
            } catch (IOException exception) {
                carCaptionLabel.setText("Unable to open the race screen.");
                exception.printStackTrace();
            }
        }
    }
}
