package game.model;

import game.view.GameController;

public abstract class FixedVelocityEnemy extends Enemy 
{
	public FixedVelocityEnemy()
	{

	}
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
