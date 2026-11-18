# Car Race Simulator

JavaFX Assignment 02: a four-car race using MVC.

## Run the project

1. Open CarRaceSimulator in NetBeans.
2. Let Maven download the dependencies from pom.xml.
3. Right-click the project and choose Run.

The app first shows four car pictures with a greeting and sound. Each picture stays for three seconds. The race screen opens after the slideshow.

## Controls

- Start starts the race or continues it after pausing.
- Pause pauses all four cars.
- Reset returns the cars to the start and clears the results.
- The speed slider changes how fast the cars move.

The message area shows the winning car number and color, followed by the finishing order.

## Project files

- CarRaceModel.java stores car types, numbers, colors, positions, speeds, and finishing order.
- CarRaceController.java handles the race buttons, animations, and results.
- primary.fxml contains the racetrack, shape cars, and controls.
- SlideshowController.java handles the pictures, captions, sound, and delay.
- slideshow.fxml contains the slideshow layout.
- race.css styles both screens.

The slideshow pictures were made in Paint. The race cars use JavaFX polygons, rectangles, circles, and text grouped together. The slideshow sound is chimes.wav from Windows Media.
