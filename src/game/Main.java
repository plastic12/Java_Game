package game;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import game.view.GameController;
import game.view.GameOverController;
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
	public static void option() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Option.fxml"));
		Pane pane = (Pane) loader.load();
		OptionController controller=loader.getController();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
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
			gameController.start(5);
			bgm.game();
		}
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
	
	public static void viewLeaderboard() throws IOException {
		FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(Main.class.getResource("view/Leaderboard.fxml"));			
		Pane leader = (Pane) loader1.load();

		Scene leaderScene = new Scene(leader);
		primaryStage.setScene(leaderScene);
	}
	public static void readConfig()
	{
		//System.out.println(Main.class.getResource("resource/config.txt")==null);
		
		if((Main.class.getResource("resource/config.txt")==null))
		{
			try {
				URL link=new URL(Main.class.getResource("resource/")+"config.txt");
				configPath=Paths.get(link.toURI());
				writeConfig();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			configPath=Paths.get(Main.class.getResource("resource/config.txt").toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

