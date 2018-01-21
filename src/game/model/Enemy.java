package game.model;

import java.util.Iterator;

import game.Main;
import game.view.GameController;

public class Enemy extends Entity {
	private int life =1;
	private int damage = 1;
	private int direction; //0:up, 1:down, 2:left, 3:right
	
	public Enemy() {
		super();
		switch ((int) Math.random()*4) {
		case 0: {
			p.setX(Math.random()*GameController.XBOUND);
			p.setY(560);
			direction = 0;
			break;
		}
		case 1: {
			p.setX(Math.random()*GameController.XBOUND);
			p.setY(0);
			direction = 1;
			break;
		}
		case 2: {
			p.setX(602);
			p.setY(Math.random()*GameController.YBOUND);
			direction = 2;
			break;
		}
		default: {
			p.setX(0);
			p.setY(Math.random()*GameController.YBOUND);
			direction = 3;
			break;
		}
	}
		
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
			xVelocity = 0;
			yVelocity = 1;
			break;
		}
		case 1: {
			xVelocity = 0;
			yVelocity = -1;
			break;
		}
		case 2: {
			xVelocity = -1;
			yVelocity = 0;
			break;
		}
		default: {
			xVelocity = 1;
			yVelocity = 0;
			break;
		}
		}
		super.move();
	}
	
}
