package game.view;

import java.util.Iterator;
import java.util.LinkedList;

import game.Main;
import game.model.Bullet;
import game.model.Enemy;
import game.model.Shooter;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GameController 
{
	@FXML
	private Label scoreLabel;
	@FXML
	private Label healthLabel;
	@FXML
	private Circle shooterRender;
	@FXML
	private AnchorPane gamePane;
	private LinkedList<Bullet> bullets;
	private Shooter shooter;
	private double mouseX=0;
	private double mouseY=0;
	private int shootCounter=0;
	public static final double shootfreq=2;
	public static final double XBOUND=600;
	public static final double YBOUND=500;
	public GameController() {
	}
	public void init()
	{	
		bullets=new LinkedList<Bullet>();
		shooter=new Shooter();
		//addBullet();
		//bind shooter
		shooter.bind(shooterRender);
		//bind label
		scoreLabel.textProperty().bind(Bindings.concat("score: ").concat(shooter.getScore().asString()));
		healthLabel.textProperty().bind(Bindings.concat("health: ").concat(shooter.getHealth().asString()));
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
		default:
			break;
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
	public void movePhase()
	{
		//move enemy
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
				System.out.println("removed");
			}
		}
		//remove Enemy
		for(int i =0; i<Main.Enemies.size(); i++)
		{
			if(!Main.Enemies.get(i).inBound())
			{
				Main.Enemies.remove(i);
			}
		}
	}
	public void scoreInc(int increment)
	{
		shooter.scoreInc(increment);
	}
	public void healthInc(int increment) {shooter.healthInc(increment);}
}
