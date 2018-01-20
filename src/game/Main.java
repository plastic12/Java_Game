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
			
			gameController.init();
			//main loop
			EventHandler<ActionEvent> eventHandler = e -> 
			{
				//gen enemy
				//gen bullet
				gameController.genBullet();
				//move
				gameController.movePhase();
				//check collision
				//remove outBound object
				gameController.removeOutBound();
			};

			Scene scene=new Scene(gamePane);
			//add event handler
			scene.addEventHandler(KeyEvent.KEY_PRESSED, e->{
				gameController.pressHandler(e);
			});
			scene.addEventHandler(KeyEvent.KEY_RELEASED, e->{
				gameController.releaseHandler(e);
			});
			//render init
			primaryStage.setScene(scene);
			primaryStage.setTitle("game");
			primaryStage.setResizable(false);
			primaryStage.show();
			Timeline animation = new Timeline(
					new KeyFrame(Duration.millis(1000/FPS), eventHandler));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play(); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

