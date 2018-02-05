package game.model;

import game.Main;
import game.view.GameController;
import javafx.scene.shape.Circle;

//enemy base class
public abstract class Enemy extends Entity {
	protected int life;
	protected int damage;
	protected double velocity;
	//base constructor
	public Enemy() 
	{
		
	}
	//random set position
	protected int setPos()
	{
		int direction = (int) (Math.random()*4);
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
			p.setY(Math.random()*GameController.YBOUND);
			break;
		}
		default: {
			p.setX(0);
			p.setY(Math.random()*GameController.YBOUND);
			break;
		}
		}
		return direction;
	}
	// init position
	public Enemy(double x, double y)
	{
		super(x, y);
	}
	//check dead
	public void setDead()
	{
		life=0;
	}
	//damage getter
	public int getDamage() {
		return damage;
	}
	//check getShot
	public boolean getShot(Bullet b) 
	{
		double distance=b.getDistance(this);

		if(distance<getR())
		{
			life-=b.getDamage();
			return true;
		}
		return false;
	}
	//check dead
	public boolean isDead() {return (life<=0);}
	//check inbound
	public boolean inBound()
	{
		return p.getX()>0&&p.getX()<GameController.XBOUND&&p.getY()>0&&p.getY()<GameController.YBOUND;
	}
}
