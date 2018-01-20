package game.view;

import java.util.Iterator;
import java.util.LinkedList;

import game.model.Bullet;
import game.model.Enemy;
import game.model.Shooter;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
	public static final double XBOUND=600;
	public static final double YBOUND=500;
	public GameController() {
	}
	public void init()
	{	
		bullets=new LinkedList<Bullet>();
		shooter=new Shooter();
		addBullet();
		//bind shooter
		shooter.bind(shooterRender);
		//System.out.println(shooterRender.getCenterX()+" "+shooter.getX());
		//System.out.println(shooterRender.getCenterY()+" "+shooter.getY());
		//bind label
		scoreLabel.textProperty().bind(Bindings.concat("score: ").concat(shooter.getScore().asString()));
		healthLabel.textProperty().bind(Bindings.concat("health: ").concat(shooter.getHealth().asString()));
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
	public void addBullet()
	{
		Bullet b=new Bullet(shooter.getX(),shooter.getY(),shooter.getX()+100,shooter.getY()+100);
		bullets.add(b);
		/*
		l.setLayoutX(0);
		l.setLayoutY(0);
		System.out.println(shooter.getX());
		l.setStartX(shooter.getX());
		l.setStartY(shooter.getY());
		l.setEndX(shooter.getX()+100);
		l.setEndY(shooter.getY()+100);
		*/
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
		for(Iterator<Bullet> itor =bullets.iterator();itor.hasNext();)
		{
			Bullet b=itor.next();
			b.move();
		}
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
	}
	public void scoreInc(int increment)
	{
		shooter.scoreInc(increment);
	}
	public void healthInc(int increment) {shooter.healthInc(increment);}
}
