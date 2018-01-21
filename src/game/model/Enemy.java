package game.model;

import game.Main;
import game.view.GameController;
import javafx.scene.shape.Circle;

public class Enemy extends Entity {
	private int life =1;
	private int damage = 1;
	public static final double ACCELERATION=50;
	public static final double SPEED_LIMIT=100;
	private Circle face;
	private int direction; //0:up, 1:down, 2:left, 3:right
	
	public Enemy() {
		super(Math.random()*GameController.XBOUND, Math.random()*GameController.YBOUND, 10);
		face = new Circle();
		bind(face);
		direction = (int) (Math.random()*4);
		
		switch (direction) {
		case 0: {
			p.setX(Math.random()*GameController.XBOUND);
			p.setY(GameController.YBOUND);
			break;
		}
		case 1: {
			p.setX(Math.random()*GameController.XBOUND);
			p.setY(0);
			break;
		}
		case 2: {
			p.setX(GameController.XBOUND);
			p.setY(GameController.YBOUND);
			break;
		}
		default: {
			p.setX(0);
			p.setY(Math.random()*GameController.YBOUND);
			break;
		}
		}
	}
	public Enemy(double x, double y)
	{
		super(x, y, 10);
		face  = new Circle();
		bind(face);
	}
	public int getDamage() {
		return damage;
	}
	
	public boolean inBound()
	{
		return p.getX()>0&&p.getX()<GameController.XBOUND&&p.getY()>0&&p.getY()<GameController.YBOUND;
	}
	
	@Override
	public void move()
	{
		switch (direction) {
		case 0: {
			xVelocity-=Math.signum(xVelocity)*ACCELERATION/Main.FPS;
			yVelocity-=ACCELERATION/Main.FPS;
			break;
		}
		case 1: {
			xVelocity-=Math.signum(xVelocity)*ACCELERATION/Main.FPS;
			yVelocity+=ACCELERATION/Main.FPS;
			break;
		}
		case 2: {
			xVelocity-=ACCELERATION/Main.FPS;
			yVelocity-=Math.signum(yVelocity)*ACCELERATION/Main.FPS;
			break;
		}
		default: {
			xVelocity+=ACCELERATION/Main.FPS;
			yVelocity-=Math.signum(yVelocity)*ACCELERATION/Main.FPS;
			break;
		}
		}
		if(xVelocity>SPEED_LIMIT)
			xVelocity=SPEED_LIMIT;
		if(xVelocity<-SPEED_LIMIT)
			xVelocity=-SPEED_LIMIT;
		if(yVelocity>SPEED_LIMIT)
			yVelocity=SPEED_LIMIT;
		if(yVelocity<-SPEED_LIMIT)
			yVelocity=-SPEED_LIMIT;
		super.move();
	}
	
	public Circle getCircle()
	{
		return face;
	}
}
