package game;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import game.view.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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
			loader.setLocation(Main.class.getResource("view/Game.fxml"));
			gamePane=(StackPane) loader.load();
			GameController gameController=loader.getController();
			gamePane.addEventHandler(KeyEvent.KEY_PRESSED, e->{
				System.out.println("pressed");
			});
			gamePane.addEventHandler(KeyEvent.KEY_RELEASED, e->{
				System.out.println("released");
			});
			
			gameController.init();
			// Create a handler for animation
			EventHandler<ActionEvent> eventHandler = e -> 
			{
				gameController.movePhase();
			};

			// Create an animation for a running clock

			Scene scene=new Scene(gamePane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("game");
			primaryStage.setResizable(false);
			primaryStage.show();
			Timeline animation = new Timeline(
					new KeyFrame(Duration.millis(1000/FPS), eventHandler));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play(); // Start animation
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

