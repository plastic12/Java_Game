package game;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import game.view.GameController;
import game.view.GameOverController;
import game.view.LeaderboardController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Main extends Application{
	public static final int FPS=50;
	private static Stage primaryStage;
	private static LeaderboardController lc = new LeaderboardController();
	private static Sound bgm = new Sound();

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			menu();
					
			primaryStage.setTitle("game");			
			primaryStage.setResizable(false);
			primaryStage.show();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void menu() throws IOException {
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(Main.class.getResource("view/Start.fxml"));	
		Pane startPane = (Pane) loader1.load();

		Scene StartScene = new Scene(startPane);
		bgm.menu();
		primaryStage.setScene(StartScene);
	}
	
	public static void startGame() throws IOException {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Game.fxml"));
			StackPane gamePane =(StackPane) loader.load();
			GameController gameController=loader.getController();
			
			gameController.init(gamePane);
			Scene scene=new Scene(gamePane);
			//add event handler
			scene.addEventHandler(KeyEvent.KEY_PRESSED, e->{
				gameController.pressHandler(e);
			});
			scene.addEventHandler(KeyEvent.KEY_RELEASED, e->{
				gameController.releaseHandler(e);
			});
			primaryStage.setScene(scene);
			gameController.start(2);
			bgm.game();
		}
	public static void gameOver(int score) throws IOException
	{
		bgm.gameOver();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GameOver.fxml"));
		Pane pane = (Pane) loader.load();
		GameOverController controller=loader.getController();
		if(controller==null)
			System.out.println("fail");
		controller.init(score);
		lc.addScore(score);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
	}
	
	public static void viewLeaderboard() throws IOException {
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(Main.class.getResource("view/Leaderboard.fxml"));			
		Pane leader = (Pane) loader1.load();

		Scene leaderScene = new Scene(leader);
		primaryStage.setScene(leaderScene);
	}		
}

