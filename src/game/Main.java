package game;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import game.view.GameController;
import game.view.GameOverController;
import game.view.GameWinController;
import game.view.LeaderboardController;
import game.view.OptionController;
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
	public static boolean music=true;
	public static boolean curtain=true;
	public static boolean win=false;
	public static boolean color=false;
	public static Path configPath=null;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		//read config
		readConfig();
		try {
			//show menu
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
	//menu scene
	public static void menu() throws IOException {
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(Main.class.getResource("view/Start.fxml"));	
		Pane startPane = (Pane) loader1.load();
		Scene StartScene = new Scene(startPane);
		bgm.menu();
		primaryStage.setScene(StartScene);
	}
	//option scene
	public static void option() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Option.fxml"));
		Pane pane = (Pane) loader.load();
		OptionController controller=loader.getController();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
	}
	//game scene
	public static void startGame() throws IOException {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Game.fxml"));
			StackPane gamePane =(StackPane) loader.load();
			GameController gameController=loader.getController();
			Scene scene=new Scene(gamePane);
			primaryStage.setScene(scene);
			gameController.init(gamePane);
			//start game in level 1
			gameController.start(0);
			bgm.game();
		}
	//gameOver scene
	public static void gameOver(int score) throws IOException
	{
		bgm.gameOver();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GameOver.fxml"));
		Pane pane = (Pane) loader.load();
		GameOverController controller=loader.getController();
		controller.init(score);
		lc.addScore(score);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
	}
	//gameWin scene
	public static void gameWin(int score) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GameWin.fxml"));
		Pane pane = (Pane) loader.load();
		GameWinController controller=loader.getController();
		controller.init(score);
		lc.addScore(score);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
	}
	//leaderboard scene
	public static void viewLeaderboard() throws IOException {
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(Main.class.getResource("view/Leaderboard.fxml"));			
		Pane leader = (Pane) loader1.load();
		Scene leaderScene = new Scene(leader);
		primaryStage.setScene(leaderScene);
	}
	//read config
	public static void readConfig()
	{
		//if can't find file
		configPath=Paths.get("config.txt");
		if(!Files.exists(configPath))
		{
				writeConfig();
		}
		ArrayList<String> fileArray=null;
		try {
			fileArray=(ArrayList<String>) Files.readAllLines(configPath);
			music=Boolean.parseBoolean(fileArray.get(0).substring((1+fileArray.get(0).indexOf('='))));
			curtain=Boolean.parseBoolean(fileArray.get(1).substring((1+fileArray.get(1).indexOf('='))));
			win=Boolean.parseBoolean(fileArray.get(2).substring((1+fileArray.get(2).indexOf('='))));
			color=Boolean.parseBoolean(fileArray.get(3).substring((1+fileArray.get(3).indexOf('='))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeConfig()
	{
		try(PrintWriter out=new PrintWriter(Files.newOutputStream(configPath, CREATE)))
		{
			out.println("music="+music);
			out.println("curtain="+curtain);
			out.println("win="+win);
			out.println("color="+color);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

