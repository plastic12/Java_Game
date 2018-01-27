package game.model;

import game.Main;
import game.view.GameController;
import javafx.scene.shape.Circle;

public class Enemy extends Entity {
	private int score=10;
	private int life =1;
	private int damage = 50;
	public static final double velocity=20;
	private int direction; //0:up, 1:down, 2:left, 3:right
	public Enemy() {
		super(Math.random()*GameController.XBOUND, Math.random()*GameController.YBOUND, 10);
		bindCircle(new Circle());
		direction = (int) (Math.random()*4);
		
		switch (direction) {
		case 0: {
			p.setX(Math.random()*GameController.XBOUND);
			p.setY(GameController.YBOUND);
			xVelocity=0;
			yVelocity=-velocity;
			break;
		}
		case 1: {
			p.setX(Math.random()*GameController.XBOUND);
			p.setY(0);
			xVelocity=0;
			yVelocity=velocity;
			break;
		}
		case 2: {
			p.setX(GameController.XBOUND);
			p.setY(Math.random()*GameController.YBOUND);
			xVelocity=-velocity;
			yVelocity=0;
			break;
		}
		default: {
			p.setX(0);
			p.setY(Math.random()*GameController.YBOUND);
			xVelocity=velocity;
			yVelocity=0;
			break;
		}
		}
	}
	public Enemy(double x, double y)
	{
		super(x, y, 10);
		bindCircle(new Circle());
	}
	public int getDamage() {
		return damage;
	}
	
	public boolean inBound()
	{
		return p.getX()>0&&p.getX()<GameController.XBOUND&&p.getY()>0&&p.getY()<GameController.YBOUND;
	}
	public int getScore() {return score;}
}
