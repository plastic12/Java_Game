package game.view;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import game.Main;
import game.model.Bullet;
import game.model.Enemy;
import game.model.Shooter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GameController 
{
	//renderer
	@FXML
	private Label scoreLabel;
	@FXML
	private Label healthLabel;
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
	private LinkedList<Bullet> bullets;
	public static LinkedList<Enemy> enemies;
	private Shooter shooter;
	//system or counter
	private double mouseX=0;
	private double mouseY=0;
	private int shootCounter=0;
	private int enemyCounter=0;
	private int collCheckCounter=0;
	private boolean pause=false;
	private boolean running=true;
	private Timeline loop;
	// constant
	public static final double shootfreq=2;
	public static final double enemyfreq=1;
	public static final double collisionCheckfreq=50;
	public static final double XBOUND=600;
	public static final double YBOUND=500;
	public GameController() {
	}
	public void init(Pane scene) throws IOException
	{
		//init variable
		bullets=new LinkedList<Bullet>();
		enemies=new LinkedList<Enemy>();
		shooter=new Shooter();
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
		shooter.bind(shooterRender);
		shooter.bindHealth(healthLabel);
		shooter.bindScore(scoreLabel);
		//add mouse monitor
		gamePane.setOnMouseMoved(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				mouseX=event.getX();
				mouseY=event.getY();
			}

		});
		//main loop and animation
		EventHandler<ActionEvent> eventHandler = e -> 
		{
			//gen enemy
			genEnemy();
			//gen bullet
			genBullet();
			//check collision
			collisionPhase();
			//move
			movePhase();
			//remove outBound object
			removeOutBound();
			//check dead
			if(isDead())
				try {
					quitGame();
					Main.gameOver(shooter.getScore());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		};
		loop = new Timeline(
				new KeyFrame(Duration.millis(1000/Main.FPS), eventHandler));
		loop.setCycleCount(Timeline.INDEFINITE);
		loop.play();
	}
	public void start(int level)
	{
		globalStop();
		curtain.setText("Level: "+level);
		curtain.start(scenePane);
	}
	public void quitGame()
	{
		loop.stop();
	}
	public void genEnemy()
	{
		enemyCounter++;
		if(enemyCounter>=Main.FPS/enemyfreq)
		{
			addEnemy();
			enemyCounter=0;
		}
	}
	public void addEnemy()
	{
		Enemy e=new Enemy();
		enemies.add(e);
		gamePane.getChildren().add(e.getCircle());
	}
	public void pressHandler(KeyEvent e)
	{
		if(running)
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
				//System.out.println("pause");
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
			default:
				break;
			}
		}
	}
	public void genBullet()
	{
		shootCounter++;
		if(shootCounter>=Main.FPS/shootfreq)
		{
			addBullet();
			shootCounter=0;
		}
	}
	public void globalStop() {running=false;loop.pause();}
	public void globalPlay() {running=true;loop.play();}
	private void addBullet()
	{
		Bullet b=new Bullet(shooter.getX(),shooter.getY(),mouseX,mouseY);
		bullets.add(b);
		gamePane.getChildren().add(b.getLine());
	}
	public void removeBullet(Bullet b)
	{
		gamePane.getChildren().remove(b.getLine());
	}
	public void releaseHandler(KeyEvent e)
	{
		if(running)
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
	}
	public void movePhase()
	{
		//move enemy
		for(Iterator<Enemy> itor =enemies.iterator();itor.hasNext();)
		{
			Enemy b=itor.next();
			b.move();
		}
		//move bullet
		for(Iterator<Bullet> itor =bullets.iterator();itor.hasNext();)
		{
			Bullet b=itor.next();
			b.move();
		}
		//move player
		shooter.accelerate();
		shooter.move();
	}
	public void collisionPhase()
	{
		collCheckCounter++;
		if(collCheckCounter>=Main.FPS/collisionCheckfreq)
		{
			//bullet enemy check
			for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
			{
				Bullet b=itor.next();

				for(Iterator<Enemy> itor2=enemies.iterator();itor2.hasNext();)
				{
					Enemy e=itor2.next();
					double distance=b.getDistance(e);

					if(distance<e.getR())
					{
						shooter.scoreInc(e.getScore());
						itor.remove();
						itor2.remove();
						gamePane.getChildren().remove(b.getLine());
						gamePane.getChildren().remove(e.getCircle());
					}
				}
			}
			//player enemy check
			for(Iterator<Enemy> itor2=enemies.iterator();itor2.hasNext();)
			{
				Enemy e=itor2.next();
				if(e.isCollide(shooter))
				{
					shooter.healthInc(-e.getDamage());
					shooter.scoreInc(e.getScore());
					itor2.remove();
					gamePane.getChildren().remove(e.getCircle());
				}
			}
			collCheckCounter=0;
		}
	}
	public void removeOutBound()
	{
		//remove Bullet
		for(Iterator<Bullet> itor=bullets.iterator();itor.hasNext();)
		{
			Bullet b=itor.next();
			if(!b.inBound())
			{
				gamePane.getChildren().remove(b.getLine());
				itor.remove();
				//System.out.println("removed");
			}
		}
		//remove Enemy
		for(Iterator<Enemy> itor=enemies.iterator();itor.hasNext();)
		{
			Enemy b=itor.next();
			if(!b.inBound())
			{
				gamePane.getChildren().remove(b.getCircle());
				itor.remove();
			}
		}
	}
	public void scoreInc(int increment)
	{
		shooter.scoreInc(increment);
	}
	public void healthInc(int increment) {shooter.healthInc(increment);}
	public boolean isDead()
	{
		return (shooter.getHealth()<=0);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("gameController garbage");
		super.finalize();
	}
	
	
}
