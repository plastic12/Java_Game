package game.model;

import game.Main;
import game.view.GameController;
import javafx.scene.shape.Circle;

public abstract class Enemy extends Entity {
	protected int life;
	protected int damage;
	protected double velocity;
	public Enemy() 
	{
		
	}
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
	public Enemy(double x, double y)
	{
		super(x, y);
		bindCircle(new Circle());
	}
	public void dead()
	{
		if(isDead())
		{
			postDeadAction();
		}
	}
	public void setDead()
	{
		life=0;
	}
	protected void postDeadAction()
	{
		
	}
	public int getDamage() {
		return damage;
	}
	public void getShot(Bullet b) {life-=b.getDamage();}
	public boolean isDead() {return (life<=0);}
	public boolean inBound()
	{
		return p.getX()>0&&p.getX()<GameController.XBOUND&&p.getY()>0&&p.getY()<GameController.YBOUND;
	}
}
