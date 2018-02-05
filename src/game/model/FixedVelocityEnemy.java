package game.model;

import game.view.GameController;

//base class for enemy with only one direction
public abstract class FixedVelocityEnemy extends Enemy 
{
	//base constructor
	public FixedVelocityEnemy()
	{
		
	}
	//init position
	public FixedVelocityEnemy(double d, double e) {
		super(d,e);
	}
	//set velocity base on direction
	protected void setVelocity(int direction)
	{
		switch (direction) {
		case 0: {
			xVelocity=0;
			yVelocity=-velocity;
			break;
		}
		case 1: {
			xVelocity=0;
			yVelocity=velocity;
			break;
		}
		case 2: {
			xVelocity=-velocity;
			yVelocity=0;
			break;
		}
		default: {
			xVelocity=velocity;
			yVelocity=0;
			break;
		}
		}
	}
}
