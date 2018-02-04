package game.view;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import game.Main;
import game.model.Bullet;
import game.model.CircleDesign;
import game.model.Enemy;
import game.model.Level;
import game.model.Shooter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameController 
{
	//renderer
	@FXML
	private Label scoreLabel;
	@FXML
	private Rectangle healthBar;
	@FXML
	private Rectangle powerBar;
	@FXML
	private Rectangle progressBar;
	@FXML
	private Circle shooterRender;
	@FXML
	private AnchorPane gamePane;
	@FXML
	private ImageView bg;
	private Pane scenePane;
	private AnchorPane pausePane;
	private Curtain curtain;
	//model
	private Shooter shooter;
	private SimpleIntegerProperty score;
	private Level level;
	private int levelNo;
	//system or counter

	private boolean pause=false;
	private boolean running=true;
	private Timeline loop;
	// constant
	public static final double XBOUND=600;
	public static final double YBOUND=500;
	//toggle testing constant

	public GameController() {
	}


	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("gameController garbage");
		super.finalize();
	}

	public void globalPlay() {running=true;loop.play();}
	public void globalStop() {running=false;loop.pause();}
	public void healthInc(int increment) {shooter.healthInc(increment);}
	public void init(Pane scene) throws IOException
	{
		//init variable
		score=new SimpleIntegerProperty(0);
		shooter=new Shooter();
		level=null;
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(GameController.class.getResource("PausePane.fxml"));
		pausePane=loader.load();
		PauseController controller=loader.getController();
		controller.bindGamePane(gamePane);

		//init curtain
		scenePane=scene;
		curtain=new Curtain(this);
		curtain.bind(scene);
		//bind model and render
		shooter.bindCircle(new CircleDesign(shooterRender));
		shooter.bindHealth(healthBar);
		shooter.bindPower(powerBar);
		scoreLabel.textProperty().bind(Bindings.concat("score: ").concat(score.asString()));
		//add mouse monitor
		gamePane.setOnMouseMoved(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				shooter.setMouseX(event.getX());
				shooter.setMouseY(event.getY());
			}

		});
		//main loop and animation
		EventHandler<ActionEvent> eventHandler = e -> 
		{
			boolean levelUp=level.loop(score,shooter);
			//update render
			cleanUp();
			level.render(gamePane.getChildren());
			//check dead and level up
			if(isDead())
				try {
					quitGame();
					Main.gameOver(score.get());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(levelUp)
			{
				cleanUp();
				if(levelNo!=6)
				{
					start(levelNo+1);
				} else
					try {
						//TODO game Win panel, function
						quitGame();
						Main.gameWin(score.get());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		};
		loop = new Timeline(
				new KeyFrame(Duration.millis(1000/Main.FPS), eventHandler));
		loop.setCycleCount(Timeline.INDEFINITE);
	}
	public boolean isDead()
	{
		return (shooter.getHealth()<=0);
	}
	public void cleanUp()
	{
		gamePane.getChildren().retainAll(bg);
		shooter.render(gamePane.getChildren());

	}
	public void pressHandler(KeyEvent e)
	{

		switch(e.getCode())
		{
		case LEFT:
			shooter.LEFT=true;
			break;
		case RIGHT:
			shooter.RIGHT=true;
			break;
		case DOWN:
			shooter.DOWN=true;
			break;
		case UP:
			shooter.UP=true;
			break;
		case ESCAPE:
			if(running)
			{
				if(!pause)
				{
					//pause
					loop.pause();
					gamePane.getChildren().add(pausePane);
				}
				else
				{
					//resume
					loop.play();
					gamePane.getChildren().remove(pausePane);
				}
				pause=!pause;
				break;
			}
		default:
			break;
		}
	}
	public void quitGame()
	{
		loop.stop();
	}
	public void releaseHandler(KeyEvent e)
	{
		switch(e.getCode())
		{
		case LEFT:
			shooter.LEFT=false;
			break;
		case RIGHT:
			shooter.RIGHT=false;
			break;
		case DOWN:
			shooter.DOWN=false;
			break;
		case UP:
			shooter.UP=false;
			break;
		default:
			break;
		}
	}
	public void removeBullet(Bullet b)
	{
		gamePane.getChildren().remove(b.getLine());
	}
	public void start(int level)
	{
		this.level=Level.initLevel(level);
		levelNo=level;
		loop.play();
		if(Main.curtain)
		{
			curtain.setText(this.level.getPrompt());
			curtain.start(scenePane);
		}
		this.level.bindProgress(progressBar);
	}


}
