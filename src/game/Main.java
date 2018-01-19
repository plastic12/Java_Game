package game;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Main extends Application {
	public static final int FPS=50;
	private Stage primaryStage;
	private StackPane gamePane;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		try {
			this.primaryStage=primaryStage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Game.fxml"));

			gamePane=(StackPane) loader.load();
			GameController gameController=loader.getController();
			gameController.init();
			// Create a handler for animation
			EventHandler<ActionEvent> eventHandler = e -> {
				gameController.scoreInc(1);
				if(Math.random()>0.9)
					gameController.healthInc(-1);
			};

			// Create an animation for a running clock

			Scene scene=new Scene(gamePane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("game");
			primaryStage.show();
			Timeline animation = new Timeline(
					new KeyFrame(Duration.millis(1000/FPS), eventHandler));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play(); // Start animation
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
    ClockPane clock = new ClockPane(); // Create a clock

    // Create a handler for animation
    EventHandler<ActionEvent> eventHandler = e -> {
      clock.setCurrentTime(); // Set a new clock time
    };

    // Create an animation for a running clock
    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(1000), eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation

    // Create a scene and place it in the stage
    Scene scene = new Scene(clock, 250, 250);
		 */
		// Set the stage title
		//primaryStage.setScene(scene); // Place the scene in the stage
		//primaryStage.setResizable(false);
		// Display the stage
	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

